package be.kdg.project.rushhour.mvp.view.gameScreen;

import be.kdg.project.rushhour.mvp.model.Auto;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class AutoHandler {
    private GridPane speelveld;
    private Auto auto;
    private List<Rectangle> rects;
    private GamePresenter presenter;

    public AutoHandler(GridPane speelveld, Auto auto, GamePresenter presenter) {
        this.speelveld = speelveld;
        this.auto = auto;
        this.presenter = presenter;
        this.rects = new ArrayList<>();
        speelveld.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        int clickedX = (int) event.getX() / 50;
        int clickedY = (int) event.getY() / 50;

        if (isClickedOnFront(clickedX, clickedY)) {
            moveAutoForward();
        } else if (isClickedOnBack(clickedX, clickedY)) {
            moveAutoBackward();
        }
    }

    private void tekenAuto() {
        verwijderAutoVanGrid();
        rects.clear();

        for (int i = 0; i < auto.getLengte(); i++) {
            Rectangle rect = new Rectangle(50, 50, auto.getKleur());
            rects.add(rect);

            if (auto.isHorizontaal()) {
                speelveld.add(rect, auto.getxPos() + i, auto.getyPos());
            } else {
                speelveld.add(rect, auto.getxPos(), auto.getyPos() + i);
            }
        }
    }

    private boolean isClickedOnFront(int x, int y) {
        return auto.isHorizontaal()
                ? x == auto.getxPos() + auto.getLengte() - 1 && y == auto.getyPos()
                : y == auto.getyPos() + auto.getLengte() - 1 && x == auto.getxPos();
    }

    private boolean isClickedOnBack(int x, int y) {
        return auto.isHorizontaal()
                ? x == auto.getxPos() && y == auto.getyPos()
                : y == auto.getyPos() && x == auto.getxPos();
    }

    private void moveAutoForward() {
        if (auto.canMoveForward(6)) {
            auto.moveForward();
            presenter.updateView(); // Update het hele speelveld via de GamePresenter
        }
    }

    private void moveAutoBackward() {
        if (auto.canMoveBackward()) {
            auto.moveBackward();
            presenter.updateView(); // Update het hele speelveld via de GamePresenter
        }
    }

    private void verwijderAutoVanGrid() {
        List<Node> teVerwijderen = new ArrayList<>(rects);
        speelveld.getChildren().removeAll(teVerwijderen);
    }
}