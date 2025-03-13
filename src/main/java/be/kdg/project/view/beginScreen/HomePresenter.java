package be.kdg.project.view.beginScreen;

import be.kdg.project.model.Game;
import be.kdg.project.view.aboutScreen.AboutUsView;
import be.kdg.project.view.aboutScreen.aboutPresenter;
import be.kdg.project.view.helpview.HelpView;
import be.kdg.project.view.helpview.HelpPresenter;

public class HomePresenter {
    private final Game model;
    private final HomeView view;

    public HomePresenter(Game model, HomeView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        // Handle play button click
        view.getPlayButton().setOnAction(event -> {
            // Get the selected difficulty and card number
            String difficulty = view.getSelectedDifficulty();
            int cardNumber = view.getChoiceBox().getValue();

            // Initialize the game with the selected options
            model.initializeGame(difficulty, cardNumber);

            // Start the game
            view.startGame();
        });

        // Handle difficulty button clicks
        view.getBeginnerButton().setOnAction(event -> {
            view.setSelectedDifficulty("Beginner");
            view.updateDifficultyButtons();
        });

        view.getIntermediateButton().setOnAction(event -> {
            view.setSelectedDifficulty("Intermediate");
            view.updateDifficultyButtons();
        });

        view.getAdvancedButton().setOnAction(event -> {
            view.setSelectedDifficulty("Advanced");
            view.updateDifficultyButtons();
        });

        view.getExpertButton().setOnAction(event -> {
            view.setSelectedDifficulty("Expert");
            view.updateDifficultyButtons();
        });

        // Handle About Us button
        view.getAboutUsButton().setOnAction(event -> {
            AboutUsView aboutUsView = new AboutUsView();
            aboutPresenter aboutPresenter = new aboutPresenter(model, aboutUsView);
            view.getScene().setRoot(aboutUsView);
            aboutUsView.getScene().getWindow().sizeToScene();
        });

        // Handle Help button
        view.getHelpButton().setOnAction(event -> {
            HelpView helpView = new HelpView();
            HelpPresenter helpPresenter = new HelpPresenter(model, helpView);
            view.getScene().setRoot(helpView);
            helpView.getScene().getWindow().sizeToScene();
        });
    }

    public void addWindowEventHandlers() {
        // Add any window-specific event handlers here
    }
}
