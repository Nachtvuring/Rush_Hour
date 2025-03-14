package be.kdg.project.view.gameScreen;

import be.kdg.project.model.Auto;
import be.kdg.project.model.Game;
import be.kdg.project.model.HighScore;
import be.kdg.project.view.PopupWindow;
import be.kdg.project.view.beginScreen.HomeView;
import javafx.scene.input.MouseEvent;

public class GamePresenter {
    private final Game game;
    private final GameView view;
    private final HighScore highScoreManager;
    private final String currentDifficulty;
    private final int currentLevel;

    public GamePresenter(GameView view, String difficulty, int level) {
        this.game = new Game();
        this.view = view;
        this.highScoreManager = new HighScore();
        this.currentDifficulty = difficulty;
        this.currentLevel = level;

        // Store reference to this presenter in the view
        view.setPresenter(this);

        // Initialize the game with the selected difficulty and level
        game.initializeGame(difficulty, level);

        // Set up initial UI state
        updateHighScoreDisplay();
        this.view.updateSpeelveld(game.getAutos());
        updateScore();

        // Set up event handlers
        attachEventHandlers();
    }

    private void updateHighScoreDisplay() {
        int highScore = highScoreManager.getTopScore(currentDifficulty, currentLevel);
        view.updateHighScoreDisplay(highScore);
    }

    private void updateScore() {
        int currentScore = game.getScore();
        view.updateScoreDisplay(currentScore);
    }

    private void attachEventHandlers() {
        view.getSpeelveld().setOnMouseClicked(this::handleGridClick);
        view.getBackButton().setOnAction(e -> handleQuitButton());
    }

    private void handleQuitButton() {
        QuitPopup.display(view);
    }

    private void handleGridClick(MouseEvent event) {
        int clickX = (int) (event.getX() / 50);
        int clickY = (int) (event.getY() / 50);
        boolean[] clickedFront = new boolean[1];

        for (Auto auto : game.getAutos()) {
            if (isClickOnAuto(auto, clickX, clickY, clickedFront)) {
                moveAuto(auto, clickedFront[0]);
                break;
            }
        }
    }

    private boolean isClickOnAuto(Auto auto, int clickX, int clickY, boolean[] clickedFront) {
        if (auto.isHorizontaal()) {
            if (clickY == auto.getyPos() && clickX >= auto.getxPos() && clickX < auto.getxPos() + auto.getLengte()) {
                clickedFront[0] = (clickX >= auto.getxPos() + auto.getLengte() / 2);
                return true;
            }
        } else {
            if (clickX == auto.getxPos() && clickY >= auto.getyPos() && clickY < auto.getyPos() + auto.getLengte()) {
                clickedFront[0] = (clickY >= auto.getyPos() + auto.getLengte() / 2);
                return true;
            }
        }
        return false;
    }

    private void moveAuto(Auto auto, boolean clickedFront) {
        if (game.isMovePossible(auto, clickedFront)) {
            auto.move(clickedFront);
            game.decrementScore();
            updateScore();
            updateView();

            if (game.checkWin()) {
                highScoreManager.writeScore(currentDifficulty, currentLevel, game.getScore());
                updateHighScoreDisplay();
                PopupWindow.showPopup("Congratulations!", String.valueOf(game.getScore()), view);
            }
        }
    }

    public void refreshView() {
        updateView();
    }

    private void updateView() {
        view.updateSpeelveld(game.getAutos());
    }

    public boolean isMovePossible(Auto auto, boolean forward) {
        return game.isMovePossible(auto, forward);
    }

    // Navigation methods
    public void goToHomeView() {
        HomeView homeView = new HomeView();
        Game model = new Game();
        be.kdg.project.view.beginScreen.HomePresenter presenter = new be.kdg.project.view.beginScreen.HomePresenter(
                model, homeView);
        presenter.addWindowEventHandlers();
        view.getScene().setRoot(homeView);
        homeView.getScene().getWindow().sizeToScene();
    }

    public void restartGame() {
        GameView newGameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter(newGameView, currentDifficulty, currentLevel);
        view.getScene().setRoot(newGameView);
        newGameView.getScene().getWindow().sizeToScene();
    }
}