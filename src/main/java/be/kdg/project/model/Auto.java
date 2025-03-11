package be.kdg.project.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Auto {
    private int xPos;
    private int yPos;
    private final int lengte;
    private final boolean horizontaal;
    private final ImageView node;

    public Auto(int xPos, int yPos, int lengte, boolean horizontaal, String imagePath) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lengte = lengte;
        this.horizontaal = horizontaal;
        Image carImage = new Image(getClass().getResource(imagePath).toExternalForm(),
                50, 50, false, true);  // Force image to be 50x50
        this.node = new ImageView(carImage);
        this.node.setPreserveRatio(false);
        this.node.setSmooth(true);
        if (horizontaal) {
            this.node.setFitWidth(50 * lengte);
            this.node.setFitHeight(50);
        } else {
            this.node.setFitWidth(50);
            this.node.setFitHeight(50 * lengte);
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
        return horizontaal;
    }

    public ImageView getNode() {
        return node;
    }

    public void move(boolean forward) {
        if (horizontaal) {
            xPos += forward ? 1 : -1;
        } else {
            yPos += forward ? 1 : -1;
        }
    }
}