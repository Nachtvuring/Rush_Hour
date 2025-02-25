package be.kdg.project.view;

import javafx.geometry.Pos;
import be.kdg.project.view.beginScreen.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {
    private static SceneManager sceneManager;

    public static void setSceneManager(SceneManager manager) {
        sceneManager = manager;
    }

    public static void showPopup(String title, String score, SceneManager manager) {
        setSceneManager(manager);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);
        popupStage.setMinWidth(250);

        String playerName = View.getPlayerName();
        Label label = new Label(String.format("Congratulations %s!\nYou got a score of %s points",
                playerName, score));

        Button closeButton = new Button("Back to menu");
        Button playAgainButton = new Button("Play again");

        closeButton.setOnAction(e -> {
            popupStage.close();
            if (sceneManager != null) {
                sceneManager.setScene(new Scene(new View(sceneManager), 800, 600));
            }
        });

        playAgainButton.setOnAction(e -> {
            popupStage.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgainButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}