package com.ldts.crystalclash.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testGetHexCode() {
        assertEquals("#70cdff", Color.DIAMOND.getHexCode());
        assertEquals("#11a62f", Color.EMERALD.getHexCode());
        assertEquals("#ff1c20", Color.RUBY.getHexCode());
        assertEquals("#0e3edd", Color.SAPPHIRE.getHexCode());
        assertEquals("#a62dc5", Color.AMETHYST.getHexCode());
        assertEquals("#F9F6EE", Color.DEFAULT.getHexCode());
    }

    @Test
    void testGetRarity() {
        assertEquals(5, Color.DIAMOND.getRarity());
        assertEquals(4, Color.EMERALD.getRarity());
        assertEquals(3, Color.RUBY.getRarity());
        assertEquals(2, Color.SAPPHIRE.getRarity());
        assertEquals(1, Color.AMETHYST.getRarity());
        assertEquals(1, Color.DEFAULT.getRarity());
    }

    @Test
    void testHexCodeFormat() {
        for (Color color : Color.values()) {
            assertTrue(color.getHexCode().matches("^#[0-9A-Fa-f]{6}$"),
                    "Hex code format is invalid for color: " + color);
        }
    }
}
