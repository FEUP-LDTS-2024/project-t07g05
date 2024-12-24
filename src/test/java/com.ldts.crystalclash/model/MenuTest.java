package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void testInitialization() {
        assertEquals(4, menu.getNumberOptions());
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testSelectNext() {
        menu.selectNext();
        assertTrue(menu.isSelectedScores());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());

        menu.selectNext();
        assertTrue(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedExit());

        menu.selectNext();
        assertTrue(menu.isSelectedExit());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedInstructions());

        menu.selectNext();
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testSelectPrevious() {
        menu.selectPrevious();
        assertTrue(menu.isSelectedExit());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedInstructions());

        menu.selectPrevious();
        assertTrue(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedExit());

        menu.selectPrevious();
        assertTrue(menu.isSelectedScores());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());

        menu.selectPrevious();
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testGetSelectedOption() {
        assertEquals("PLAY", menu.getSelectedOption(0));
        assertEquals("SCORES", menu.getSelectedOption(1));
        assertEquals("INSTRUCTIONS", menu.getSelectedOption(2));
        assertEquals("EXIT", menu.getSelectedOption(3));
    }

    @Test
    void testOutOfBoundsGetSelectedOption() {
        assertThrows(IndexOutOfBoundsException.class, () -> menu.getSelectedOption(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> menu.getSelectedOption(4));
    }
}
