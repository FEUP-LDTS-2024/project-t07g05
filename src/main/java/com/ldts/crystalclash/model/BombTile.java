package com.ldts.crystalclash.model;

public class BombTile extends Tile {
    Color color;

    public BombTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = "⊖";
    }
}
