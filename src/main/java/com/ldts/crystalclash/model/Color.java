package com.ldts.crystalclash.model;

import java.util.List;
import java.util.Random;

public enum Color {
    DIAMOND("#70cdff", 5),
    EMERALD("#11a62f", 4),
    RUBY("#ff1c20", 3),
    SAPPHIRE("#0e3edd", 2),
    AMETHYST("#a62dc5", 1),
    DEFAULT("#F9F6EE", 1);

    private final String hexCode;
    private final int rarity;

    Color(String hexCode, int rarity) {
        if (!hexCode.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("Invalid hex color code.");
        }
        this.hexCode = hexCode;
        this.rarity = rarity;
    }

    public String getHexCode() {
        return hexCode;
    }

    public int getRarity() {
        return rarity;
    }
}
