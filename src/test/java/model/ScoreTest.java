package model;

import com.ldts.crystalclash.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void testInitialScore() {
        assertEquals(0, score.getScore(), "Initial score should be 0");
    }

    @Test
    void testAddScore() {
        score.addScore(10);
        assertEquals(10, score.getScore(), "Score should be 10 after adding 10 points");

        score.addScore(5);
        assertEquals(15, score.getScore(), "Score should be 15 after adding 5 points");
    }

    @Test
    void testAddNegativeScore() {
        score.addScore(10);
        score.addScore(-5);
        assertEquals(5, score.getScore(), "Score should be 5 after adding -5 points");
    }
}
