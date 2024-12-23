package model;

import com.ldts.crystalclash.model.ScoreEntry;
import com.ldts.crystalclash.model.ScoresMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresMenuTest {
    private ScoresMenu scoresMenu;
    private final String filePath = System.getProperty("user.dir") + "/src/main/scores/scores.txt";

    @BeforeEach
    void setUp() throws IOException {
        File tempFile = new File(filePath);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("100,01/01/2024 12:00\n");
            writer.write("200,02/01/2024 13:00\n");
            writer.write("150,03/01/2024 14:00\n");
        }

        scoresMenu = new ScoresMenu();
    }

    @Test
    void testInitialization() {
        ScoresMenu scoresMenu = null;
        try {
            scoresMenu = new ScoresMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scoresMenu.addScore(200, "02/01/2024 13:00");
        scoresMenu.addScore(150, "03/01/2024 14:00");

        ScoreEntry lastScore = scoresMenu.getEntries().getFirst();
        assertEquals(115, lastScore.getPoints());
    }


    @Test
    void testAddScore_AboveThreshold() {
        scoresMenu.addScore(250, "04/01/2024 15:00");
        List<ScoreEntry> entries = scoresMenu.getEntries();

        assertEquals(4, entries.size());
        assertEquals(250, entries.getFirst().getPoints());
    }

    @Test
    void testAddScore_LimitTo10Entries() {
        for (int i = 1; i <= 15; i++) {
            scoresMenu.addScore(100 + i, "date" + i);
        }

        List<ScoreEntry> entries = scoresMenu.getEntries();
        assertEquals(10, entries.size());
        assertEquals(115, entries.getFirst().getPoints());
    }

    @Test
    void testFileWritingAfterAddScore() throws IOException {
        scoresMenu.addScore(500, "05/01/2024 16:00");

        List<ScoreEntry> entriesAfterWrite = readScoresFromFile();

        assertEquals(4, entriesAfterWrite.size());
        assertEquals(500, entriesAfterWrite.getFirst().getPoints());
    }

    @Test
    void testFileReading() throws IOException {
        ScoresMenu newScoresMenu = new ScoresMenu();
        List<ScoreEntry> entries = newScoresMenu.getEntries();

        assertEquals(3, entries.size());
        assertEquals(200, entries.getFirst().getPoints());
    }

    // Helper method to read the scores from the file
    private List<ScoreEntry> readScoresFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<ScoreEntry> entries = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                entries.add(new ScoreEntry(Integer.parseInt(parts[0].trim()), parts[1].trim()));
            }
        }
        reader.close();
        return entries;
    }
}
