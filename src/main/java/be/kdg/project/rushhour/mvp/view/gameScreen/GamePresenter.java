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
        Auto rodeAuto = new Auto(0, 2, 2, true, Color.RED); // Rode auto
        Auto blauweAuto = new Auto(2, 0, 2, false, Color.BLUE); // Blauwe auto

        autos.add(rodeAuto);
        autos.add(blauweAuto);

        for (Auto auto : autos) {
            new AutoHandler(view.getSpeelveld(), auto, this); // Koppel een AutoHandler aan elke auto
        }

        updateView();
    }

    public void updateView() {
        view.updateSpeelveld(autos); // Update het hele speelveld
    }
}