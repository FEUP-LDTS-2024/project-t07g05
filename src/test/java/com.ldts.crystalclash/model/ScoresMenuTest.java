package com.ldts.crystalclash.model;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ScoresMenuTest {
    private ScoresMenu scoresMenu;
    private final String filePath = System.getProperty("user.dir") + "/src/main/scores/scores.txt";

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(Path.of(filePath).getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {
            writer.write("100,01/01/2024 12:00\n");
            writer.write("200,02/01/2024 13:00\n");
            writer.write("150,03/01/2024 14:00\n");
        }
        scoresMenu = new ScoresMenu();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(filePath));
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
    public void testAddScore() {
        scoresMenu.addScore(250, "04/01/2024 15:00");
        List<ScoreEntry> entries = scoresMenu.getEntries();

        assertEquals(4, entries.size());
        assertEquals(250, entries.getFirst().getPoints());
        // Adjusted method name
        assertEquals("04/01/2024 15:00", entries.getFirst().getDate());
    }


    @Test
    public void testFileWritingAfterAddScore() throws IOException {
        scoresMenu.addScore(300, "05/01/2024 16:00");
        List<ScoreEntry> entries = scoresMenu.getEntries();
        assertEquals(4, entries.size());

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8)) {
            assertEquals("300,05/01/2024 16:00", reader.readLine());
        }
    }

    @Test
    public void testMalformedScoresFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {
            writer.write("100,01/01/2024 12:00\n");
            writer.write("invalidLine\n");
        }

        ScoresMenu newScoresMenu = new ScoresMenu();

        List<ScoreEntry> entries = newScoresMenu.getEntries();
        assertEquals(1, entries.size());
        assertEquals(100, entries.getFirst().getPoints());
    }


    @Test
    public void testEmptyScoresFile() throws IOException {
        Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8).close();

        ScoresMenu newScoresMenu = new ScoresMenu();
        assertTrue(newScoresMenu.getEntries().isEmpty());
    }
}
