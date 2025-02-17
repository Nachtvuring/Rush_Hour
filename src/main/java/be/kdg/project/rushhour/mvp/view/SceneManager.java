package be.kdg.project.rushhour.mvp.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}
