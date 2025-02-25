package be.kdg.project.view.gameScreen;

import be.kdg.project.model.Auto;
import be.kdg.project.model.Game;
import be.kdg.project.view.PopupWindow;
import be.kdg.project.view.beginScreen.View;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class GamePresenter {
    private final Game game;
    private final GameView view;
    private Auto selectedAuto;

    public GamePresenter(GameView view) {
        this.game = new Game();
        this.view = view;
        this.view.updateSpeelveld(game.getAutos());
        updateScore();
        attachEventHandlers();
    }

    private void updateScore() {
        view.getScoreLabel().setText("Score: " + game.getScore());
    }

    private void attachEventHandlers() {
        view.getBackButton().setOnAction(event ->
                view.getSceneManager().setScene(new Scene(new View(view.getSceneManager()), 800, 600)));

        view.getSpeelveld().setOnMouseClicked(this::handleGridClick);
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
                clickedFront[0] = (clickX >= auto.getxPos() + auto.getLengte()/2);
                return true;
            }
        } else {
            if (clickX == auto.getxPos() && clickY >= auto.getyPos() && clickY < auto.getyPos() + auto.getLengte()) {
                clickedFront[0] = (clickY >= auto.getyPos() + auto.getLengte()/2);
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
                PopupWindow.showPopup("Congratulations!", String.valueOf(game.getScore()), view.getSceneManager());
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
}