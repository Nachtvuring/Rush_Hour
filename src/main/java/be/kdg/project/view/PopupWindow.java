package be.kdg.project.view;

import be.kdg.project.view.beginScreen.HomeView;
import be.kdg.project.view.gameScreen.GamePresenter;
import be.kdg.project.view.gameScreen.GameView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {
    public static void showPopup(String title, String score, GameView gameView) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);
        popupStage.setMinWidth(250);

        String playerName = HomeView.getPlayerName();
        Label label = new Label(String.format("Congratulations %s!\nYou got a score of %s points",
                playerName, score));

        Button closeButton = new Button("Back to menu");
        Button playAgainButton = new Button("Play again");

        // Get the presenter from the view
        GamePresenter presenter = (GamePresenter) gameView.getUserData();

        closeButton.setOnAction(e -> {
            popupStage.close();
            presenter.goToHomeView();
        });

        playAgainButton.setOnAction(e -> {
            popupStage.close();
            presenter.restartGame();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgainButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        popupStage.setScene(new javafx.scene.Scene(layout, 300, 150));
        popupStage.showAndWait();
    }
}