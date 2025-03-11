package be.kdg.project.model;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.ImageView;

import java.util.List;

public class Board {
    private GridPane speelveld;
    private final List<Auto> autos;

    public Board(List<Auto> autos) {
        this.autos = autos;
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

    private void handleAutoClick(Auto auto, MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        if (source == auto.getFrontClickArea()) {
            auto.move(true);  // Move forward
        } else if (source == auto.getBackClickArea()) {
            auto.move(false); // Move backward
        }
        updateSpeelveld(autos); // You'll need to make autos accessible
    }


    private void plaatsAuto(Auto auto) {
        if (auto.isHorizontaal()) {
            // Place main car image
            speelveld.add(auto.getCarImage(), auto.getxPos(), auto.getyPos());
            // Place click areas
            speelveld.add(auto.getFrontClickArea(), auto.getxPos() + auto.getLengte() - 1, auto.getyPos());
            speelveld.add(auto.getBackClickArea(), auto.getxPos(), auto.getyPos());
        } else {
            int adjustedY = auto.getyPos() - (auto.getLengte() - 1);
            // Place main car image
            speelveld.add(auto.getCarImage(), auto.getxPos(), adjustedY);
            // Place click areas
            speelveld.add(auto.getFrontClickArea(), auto.getxPos(), adjustedY);
            speelveld.add(auto.getBackClickArea(), auto.getxPos(), auto.getyPos());
        }

        // Set click handlers
        auto.setOnMouseClicked(event -> handleAutoClick(auto, event));
    }
}