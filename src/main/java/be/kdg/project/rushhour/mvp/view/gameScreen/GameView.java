package be.kdg.project.rushhour.mvp.view.gameScreen;

import be.kdg.project.rushhour.mvp.model.Auto;
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

public class GameView extends VBox {
    private SceneManager sceneManager;

    public GameView(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        // Maak het grid voor het speelveld
        GridPane grid = new GridPane();
        grid.setMaxWidth(300);
        grid.setMaxHeight(300);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-border-color: black; -fx-background-color: lightgray; -fx-border-width: 2px;");
        grid.setGridLinesVisible(true);

        // Voeg de rijen en kolommen toe aan het grid
        for (int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 6);
            grid.getColumnConstraints().add(column);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 6);
            grid.getRowConstraints().add(row);
        }

        // Maak een nieuwe Auto (bijvoorbeeld een horizontale auto van lengte 2)
        Auto auto = new Auto(0, 2, 2, true, Color.RED); // Positie (0, 0), lengte 2, horizontaal
        auto.plaatsAutoInGrid(grid); // Plaats de auto op het grid

        // UI-elementen zoals score en knoppen
        Label highScore = new Label("High Score");
        Label scoreLabel = new Label("Score");
        Button testButton = new Button("Test");
        testButton.setOnAction(e -> PopupWindow.showPopup("Belangrijke melding", "Dit is een custom popup!"));
        Button backButton = new Button("Quit");
        backButton.setOnAction(event -> goToMenu());

        grid.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(highScore, Pos.TOP_LEFT);
        BorderPane.setMargin(highScore, new Insets(10, 0, 0, 10));

        BorderPane.setAlignment(scoreLabel, Pos.TOP_CENTER);

        backButton.setOnAction(event -> goToMenu());
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10, 10, 0, 0));

        HBox topSection = new HBox();
        topSection.setPadding(new Insets(10, 10, 10, 10));
        topSection.setSpacing(200);
        topSection.setAlignment(Pos.TOP_CENTER);
        topSection.getChildren().addAll(highScore, scoreLabel, backButton);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        mainLayout.setCenter(grid);
        mainLayout.setBottom(testButton);

        this.getChildren().addAll(mainLayout);
        this.setAlignment(Pos.CENTER);
        grid.setPrefSize(300, 300);
    }

    private void goToMenu() {
        View menuView = new View(sceneManager);
        Scene menuScene = new Scene(menuView, 800, 600);
        sceneManager.setScene(menuScene);
    }
}
