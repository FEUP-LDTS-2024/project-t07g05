package com.ldts.crystalclash.model;

public class EmptyTile extends Tile {
    public EmptyTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = " ";
    }
}