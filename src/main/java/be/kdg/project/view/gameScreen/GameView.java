package be.kdg.project.view.gameScreen;

import be.kdg.project.model.Auto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.List;

public class GameView extends VBox {
    private final GridPane speelveld;
    private final Button backButton;
    private final Label highScore;
    private final Label scoreLabel;
    private final HBox topBar;

    public GameView() {
        this.speelveld = createGrid();

        // Initialize UI components without business logic
        this.highScore = new Label("High Score: 0");
        this.scoreLabel = new Label("Score: 0");
        this.backButton = new Button("Quit");

        this.topBar = new HBox(20);
        this.topBar.setAlignment(Pos.CENTER);
        this.topBar.setPadding(new Insets(40));
        this.topBar.getChildren().addAll(scoreLabel, highScore, backButton);

        setSpacing(10);
        getChildren().addAll(topBar, speelveld);
        setAlignment(Pos.CENTER);
    }

    // Method to set a reference to the presenter
    public void setPresenter(GamePresenter presenter) {
        this.setUserData(presenter);
    }

    // Update UI methods - no business logic
    public void updateScoreDisplay(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateHighScoreDisplay(int highScoreValue) {
        highScore.setText("High Score: " + highScoreValue);
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

    public void plaatsAuto(Auto auto) {
        speelveld.add(auto.getNode(), auto.getxPos(), auto.getyPos());
    }

    public GridPane getSpeelveld() {
        return speelveld;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Label getHighScore() {
        return highScore;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }
}