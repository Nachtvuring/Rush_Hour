package be.kdg.project.view.beginScreen;

import be.kdg.project.view.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutUsView extends VBox {
    public AboutUsView(SceneManager sceneManager) {
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        Text aboutUsText = new Text("Wij zijn Yannick en Lander, de makers van Rush Hour. Met dit project combineren we onze passie voor creativiteit en technologie. We werken samen om iets unieks en impactvols te creëren.");
        aboutUsText.setWrappingWidth(300);

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> sceneManager.setScene(new Scene(new View(sceneManager), 800, 600)));

        this.getChildren().addAll(aboutUsText, goBackButton);
    }
}