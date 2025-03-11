package be.kdg.project.model;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.ImageView;

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
        ImageView carImageView = auto.getNode();
        // First, configure the spans before adding to grid
        if (auto.isHorizontaal()) {
            GridPane.setColumnSpan(carImageView, auto.getLengte());
            GridPane.setRowSpan(carImageView, 1);
            speelveld.add(carImageView, auto.getxPos(), auto.getyPos());
        } else {
            GridPane.setColumnSpan(carImageView, 1);
            GridPane.setRowSpan(carImageView, auto.getLengte());
            speelveld.add(carImageView, auto.getxPos(), auto.getyPos());
        }
    }
}