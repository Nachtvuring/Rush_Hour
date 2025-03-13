package be.kdg.project.view.aboutScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutUsView extends VBox {

    private Text aboutUsText;
    private Button goBackButton;

    public AboutUsView() {
        this.initialiseNodes();
        this.layoutNodes();

    }

    private void initialiseNodes() {
        aboutUsText = new Text(
                "Wij zijn Yannick en Lander, de makers van Rush Hour. Met dit project combineren we onze passie voor creativiteit en technologie. We werken samen om iets unieks en impactvols te creëren.");
        aboutUsText.setWrappingWidth(300);
        goBackButton = new Button("Go Back");
    }

    private void layoutNodes() {
        this.getChildren().addAll(aboutUsText, goBackButton);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

}