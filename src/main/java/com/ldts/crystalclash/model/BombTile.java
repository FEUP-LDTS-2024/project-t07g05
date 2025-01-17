package com.ldts.crystalclash.model;

import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.BombTileBehavior;

public class BombTile extends Tile {
    public BombTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = "@";
        setBehaviorContext(new BehaviorContext(this, new BombTileBehavior()));
    }
}
