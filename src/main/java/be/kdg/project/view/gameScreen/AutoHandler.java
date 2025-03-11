package be.kdg.project.view.gameScreen;

import be.kdg.project.model.Auto;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AutoHandler {
    private final GridPane speelveld;
    private final Auto auto;
    private final GamePresenter presenter;

    public AutoHandler(GridPane speelveld, Auto auto, GamePresenter presenter) {
        this.speelveld = speelveld;
        this.auto = auto;
        this.presenter = presenter;
        attachEventHandlers();
        addAutoToGrid();
    }

    private void attachEventHandlers() {
        auto.getNode().setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        if (presenter.isMovePossible(auto, true)) {
            auto.move(true);
        } else if (presenter.isMovePossible(auto, false)) {
            auto.move(false);
        }
        presenter.refreshView();
    }

    private void addAutoToGrid() {
        if (!speelveld.getChildren().contains(auto.getNode())) {
            speelveld.add(auto.getNode(), auto.getxPos(), auto.getyPos());
        }
    }
}