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
        assertEquals(3, menu.getNumberOptions());
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testSelectNext() {
        menu.selectNext();
        assertTrue(menu.isSelectedScores());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedExit());

        menu.selectNext();
        assertTrue(menu.isSelectedExit());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());

        menu.selectNext(); // Wrap around to the first option
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testSelectPrevious() {
        menu.selectPrevious(); // Wrap around to the last option
        assertTrue(menu.isSelectedExit());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());

        menu.selectPrevious();
        assertTrue(menu.isSelectedScores());
        assertFalse(menu.isSelectedPlay());
        assertFalse(menu.isSelectedExit());

        menu.selectPrevious();
        assertTrue(menu.isSelectedPlay());
        assertFalse(menu.isSelectedScores());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testGetSelectedOption() {
        assertEquals("PLAY", menu.getSelectedOption(0));
        assertEquals("SCORES", menu.getSelectedOption(1));
        assertEquals("EXIT", menu.getSelectedOption(2));
    }

    @Test
    void testOutOfBoundsGetSelectedOption() {
        assertThrows(IndexOutOfBoundsException.class, () -> menu.getSelectedOption(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> menu.getSelectedOption(3));
    }
}
