package com.ldts.crystalclash.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ScoresMenu {
    private List<ScoreEntry> entries;
    private final String filePath;

    public ScoresMenu() throws IOException {
        this.filePath = System.getProperty("user.dir") + "/src/main/scores/scores.txt";
        this.entries = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                readEntries(br);
                sortEntries();
            }
        }
    }

    public ScoresMenu(int newScore) throws IOException {
        this();
        String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        addScore(newScore, currentDate);
    }

    private void readEntries(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            try {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int points = Integer.parseInt(parts[0].trim());
                    String date = parts[1].trim();
                    entries.add(new ScoreEntry(points, date));
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid score format: " + line);
            }
        }
    }


    private void sortEntries() {
        entries.sort(Comparator.comparingInt(ScoreEntry::getPoints).reversed());
        if (entries.size() > 10) {
            entries = entries.subList(0, 10);  // Keep only the top 10 entries
        }
    }

    public List<ScoreEntry> getEntries() {
        return new ArrayList<>(entries);  // Return a copy to avoid external modification
    }

    private void writeScoresToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (ScoreEntry entry : entries) {
                bw.write(entry.getPoints() + "," + entry.getDate());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write scores to file", e);
        }
    }

    public void addScore(int points, String date) {
        entries.add(new ScoreEntry(points, date));
        entries.sort(Comparator.comparingInt(ScoreEntry::getPoints).reversed());

        // Enforce limit of 10 entries
        if (entries.size() > 10) {
            entries = entries.subList(0, 10);  // Trim the list to the top 10 scores
        }

        writeScoresToFile();
    }


}
