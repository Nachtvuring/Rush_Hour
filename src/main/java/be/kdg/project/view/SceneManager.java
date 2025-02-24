package be.kdg.project.view;

import javafx.stage.Stage;
import javafx.scene.Scene;

public class SceneManager {
    private final Stage mainStage;

    public SceneManager(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setScene(Scene scene) {
        mainStage.setScene(scene);
    }
}