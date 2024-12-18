package com.ldts.crystalclash.model;

public class GemTile extends Tile {
    public GemTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = "*";
    }
}
