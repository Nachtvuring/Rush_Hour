package be.kdg.project.view.helpview;

import be.kdg.project.model.Game;
import be.kdg.project.view.beginScreen.HomeView;
import be.kdg.project.view.beginScreen.HomePresenter;

public class HelpPresenter {
    private final Game model;
    private final HelpView view;

    public HelpPresenter(Game model, HelpView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getGoBackButton().setOnAction(event -> {
            HomeView homeView = new HomeView();
            HomePresenter homePresenter = new HomePresenter(model, homeView);
            homePresenter.addWindowEventHandlers();
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow().sizeToScene();
        });
    }
}
