package be.kdg.project.rushhour.mvp.view.beginScreen;

import be.kdg.project.rushhour.mvp.model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Presenter {
    private Game model;
    private View view;

    public Presenter(Game model, View view) {
        this.model = model;
        this.view = view;

        view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Game gestart door speler: " + view.getTextField().getText());
            }
        });
    }
}

