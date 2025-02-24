package be.kdg.project.view.beginScreen;

import be.kdg.project.view.SceneManager;
import be.kdg.project.view.gameScreen.GamePresenter;
import be.kdg.project.view.gameScreen.GameView;
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

    private SceneManager sceneManager;

    private Button beginnerButton;
    private Button intermediateButton;
    private Button advancedButton;
    private Button expertButton;
    private Button playButton;
    private TextField textField;
    private ChoiceBox<Integer> choiceBox;

    public View(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.setPadding(new Insets(10));

        Text text = new Text("Rush Hour");

        textField = new TextField();
        textField.setPromptText("Player name");
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);

        beginnerButton = new Button("Beginner");
        intermediateButton = new Button("Intermediate");
        advancedButton = new Button("Advanced");
        expertButton = new Button("Expert");
        playButton = new Button("Play");
        playButton.setOnAction(event -> startGame());

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
        choiceBox.setValue(1);

        HBox buttonBox = new HBox(10, beginnerButton, intermediateButton, advancedButton, expertButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox textBox = new VBox(10, text, textField, buttonBox, choiceBox, playButton);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(50));

        this.setTop(textBox);
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

    public ChoiceBox<Integer> getChoiceBox() {
        return choiceBox;
    }

    public TextField getTextField() {
        return textField;
    }
}
