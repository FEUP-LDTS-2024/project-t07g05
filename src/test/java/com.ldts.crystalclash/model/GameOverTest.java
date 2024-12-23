package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverTest {

    private GameOver gameOver;

    @BeforeEach
    void setUp() {
        gameOver = new GameOver(100);
    }

    @Test
    void testInitialization() {
        assertNotNull(gameOver);
        assertEquals(100, gameOver.getScore());
        assertEquals(3, gameOver.getNumberOptions());
    }

    @Test
    void testSelectNext() {
        gameOver.selectNext();
        assertTrue(gameOver.isSelected(1));

        gameOver.selectNext();
        assertTrue(gameOver.isSelected(2));

        gameOver.selectNext();
        assertTrue(gameOver.isSelected(0));
    }

    @Test
    void testSelectPrevious() {
        gameOver.selectPrevious();
        assertTrue(gameOver.isSelected(2));

        gameOver.selectPrevious();
        assertTrue(gameOver.isSelected(1));

        gameOver.selectPrevious();
        assertTrue(gameOver.isSelected(0));
    }

    @Test
    void testIsSelected() {
        assertTrue(gameOver.isSelectedPlayAgain());
        assertFalse(gameOver.isSelectedScores());
        assertFalse(gameOver.isSelectedExit());

        gameOver.selectNext();
        assertTrue(gameOver.isSelectedScores());
    }

    @Test
    void testEdgeCaseSelectNext() {
        gameOver.selectNext();
        gameOver.selectNext();
        gameOver.selectNext();
        gameOver.selectNext();
        assertTrue(gameOver.isSelectedScores());
    }

    @Test
    void testEdgeCaseSelectPrevious() {
        gameOver.selectPrevious();
        gameOver.selectPrevious();
        gameOver.selectPrevious();
        gameOver.selectPrevious();
        assertTrue(gameOver.isSelectedExit());
    }

    @Test
    void testGetSelectedOption() {
        assertEquals("PLAY AGAIN", gameOver.getSelectedOption(0));
        assertEquals("SCORES", gameOver.getSelectedOption(1));
        assertEquals("EXIT", gameOver.getSelectedOption(2));
    }
}
