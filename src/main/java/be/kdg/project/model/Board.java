package be.kdg.project.model;

import be.kdg.project.model.Auto;
import be.kdg.project.view.PopupWindow;
import be.kdg.project.view.SceneManager;
import be.kdg.project.view.beginScreen.View;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Board {
    private GridPane speelveld;

    public Board() {
        this.speelveld = createGrid();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setMaxHeight(300);
        grid.setMaxWidth(300);
        grid.setStyle("-fx-border-color: black; -fx-background-color: lightgray; -fx-border-width: 2px;");
        grid.setGridLinesVisible(true);

        for (int i = 0; i < 6; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50));
            grid.getRowConstraints().add(new RowConstraints(50));
        }
        return grid;
    }

    public void updateSpeelveld(List<Auto> autos) {
        speelveld.getChildren().clear();
        for (Auto auto : autos) {
            plaatsAuto(auto);
        }
    }

    private void plaatsAuto(Auto auto) {
        for (int i = 0; i < auto.getLengte(); i++) {
            Rectangle rect = new Rectangle(50, 50, auto.getColor());
            if (auto.isHorizontaal()) {
                speelveld.add(rect, auto.getxPos() + i, auto.getyPos());
            } else {
                speelveld.add(rect, auto.getxPos(), auto.getyPos() + i);
            }
        }
    }
}