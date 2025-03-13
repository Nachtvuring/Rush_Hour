package be.kdg.project;

import be.kdg.project.model.Game;
import be.kdg.project.view.beginScreen.HomeView;
import be.kdg.project.view.beginScreen.HomePresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize the view first
        HomeView view = new HomeView();

        // Initialize the model
        Game model = new Game();

        // Initialize the presenter with both model and view
        HomePresenter presenter = new HomePresenter(model, view);

        // Set up the scene
        presenter.addWindowEventHandlers();
        primaryStage.setScene(new Scene(view, 800, 600));
        primaryStage.setTitle("Rush Hour");

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
