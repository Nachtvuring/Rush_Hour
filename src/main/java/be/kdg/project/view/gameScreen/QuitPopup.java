package be.kdg.project.view.gameScreen;

import be.kdg.project.view.SceneManager;
import be.kdg.project.view.beginScreen.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuitPopup {
    public static void display(SceneManager sceneManager) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Quit Game");

        Label messageLabel = new Label("Are you sure you want to quit?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            sceneManager.setScene(new Scene(new View(sceneManager), 800, 600));
            popupStage.close();
        });

        noButton.setOnAction(e -> popupStage.close());

        HBox buttonBox = new HBox(10, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, messageLabel, buttonBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}