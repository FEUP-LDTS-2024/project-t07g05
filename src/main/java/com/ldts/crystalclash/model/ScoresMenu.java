package com.ldts.crystalclash.model;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ScoresMenu {
    private List<ScoreEntry> entries;
    int newScore;
    String filePath = System.getProperty("user.dir") + "/src/main/scores/scores.txt";

    public ScoresMenu() throws IOException {
        entries = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        readEntries(br);
    }

    public ScoresMenu(int newScore) throws IOException {
        this(); // Calls the default constructor

        this.newScore = newScore;
        String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        // Calls addScore if newScore is higher than the last element
        // Limits the number of entries to 10
        if (entries.isEmpty() || newScore > entries.getLast().getPoints() || entries.size() < 10) {
            addScore(newScore, currentDate);
        }
    }

    private void readEntries(BufferedReader br) throws IOException {
        for (String line; (line = br.readLine()) != null; ) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            String points = parts[0].trim();
            String date = parts[1].trim();
            entries.add(new ScoreEntry(Integer.parseInt(points), date));
        }
    }
        br.close();
    }

    public List<ScoreEntry> getEntries() {
        return entries;
    }

    private void writeScoresToFile() {
        // Limits the number of entries to 10
        if (entries.size() > 10) {
            entries = entries.subList(0, 10);
        }

        try {
            File file = new File(filePath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (ScoreEntry entry : entries) {
                bw.write(entry.getPoints() + "," + entry.getDate());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write scores to file", e);
        }
    }

    public void addScore(int points, String date) {
        entries.add(new ScoreEntry(points, date));
        System.out.println(entries);
        entries.sort(Comparator.comparingInt(ScoreEntry::getPoints).reversed());
        writeScoresToFile();
    }
}
