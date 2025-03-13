package be.kdg.project;

import be.kdg.project.view.SceneManager;
import be.kdg.project.view.beginScreen.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.setScene(new Scene(new View(sceneManager), 800, 600));
        primaryStage.setTitle("Rush Hour");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

