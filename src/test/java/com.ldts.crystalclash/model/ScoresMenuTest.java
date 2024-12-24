package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresMenuTest {
    private ScoresMenu scoresMenu;
    private final String filePath = System.getProperty("user.dir") + "/src/main/scores/scores.txt";

    @BeforeEach
    public void setUp() throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().mkdirs() && !file.getParentFile().exists()) {
            throw new IOException("Failed to create directories for scores file");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("100,01/01/2024 12:00\n");
            writer.write("200,02/01/2024 13:00\n");
            writer.write("150,03/01/2024 14:00\n");
        }

        scoresMenu = new ScoresMenu();
    }

    @Test
    public void testInitialization() {
        List<ScoreEntry> entries = scoresMenu.getEntries();
        assertEquals(3, entries.size());
        assertEquals(200, entries.get(0).getPoints());
        assertEquals(150, entries.get(1).getPoints());
        assertEquals(100, entries.get(2).getPoints());
    }

    @Test
    public void testLimitToTenScores()  {
        scoresMenu.addScore(300, "05/01/2024 16:00");
        scoresMenu.addScore(50, "06/01/2024 17:00");
        scoresMenu.addScore(400, "07/01/2024 18:00");
        scoresMenu.addScore(10, "08/01/2024 19:00");
        scoresMenu.addScore(250, "09/01/2024 20:00");
        scoresMenu.addScore(500, "10/01/2024 21:00");
        scoresMenu.addScore(350, "11/01/2024 22:00");
        scoresMenu.addScore(200, "12/01/2024 23:00");
        scoresMenu.addScore(450, "13/01/2024 00:00");
        scoresMenu.addScore(100, "14/01/2024 01:00");
        scoresMenu.addScore(600, "15/01/2024 02:00");

        List<ScoreEntry> entries = scoresMenu.getEntries();

        assertEquals(10, entries.size());

        for (int i = 0; i < entries.size() - 1; i++) {
            assertTrue(entries.get(i).getPoints() >= entries.get(i + 1).getPoints());
        }

        assertFalse(entries.stream().anyMatch(entry -> entry.getPoints() == 10));
        assertFalse(entries.stream().anyMatch(entry -> entry.getPoints() == 50));

        assertEquals(600, entries.getFirst().getPoints());
    }

    @Test
    public void testAddScore() {
        scoresMenu.addScore(250, "04/01/2024 15:00");
        List<ScoreEntry> entries = scoresMenu.getEntries();
        assertEquals(4, entries.size());
        assertEquals(250, entries.getFirst().getPoints());
    }


    @Test
    public void testFileWritingAfterAddScore() throws IOException {
        scoresMenu.addScore(300, "05/01/2024 16:00");
        List<ScoreEntry> entries = scoresMenu.getEntries();

        assertEquals(4, entries.size());
        assertEquals(300, entries.getFirst().getPoints());

        File file = new File(filePath);
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            assertEquals(4, lineCount);
        }
    }

    @Test
    public void testFileReading() throws IOException {
        ScoresMenu newScoresMenu = new ScoresMenu();
        List<ScoreEntry> entries = newScoresMenu.getEntries();
        assertEquals(3, entries.size());
        assertEquals(200, entries.getFirst().getPoints());
    }

    @Test
    public void testNoScoresFile() throws IOException {
        File file = new File(filePath);
        assertTrue(file.delete());

        ScoresMenu newScoresMenu = new ScoresMenu();
        assertTrue(newScoresMenu.getEntries().isEmpty());
    }
}
