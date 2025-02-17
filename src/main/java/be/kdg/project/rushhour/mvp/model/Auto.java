package be.kdg.project.rushhour.mvp.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Auto {
    private int xPos;
    private int yPos;
    private int lengte; // Lengte van de auto (2 of 3)
    private boolean isHorizontaal; // Of de auto horizontaal is
    private Color kleur; // Kleur van de auto

    public Auto(int xPos, int yPos, int lengte, boolean isHorizontaal, Color kleur) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lengte = lengte;
        this.isHorizontaal = isHorizontaal;
        this.kleur = kleur;
    }

    // Functie om de auto op het grid te plaatsen
    public void plaatsAutoInGrid(GridPane grid) {
        // Verwijder eerst de oude auto (indien deze al bestaat)
        grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null);

        // Plaats de rechthoeken van de auto op het grid
        for (int i = 0; i < lengte; i++) {
            Rectangle rect;
            if (isHorizontaal) {
                rect = new Rectangle(30, 30, kleur); // Horizontale rechthoek
                grid.add(rect, xPos + i, yPos); // Voeg toe aan het grid
            } else {
                rect = new Rectangle(30, 30, kleur); // Verticale rechthoek
                grid.add(rect, xPos, yPos + i); // Voeg toe aan het grid
            }
        }
    }

    // Setters en getters voor de posities en andere eigenschappen
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getLengte() {
        return lengte;
    }

    public boolean isHorizontaal() {
        return isHorizontaal;
    }
}
