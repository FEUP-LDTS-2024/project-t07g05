package model;

import com.ldts.crystalclash.model.ScoreEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreEntryTest {

    @Test
    void testConstructor() {
        ScoreEntry entry = new ScoreEntry(100, "01/01/2024 12:00");

        assertEquals(100, entry.getPoints());
        assertEquals("01/01/2024 12:00", entry.getDate());
    }

    @Test
    void testGetPoints() {
        ScoreEntry entry = new ScoreEntry(200, "02/01/2024 13:00");

        assertEquals(200, entry.getPoints());
    }

    @Test
    void testGetDate() {
        ScoreEntry entry = new ScoreEntry(150, "03/01/2024 14:00");

        assertEquals("03/01/2024 14:00", entry.getDate());
    }

    @Test
    void testToString() {
        ScoreEntry entry = new ScoreEntry(150, "03/01/2024 14:00");

        assertEquals("ScoreEntry{points='150', date='03/01/2024 14:00'}", entry.toString());
    }
}
