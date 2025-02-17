package be.kdg.project.rushhour.mvp.view.gameScreen;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import be.kdg.project.rushhour.mvp.model.Auto;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AutoHandler {

    private GridPane speelveld; // Jouw GridPane waar de auto geplaatst is
    private Auto auto; // De auto die je wilt verplaatsen
    private boolean isAutoGemaakt = false; // Controleer of de auto al gemaakt is

    public AutoHandler(GridPane speelveld) {
        this.speelveld = speelveld;

        // Maak een rectangle voor de eerste positie (0, 0)
        Rectangle eersteVakje = new Rectangle(30, 30, Color.RED); // Stel de grootte in en kleur het vakje
        speelveld.add(eersteVakje, 0, 0); // Voeg het rectangle toe op de eerste positie van het grid

        // Event handler voor het klikken op het grid
        speelveld.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int clickedX = (int) event.getX() / 30; // Stel dat de grootte van een vakje 30 is
                int clickedY = (int) event.getY() / 30;

                // Als de auto nog niet gemaakt is, maak de auto aan de geklikte positie
                if (!isAutoGemaakt) {
                    // Maak een nieuwe auto op de geklikte positie
                    auto = new Auto(clickedX, clickedY, 2, true, Color.RED); // Stel een standaard auto in (bijv. horizontaal, lengte 2)
                    auto.plaatsAutoInGrid(speelveld); // Plaats de auto op het grid
                    isAutoGemaakt = true; // Markeer dat de auto is gemaakt
                } else {
                    // Als de auto al bestaat, verplaats de auto naar de nieuwe locatie
                    if (isMoveValid(clickedX, clickedY)) {
                        auto.setxPos(clickedX);
                        auto.setyPos(clickedY);

                        // Verwijder oude auto en plaats de nieuwe auto op het grid
                        speelveld.getChildren().clear(); // Verwijder de oude auto uit het grid
                        auto.plaatsAutoInGrid(speelveld); // Plaats de auto op de nieuwe positie
                    }
                }
            }
        });
    }

    private boolean isMoveValid(int x, int y) {
        // Controleer of de verplaatsing binnen het grid past
        return x >= 0 && x < 6 && y >= 0 && y < 6; // Veronderstel dat het grid 6x6 is
    }
}
