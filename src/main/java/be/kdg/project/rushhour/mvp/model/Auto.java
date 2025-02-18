package be.kdg.project.rushhour.mvp.model;

import javafx.scene.paint.Color;

public class Auto {
    private int xPos;
    private int yPos;
    private int lengte;
    private boolean isHorizontaal;
    private Color kleur;

    public Auto(int xPos, int yPos, int lengte, boolean isHorizontaal, Color kleur) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lengte = lengte;
        this.isHorizontaal = isHorizontaal;
        this.kleur = kleur;
    }

    public boolean canMoveForward(int gridSize) {
        if (isHorizontaal) {
            return xPos + lengte < gridSize;
        } else {
            return yPos + lengte < gridSize;
        }
    }

    public boolean canMoveBackward() {
        if (isHorizontaal) {
            return xPos > 0;
        } else {
            return yPos > 0;
        }
    }

    public void moveForward() {
        if (isHorizontaal) {
            xPos++;
        } else {
            yPos++;
        }
    }

    public void moveBackward() {
        if (isHorizontaal) {
            xPos--;
        } else {
            yPos--;
        }
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

    public Color getKleur() {
        return kleur;
    }
}