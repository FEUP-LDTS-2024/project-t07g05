package com.ldts.crystalclash.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScoresMenu {
    private List<ScoreEntry> entries;
    private int newScore;

    public ScoresMenu() throws IOException {
        URL resource = ScoresMenu.class.getResource("/scores/scores.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        entries = readEntries(br);
    }

    public ScoresMenu(int newScore) throws IOException {
        URL resource = ScoresMenu.class.getResource("/scores/scores.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        entries = readEntries(br);
        this.newScore = newScore;

        // Create function to compare new score with points in the list,
        // if it is higher than any of the list entries, place it in order
    }

    private List<ScoreEntry> readEntries(BufferedReader br) throws IOException {
        List<ScoreEntry> entries = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; ) {
            String[] parts = line.split(",", 2);
            if (parts.length == 2) {
                entries.add(new ScoreEntry(parts[0].trim(), parts[1].trim()));
            }
        }
        br.close();
        return entries;
    }


    public List<ScoreEntry> getEntries() {
        return entries;
    }
}
