package be.kdg.project.rushhour.mvp.view.gameScreen;

import be.kdg.project.rushhour.mvp.model.Auto;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class AutoHandler {
    private GridPane speelveld;
    private Auto auto;
    private GamePresenter presenter;

    public AutoHandler(GridPane speelveld, Auto auto, GamePresenter presenter) {
        this.speelveld = speelveld;
        this.auto = auto;
        this.presenter = presenter;
        speelveld.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        int clickedX = (int) event.getX() / 50;
        int clickedY = (int) event.getY() / 50;

        if (isClickedOnFront(clickedX, clickedY) && canMoveForward()) {
            auto.moveForward();
            presenter.updateView();
        } else if (isClickedOnBack(clickedX, clickedY) && canMoveBackward()) {
            auto.moveBackward();
            presenter.updateView();
        }
    }

    private boolean isClickedOnFront(int x, int y) {
        if (auto.isHorizontaal()) {
            return x == auto.getxPos() + auto.getLengte() - 1 && y == auto.getyPos();
        } else {
            return y == auto.getyPos() + auto.getLengte() - 1 && x == auto.getxPos();
        }
    }

    private boolean isClickedOnBack(int x, int y) {
        if (auto.isHorizontaal()) {
            return x == auto.getxPos() && y == auto.getyPos();
        } else {
            return y == auto.getyPos() && x == auto.getxPos();
        }
    }

    private boolean canMoveForward() {
        return presenter.isMovePossible(auto, true);
    }

    private boolean canMoveBackward() {
        return presenter.isMovePossible(auto, false);
    }
}
