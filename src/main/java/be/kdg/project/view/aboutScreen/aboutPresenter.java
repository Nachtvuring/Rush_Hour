package be.kdg.project.view.aboutScreen;

import be.kdg.project.model.Game;
import be.kdg.project.view.beginScreen.HomeView;
import be.kdg.project.view.beginScreen.HomePresenter;

public class aboutPresenter {
    private AboutUsView view;
    private Game model;

    public aboutPresenter(Game model, AboutUsView view) {
        this.view = view;
        this.model = model;
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
