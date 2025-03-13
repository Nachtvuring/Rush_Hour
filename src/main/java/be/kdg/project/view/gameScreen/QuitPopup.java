package be.kdg.project.view.gameScreen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuitPopup {
    public static void display(GameView gameView) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Quit Game");
        popupStage.setMinWidth(250);

        Label label = new Label("Are you sure you want to quit?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            popupStage.close();
            // Get the presenter through a new method in GameView
            GamePresenter presenter = (GamePresenter) gameView.getUserData();
            presenter.goToHomeView();
        });

        noButton.setOnAction(e -> popupStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        popupStage.setScene(new Scene(layout, 250, 150));
        popupStage.showAndWait();
    }
}