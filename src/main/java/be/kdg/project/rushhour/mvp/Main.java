package be.kdg.project.rushhour.mvp;

import be.kdg.project.rushhour.mvp.view.SceneManager;
import be.kdg.project.rushhour.mvp.view.beginScreen.View;
import be.kdg.project.rushhour.mvp.view.gameScreen.AutoHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);

        View mainMenu = new View(sceneManager);
        Scene mainScene = new Scene(mainMenu, 800, 600);

        sceneManager.setScene(mainScene);

        primaryStage.setTitle("Rush Hour");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//joehopelijkziegijdees