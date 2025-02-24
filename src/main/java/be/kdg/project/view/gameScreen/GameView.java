package be.kdg.project.view.gameScreen;

import be.kdg.project.view.beginScreen.View;
import be.kdg.project.model.Auto;
import be.kdg.project.view.PopupWindow;
import be.kdg.project.view.SceneManager;
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
    private final Button testButton;
    private final Label highScore;
    private final Label scoreLabel;

    public GameView(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.speelveld = createGrid();
        this.highScore = new Label("High Score");
        this.scoreLabel = new Label("Score");
        this.testButton = new Button("Test");
        this.backButton = new Button("Quit");

        initializeLayout();
        new GamePresenter(this);
    }

    private void initializeLayout() {
        testButton.setOnAction(e -> PopupWindow.showPopup("Test", "This is a test popup", sceneManager));
        backButton.setOnAction(event -> goToMenu());

        HBox topSection = new HBox(200, highScore, scoreLabel, backButton);
        topSection.setPadding(new Insets(10));
        topSection.setAlignment(Pos.TOP_CENTER);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        mainLayout.setCenter(speelveld);
        mainLayout.setBottom(testButton);

        this.getChildren().addAll(mainLayout);
        this.setAlignment(Pos.CENTER);
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

    public Button getTestButton() {
        return testButton;
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