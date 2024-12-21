package com.ldts.crystalclash.model;

import com.ldts.crystalclash.strategy.GemTileBehavior;

public class GemTile extends Tile {
    public GemTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = "*";
        setBehavior(new GemTileBehavior());
    }
}
