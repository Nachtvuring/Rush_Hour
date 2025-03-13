package be.kdg.project.view.beginScreen;

import be.kdg.project.view.aboutScreen.AboutUsView;
import be.kdg.project.view.gameScreen.GamePresenter;
import be.kdg.project.view.gameScreen.GameView;
import be.kdg.project.view.helpview.HelpView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeView extends BorderPane {
    private static String selectedDifficulty = "Beginner";
    private static String playerName = "Player";

    private Button beginnerButton;
    private Button intermediateButton;
    private Button advancedButton;
    private Button expertButton;
    private Button playButton;
    private TextField textField;
    private Button aboutUsButton;
    private Button helpButton;
    private static ChoiceBox<Integer> choiceBox;

    public HomeView() {
        this.setPadding(new Insets(10));

        Text text = new Text("Rush Hour");

        textField = new TextField();
        textField.setPromptText("Player name");
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            playerName = newValue.isEmpty() ? "Player" : newValue;
        });

        Platform.runLater(() -> textField.getParent().requestFocus());

        beginnerButton = new Button("Beginner");
        intermediateButton = new Button("Intermediate");
        advancedButton = new Button("Advanced");
        expertButton = new Button("Expert");
        playButton = new Button("Play");

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(1, 2, 3);
        choiceBox.setValue(1);

        HBox buttonBox = new HBox(10, beginnerButton, intermediateButton, advancedButton, expertButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox textBox = new VBox(10, text, textField, buttonBox, choiceBox, playButton);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(200));

        aboutUsButton = new Button("About Us");
        helpButton = new Button("Help");

        HBox topRightButtons = new HBox(10, aboutUsButton, helpButton);
        topRightButtons.setAlignment(Pos.TOP_RIGHT);
        topRightButtons.setPadding(new Insets(10));

        BorderPane centerPane = new BorderPane();
        centerPane.setCenter(textBox);
        centerPane.setTop(topRightButtons);

        this.setCenter(centerPane);

        String selectedStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
        String defaultStyle = "";

        beginnerButton.setStyle(selectedStyle);
    }

    public void setSelectedDifficulty(String difficulty) {
        selectedDifficulty = difficulty;
        enableChoiceBox();
    }

    public void updateDifficultyButtons() {
        String selectedStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
        String defaultStyle = "";

        beginnerButton.setStyle(selectedDifficulty.equals("Beginner") ? selectedStyle : defaultStyle);
        intermediateButton.setStyle(selectedDifficulty.equals("Intermediate") ? selectedStyle : defaultStyle);
        advancedButton.setStyle(selectedDifficulty.equals("Advanced") ? selectedStyle : defaultStyle);
        expertButton.setStyle(selectedDifficulty.equals("Expert") ? selectedStyle : defaultStyle);
    }

    private void enableChoiceBox() {
        choiceBox.setDisable(false);
    }

    private void disableChoiceBox() {
        choiceBox.setDisable(true);
    }

    public static String getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void startGame() {
        GameView gameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter(gameView, selectedDifficulty, choiceBox.getValue());
        this.getScene().setRoot(gameView);
        gameView.getScene().getWindow().sizeToScene();
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getBeginnerButton() {
        return beginnerButton;
    }

    public Button getIntermediateButton() {
        return intermediateButton;
    }

    public Button getAdvancedButton() {
        return advancedButton;
    }

    public Button getExpertButton() {
        return expertButton;
    }

    public static ChoiceBox<Integer> getChoiceBox() {
        return choiceBox;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public TextField getTextField() {
        return textField;
    }

    public Button getAboutUsButton() {
        return aboutUsButton;
    }

    public Button getHelpButton() {
        return helpButton;
    }
}