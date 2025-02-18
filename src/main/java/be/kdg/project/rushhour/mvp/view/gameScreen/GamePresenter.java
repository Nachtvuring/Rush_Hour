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
        autos.add(new Auto(0, 2, 2, true, Color.RED)); // Rode auto
        autos.add(new Auto(2, 0, 2, false, Color.BLUE)); // Blauwe vrachtwagen
    }

    private void updateView() {
        for (Auto auto : autos) {
            view.plaatsAuto(auto);
        }
    }
}