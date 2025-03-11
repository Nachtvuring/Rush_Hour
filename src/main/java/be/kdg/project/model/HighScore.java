package be.kdg.project.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class HighScore {
    private static final String FILE_PATH = Paths.get("src", "main", "resources", "highscores.txt").toString();

    public void writeScore(String difficulty, int level, int score) {
        int currentHighScore = getTopScore(difficulty, level);
        if (score > currentHighScore) {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (!(parts[0].equals(difficulty) && Integer.parseInt(parts[1]) == level)) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading score from file: " + e.getMessage());
            }

            lines.add(difficulty + "," + level + "," + score);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing score to file: " + e.getMessage());
            }
        }
    }

    public int getTopScore(String difficulty, int level) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(difficulty) && Integer.parseInt(parts[1]) == level) {
                    return Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading score from file: " + e.getMessage());
        }
        return 0;
    }
}