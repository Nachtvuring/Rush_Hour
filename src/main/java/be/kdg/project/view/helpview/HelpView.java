package be.kdg.project.view.helpview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelpView extends VBox {
    private Button goBackButton;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        goBackButton = new Button("Go Back");
    }

    private void layoutNodes() {
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        Text helpText = new Text(
                "Hoe speel je Rush Hour?\n\nIn Rush Hour is het jouw doel om de rode auto uit de file te bevrijden. Het spel speelt zich af op een 6x6 raster vol voertuigen die de weg blokkeren. Je kunt deze auto's en vrachtwagens alleen vooruit of achteruit verplaatsen, afhankelijk van hun oorspronkelijke rijrichting. Door strategisch te schuiven, maak je een pad vrij zodat de rode auto de uitgang kan bereiken. Elk niveau wordt steeds uitdagender en vereist slim nadenken om de oplossing te vinden. Veel succes!");
        helpText.setWrappingWidth(300);

        this.getChildren().addAll(helpText, goBackButton);
    }

    public Button getGoBackButton() {
        return goBackButton;
    }
}