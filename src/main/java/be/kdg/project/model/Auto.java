package be.kdg.project.model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Auto {
    private int xPos;
    private int yPos;
    private final int lengte;
    private final boolean horizontaal;
    private final ImageView carImage;
    private final ImageView frontClickArea;
    private final ImageView backClickArea;
    private final Node node;
    private final StackPane clickableCar;

    public Auto(int xPos, int yPos, int lengte, boolean horizontaal, String imagePath) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lengte = lengte;
        this.horizontaal = horizontaal;

        // Main car image
        this.carImage = new ImageView(new Image(imagePath));
        carImage.setPreserveRatio(true);

        // Create transparent click areas
        this.frontClickArea = new ImageView();
        this.backClickArea = new ImageView();

        // Set sizes based on orientation
        if (horizontaal) {
            carImage.setFitWidth(50 * lengte);
            carImage.setFitHeight(50);
            frontClickArea.setFitWidth(50);
            frontClickArea.setFitHeight(50);
            backClickArea.setFitWidth(50);
            backClickArea.setFitHeight(50);

            // Position click areas for horizontal car
            frontClickArea.setTranslateX((lengte - 1) * 25);
            backClickArea.setTranslateX(-(lengte - 1) * 25);

            // Position car image at center
            carImage.setTranslateX(0);
        } else {
            carImage.setFitWidth(50);
            carImage.setFitHeight(50 * lengte);
            frontClickArea.setFitWidth(50);
            frontClickArea.setFitHeight(50);
            backClickArea.setFitWidth(50);
            backClickArea.setFitHeight(50);

            // Position click areas for vertical car
            frontClickArea.setTranslateY(0);
            backClickArea.setTranslateY((lengte - 1) * 50);

            // Center the car image at its starting point
            carImage.setTranslateY((lengte - 1) * 25);
        }

        // Stack all components correctly
        this.clickableCar = new StackPane(frontClickArea, backClickArea, carImage);
        this.node = clickableCar;

        // Debug visualization
        frontClickArea.setStyle("-fx-background-color: rgba(255,0,0,0.3);");
        backClickArea.setStyle("-fx-background-color: rgba(0,255,0,0.3);");
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> eventHandler) {
        frontClickArea.setOnMouseClicked(eventHandler);
        backClickArea.setOnMouseClicked(eventHandler);
    }

    public ImageView getCarImage() {
        return carImage;
    }

    public ImageView getFrontClickArea() {
        return frontClickArea;
    }

    public ImageView getBackClickArea() {
        return backClickArea;
    }

    public Node getNode() {
        return node;
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

    public boolean move(boolean forward) {
        if (forward) {
            if (horizontaal) {
                xPos++;
            } else {
                yPos++;
            }
        } else {
            if (horizontaal) {
                xPos--;
            } else {
                yPos--;
            }
        }
        return true;
    }
}