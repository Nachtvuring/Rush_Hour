package be.kdg.project.model;

import be.kdg.project.view.beginScreen.View;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Auto> autos;
    private final int gridSize = 6;
    private int score;

    public Game() {
        this.autos = new ArrayList<>();
        this.score = 10000;
        if (View.getSelectedDifficulty().equals("Beginner")) {
            int selectedLevel = View.getChoiceBox().getValue();
            switch (selectedLevel) {
                case 1 -> loadAutosFromFile("easyLevel1.txt");
                case 2 -> loadAutosFromFile("easyLevel2.txt");
                case 3 -> loadAutosFromFile("easyLevel3.txt");
                default -> throw new RuntimeException("Invalid level selected");
            }
        } else if (View.getSelectedDifficulty().equals("Intermediate")) {
            int selectedLevel = View.getChoiceBox().getValue();
            switch (selectedLevel) {
                case 1 -> loadAutosFromFile("intermediateLevel1.txt");
                case 2 -> loadAutosFromFile("intermediateLevel2.txt");
                case 3 -> loadAutosFromFile("intermediateLevel3.txt");
                default -> throw new RuntimeException("Invalid level selected");
            }
        } else if (View.getSelectedDifficulty().equals("Advanced")) {
            int selectedLevel = View.getChoiceBox().getValue();
            switch (selectedLevel) {
                case 1 -> loadAutosFromFile("advancedLevel1.txt");
                case 2 -> loadAutosFromFile("advancedLevel2.txt");
                case 3 -> loadAutosFromFile("advancedLevel3.txt");
                default -> throw new RuntimeException("Invalid level selected");
            }
        } else if (View.getSelectedDifficulty().equals("Expert")) {
            int selectedLevel = View.getChoiceBox().getValue();
            switch (selectedLevel) {
                case 1 -> loadAutosFromFile("expertLevel1.txt");
                case 2 -> loadAutosFromFile("expertLevel2.txt");
                case 3 -> loadAutosFromFile("expertLevel3.txt");
                default -> throw new RuntimeException("Invalid level selected");
            }
        }
    }

    public void decrementScore() {
        this.score = Math.max(0, this.score - 100);
    }

    public int getScore() {
        return score;
    }

    private void loadAutosFromFile(String filename) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new RuntimeException("File not found: " + filename);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int xPos = Integer.parseInt(parts[0].trim());
                    int yPos = Integer.parseInt(parts[1].trim());
                    int length = Integer.parseInt(parts[2].trim());
                    boolean isHorizontal = Boolean.parseBoolean(parts[3].trim());
                    String imagePath = parts[4].trim();

                    autos.add(new Auto(xPos, yPos, length, isHorizontal, imagePath));
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }

            if (autos.isEmpty()) {
                throw new RuntimeException("No autos loaded from file: " + filename);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load autos from file: " + filename, e);
        }
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
//            if (((ImageView)auto.getNode()).getImage().getUrl().endsWith("carh.png")) {
                if (auto.getCarImage().getImage().getUrl().endsWith("carh.png")) {
                if (auto.getxPos() == 4 && auto.getyPos() == 2) {
                    HighScore highScore = new HighScore();
                    highScore.writeScore(View.getSelectedDifficulty(), View.getChoiceBox().getValue(), getScore());
                    return true;
                }
            }
        }
        return false;
    }
}