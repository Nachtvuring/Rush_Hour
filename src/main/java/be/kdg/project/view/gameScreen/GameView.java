package be.kdg.project.view.gameScreen;

import be.kdg.project.model.HighScore;
import be.kdg.project.view.SceneManager;
import be.kdg.project.view.beginScreen.View;
import be.kdg.project.model.Auto;
import be.kdg.project.view.PopupWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class GameView extends VBox {
    private final SceneManager sceneManager;
    private final GridPane speelveld;
    private final Button backButton;
    private final Label highScore;
    private final Label scoreLabel;
    private final HighScore highScoreManager;
    private final HBox topBar;

    public GameView(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.speelveld = createGrid();
        this.highScoreManager = new HighScore();

        String currentDifficulty = View.getSelectedDifficulty();
        int currentCard = View.getChoiceBox().getValue();
        this.highScore = new Label("High Score: " + highScoreManager.getTopScore(currentDifficulty, currentCard));
        this.highScore.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        this.scoreLabel = new Label("Score: 0");
        this.scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        this.backButton = new Button("Quit");
        this.backButton.setOnAction(e -> QuitPopup.display(sceneManager));

        this.topBar = new HBox(20);
        this.topBar.setAlignment(Pos.CENTER);
        this.topBar.setPadding(new Insets(10));
        this.topBar.getChildren().addAll(scoreLabel, highScore, backButton);

        setSpacing(10);
        getChildren().addAll(topBar, speelveld);
        setAlignment(Pos.CENTER);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
        if (score > 0) {
            String currentDifficulty = View.getSelectedDifficulty();
            int currentCard = View.getChoiceBox().getValue();
            highScore.setText("High Score: " +
                    highScoreManager.getTopScore(currentDifficulty, currentCard));
        }
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
        for (int i = 0; i < auto.getLengte(); i++) {
            Rectangle rect = new Rectangle(50, 50, auto.getColor());
            if (auto.isHorizontaal()) {
                speelveld.add(rect, auto.getxPos() + i, auto.getyPos());
            } else {
                speelveld.add(rect, auto.getxPos(), auto.getyPos() + i);
            }
        }
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

    private void goToMenu() {
        sceneManager.setScene(new Scene(new View(sceneManager), 800, 600));
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}