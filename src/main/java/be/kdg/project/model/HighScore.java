package be.kdg.project.model;

import java.io.*;
import java.util.*;

public class HighScore {
    private static final String HIGHSCORE_FILE = "highscores.txt";
    private final Map<String, TreeMap<Integer, String>> highScores;

    public HighScore() {
        this.highScores = new HashMap<>();
        loadHighScores();
    }

    public void addScore(String playerName, int score, String difficulty, int cardNumber) {
        String key = difficulty + "_" + cardNumber;
        highScores.putIfAbsent(key, new TreeMap<>(Collections.reverseOrder()));
        highScores.get(key).put(score, playerName);
        saveHighScores();
    }

    public String getTopScore(String difficulty, int cardNumber) {
        String key = difficulty + "_" + cardNumber;
        if (!highScores.containsKey(key) || highScores.get(key).isEmpty()) {
            return "No scores yet";
        }
        Map.Entry<Integer, String> topEntry = highScores.get(key).firstEntry();
        return String.format("%d by %s", topEntry.getKey(), topEntry.getValue());
    }

    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String key = parts[0];
                String playerName = parts[1];
                int score = Integer.parseInt(parts[2]);

                highScores.putIfAbsent(key, new TreeMap<>(Collections.reverseOrder()));
                highScores.get(key).put(score, playerName);
            }
        } catch (IOException ignored) {}
    }

    private void saveHighScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
            for (Map.Entry<String, TreeMap<Integer, String>> levelEntry : highScores.entrySet()) {
                for (Map.Entry<Integer, String> scoreEntry : levelEntry.getValue().entrySet()) {
                    writer.println(levelEntry.getKey() + "|" + scoreEntry.getValue() + "|" + scoreEntry.getKey());
                }
            }
        } catch (IOException ignored) {}
    }
}