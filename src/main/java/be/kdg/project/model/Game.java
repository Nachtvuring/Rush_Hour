package be.kdg.project.model;

import be.kdg.project.view.PopupWindow;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Auto> autos;
    private final int gridSize = 6;

    public Game() {
        this.autos = new ArrayList<>();
        initializeAutos();
    }

    private void initializeAutos() {
        Auto rodeAuto = new Auto(0, 2, 2, true, Color.RED);
        Auto blauweAuto = new Auto(4, 0, 2, false, Color.BLUE);
        Auto groeneAuto = new Auto(3, 3, 3, true, Color.GREEN);
        Auto geleAuto = new Auto(0, 4, 2, false, Color.YELLOW);

        autos.add(rodeAuto);
        autos.add(blauweAuto);
        autos.add(groeneAuto);
        autos.add(geleAuto);
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public boolean isMovePossible(Auto auto, boolean forward) {
        int newX = auto.getxPos();
        int newY = auto.getyPos();

        if (auto.isHorizontaal()) {
            newX += forward ? 1 : -1;
        } else {
            newY += forward ? 1 : -1;
        }

        // Check boundaries
        if (newX < 0 || newY < 0 ||
                (auto.isHorizontaal() && newX + auto.getLengte() > gridSize) ||
                (!auto.isHorizontaal() && newY + auto.getLengte() > gridSize)) {
            return false;
        }

        // Check collision with other cars
        for (Auto other : autos) {
            if (other != auto && checkCollision(auto, other, newX, newY)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCollision(Auto movingAuto, Auto otherAuto, int newX, int newY) {
        int movingStartX = newX;
        int movingStartY = newY;
        int movingEndX = movingAuto.isHorizontaal() ? newX + movingAuto.getLengte() - 1 : newX;
        int movingEndY = movingAuto.isHorizontaal() ? newY : newY + movingAuto.getLengte() - 1;

        int otherStartX = otherAuto.getxPos();
        int otherStartY = otherAuto.getyPos();
        int otherEndX = otherAuto.isHorizontaal() ? otherStartX + otherAuto.getLengte() - 1 : otherStartX;
        int otherEndY = otherAuto.isHorizontaal() ? otherStartY : otherStartY + otherAuto.getLengte() - 1;

        return !(movingEndX < otherStartX || movingStartX > otherEndX ||
                movingEndY < otherStartY || movingStartY > otherEndY);
    }

    public boolean checkWin() {
        for (Auto auto : autos) {
            if (auto.getColor() == Color.RED) {
                return auto.getxPos() == 4 && auto.getyPos() == 2;
            }
        }
        return false;
    }
}