package be.kdg.project.rushhour.mvp.view.gameScreen;

import be.kdg.project.rushhour.mvp.model.Auto;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {
    private GameView view;
    private List<Auto> autos;

    public GamePresenter(GameView view) {
        this.view = view;
        this.autos = new ArrayList<>();
        initializeAutos();
        updateView();
    }

    private void initializeAutos() {
        Auto rodeAuto = new Auto(0, 2, 2, true, Color.RED);
        Auto blauweAuto = new Auto(2, 0, 2, false, Color.BLUE);

        autos.add(rodeAuto);
        autos.add(blauweAuto);

        for (Auto auto : autos) {
            new AutoHandler(view.getSpeelveld(), auto, this);
        }

        updateView();
    }

    public void updateView() {
        view.updateSpeelveld(autos);
    }

    public boolean isMovePossible(Auto auto, boolean forward) {
        int newX = auto.getxPos();
        int newY = auto.getyPos();

        if (forward) {
            if (auto.isHorizontaal()) {
                newX += auto.getLengte();
            } else {
                newY += auto.getLengte();
            }
        } else {
            if (auto.isHorizontaal()) {
                newX -= 1;
            } else {
                newY -= 1;
            }
        }

        if (newX < 0 || newX >= 6 || newY < 0 || newY >= 6) {
            return false;
        }

        for (Auto other : autos) {
            if (other != auto) {
                for (int i = 0; i < other.getLengte(); i++) {
                    int occupiedX = other.isHorizontaal() ? other.getxPos() + i : other.getxPos();
                    int occupiedY = other.isHorizontaal() ? other.getyPos() : other.getyPos() + i;

                    if (occupiedX == newX && occupiedY == newY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
