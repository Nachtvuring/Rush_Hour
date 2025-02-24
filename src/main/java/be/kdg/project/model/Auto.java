package be.kdg.project.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Auto {
    private int xPos;
    private int yPos;
    private final int lengte;
    private final boolean horizontaal;
    private final Color color;
    private final Rectangle node;

    public Auto(int xPos, int yPos, int lengte, boolean horizontaal, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lengte = lengte;
        this.horizontaal = horizontaal;
        this.color = color;
        this.node = new Rectangle(50, 50, color); // Example size
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
        return horizontaal;
    }

    public Rectangle getNode() {
        return node;
    }

    public void move(boolean forward) {
        if (horizontaal) {
            xPos += forward ? 1 : -1;
        } else {
            yPos += forward ? 1 : -1;
        }
    }

    public Color getColor() {
        return color;
    }
}