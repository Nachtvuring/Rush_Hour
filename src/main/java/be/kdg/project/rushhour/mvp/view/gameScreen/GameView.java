package be.kdg.project.rushhour.mvp.view.gameScreen;

import be.kdg.project.rushhour.mvp.model.Auto;
import be.kdg.project.rushhour.mvp.model.Game;
import be.kdg.project.rushhour.mvp.view.PopupWindow;
import be.kdg.project.rushhour.mvp.view.SceneManager;
import be.kdg.project.rushhour.mvp.view.beginScreen.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView extends VBox {
    private SceneManager sceneManager;
    private GridPane speelveld;

    public GameView(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.speelveld = createGrid();
        new GamePresenter(this);

        Label highScore = new Label("High Score");
        Label scoreLabel = new Label("Score");
        Button testButton = new Button("Test");
        testButton.setOnAction(e -> PopupWindow.showPopup("Belangrijke melding", "Dit is een custom popup!"));
        Button backButton = new Button("Quit");
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

    public void plaatsAuto(Auto auto) {
        for (int i = 0; i < auto.getLengte(); i++) {
            Rectangle rect = new Rectangle(50, 50, auto.getKleur());
            if (auto.isHorizontaal()) {
                speelveld.add(rect, auto.getxPos() + i, auto.getyPos());
            } else {
                speelveld.add(rect, auto.getxPos(), auto.getyPos() + i);
            }
        }
        new AutoHandler(speelveld, auto);
    }

    public GridPane getSpeelveld() {
        return speelveld;
    }

    private void goToMenu() {
        sceneManager.setScene(new Scene(new View(sceneManager), 800, 600));
    }
}