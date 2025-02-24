package be.kdg.project.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {

    public static void showPopup(String title, String message) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Blokkeert interactie met hoofdvenster
        popupStage.setTitle(title);
        popupStage.setMinWidth(250);

        Label label = new Label(message);
        Button closeButton = new Button("Sluiten");
        closeButton.setOnAction(event -> popupStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);
        popupStage.showAndWait(); // Wacht tot popup gesloten wordt
    }
}
