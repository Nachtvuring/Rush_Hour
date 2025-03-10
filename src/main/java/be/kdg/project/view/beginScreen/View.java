package be.kdg.project.view.beginScreen;

import be.kdg.project.view.SceneManager;
import be.kdg.project.view.gameScreen.GamePresenter;
import be.kdg.project.view.gameScreen.GameView;
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

public class View extends BorderPane {
    private final SceneManager sceneManager;
    private static String selectedDifficulty = "Beginner";
    private static String playerName = "Player";

    private Button beginnerButton;
    private Button intermediateButton;
    private Button advancedButton;
    private Button expertButton;
    private Button playButton;
    private TextField textField;
    private static ChoiceBox<Integer> choiceBox;

    public View(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
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
        playButton.setOnAction(event -> startGame());

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(1, 2, 3);
        choiceBox.setValue(1);

        HBox buttonBox = new HBox(10, beginnerButton, intermediateButton, advancedButton, expertButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox textBox = new VBox(10, text, textField, buttonBox, choiceBox, playButton);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(200));

        Button aboutUsButton = new Button("About Us");
        Button helpButton = new Button("Help");

        aboutUsButton.setOnAction(e -> sceneManager.setScene(new Scene(new AboutUsView(sceneManager), 800, 600)));
        helpButton.setOnAction(e -> sceneManager.setScene(new Scene(new HelpView(sceneManager), 800, 600)));

        HBox topRightButtons = new HBox(10, aboutUsButton, helpButton);
        topRightButtons.setAlignment(Pos.TOP_RIGHT);
        topRightButtons.setPadding(new Insets(10));

        BorderPane centerPane = new BorderPane();
        centerPane.setCenter(textBox);
        centerPane.setTop(topRightButtons);

        this.setCenter(centerPane);

        String selectedStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
        String defaultStyle = "";

        beginnerButton.setOnAction(e -> {
            selectedDifficulty = "Beginner";
            enableChoiceBox();
            beginnerButton.setStyle(selectedStyle);
            intermediateButton.setStyle(defaultStyle);
            advancedButton.setStyle(defaultStyle);
            expertButton.setStyle(defaultStyle);
        });
        intermediateButton.setOnAction(e -> {
            selectedDifficulty = "Intermediate";
            enableChoiceBox();
            beginnerButton.setStyle(defaultStyle);
            intermediateButton.setStyle(selectedStyle);
            advancedButton.setStyle(defaultStyle);
            expertButton.setStyle(defaultStyle);
        });
        advancedButton.setOnAction(e -> {
            selectedDifficulty = "Advanced";
            enableChoiceBox();
            beginnerButton.setStyle(defaultStyle);
            intermediateButton.setStyle(defaultStyle);
            advancedButton.setStyle(selectedStyle);
            expertButton.setStyle(defaultStyle);
        });
        expertButton.setOnAction(e -> {
            selectedDifficulty = "Expert";
            enableChoiceBox();
            beginnerButton.setStyle(defaultStyle);
            intermediateButton.setStyle(defaultStyle);
            advancedButton.setStyle(defaultStyle);
            expertButton.setStyle(selectedStyle);
        });
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

    private void startGame() {
        GameView gameview = new GameView(sceneManager);
        new GamePresenter(gameview);
        Scene gameScene = new Scene(gameview, 800, 600);
        sceneManager.setScene(gameScene);
    }

    public Button getPlayButton() {
        return playButton;
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
}