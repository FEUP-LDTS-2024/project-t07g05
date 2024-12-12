package com.ldts.crystalclash.model;

public enum Color {
    DIAMOND("#70cdff", 5),
    EMERALD("#11a62f", 4),
    RUBY("#ff1c20", 3),
    SAPPHIRE("#0e3edd", 2),
    AMETHYST("a62dc5", 1);

    private final String hexCode;
    private final int rarity;

    Color(String hexCode, int rarity) {
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
