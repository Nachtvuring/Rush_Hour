package be.kdg.project.view.gameScreen;

import be.kdg.project.model.Auto;
import be.kdg.project.model.Game;
import be.kdg.project.view.PopupWindow;
import be.kdg.project.view.beginScreen.View;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class GamePresenter {
    private final GameView view;
    private final Game game;

    public GamePresenter(GameView view) {
        this.view = view;
        this.game = new Game();
        attachEventHandlers();
        updateView();
    }

    public boolean isMovePossible(Auto auto, boolean forward) {
        return game.isMovePossible(auto, forward);
    }

    public void updateView() {
        view.updateSpeelveld(game.getAutos());
    }

    private void attachEventHandlers() {
        view.getBackButton().setOnAction(event ->
                view.getSceneManager().setScene(new Scene(new View(view.getSceneManager()), 800, 600)));

        view.getTestButton().setOnAction(event ->
                PopupWindow.showPopup("Belangrijke melding", "Dit is een custom popup!", view.getSceneManager()));

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
            updateView();

            if (game.checkWin()) {
                PopupWindow.showPopup("Gefeliciteerd!", "Je hebt het level gehaald!", view.getSceneManager());
            }
        }
    }
}