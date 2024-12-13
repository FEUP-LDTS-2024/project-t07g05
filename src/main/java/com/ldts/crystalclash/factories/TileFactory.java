package com.ldts.crystalclash.factories;
import com.ldts.crystalclash.model.*;

import java.util.Random;
import java.util.List;

public class TileFactory {
    public Tile createTile(String type, Position screenPosition, Position gridCoordinates) {
        return switch (type.toLowerCase()) {
            case "bomb" -> new BombTile(screenPosition, gridCoordinates, Color.DEFAULT);
            case "gem" -> new GemTile(screenPosition, gridCoordinates, getRandomGemColor());
            case "empty" -> new EmptyTile(screenPosition, gridCoordinates, Color.DEFAULT);
            default -> throw new IllegalArgumentException("Invalid tile type: " + type);
        };
    }

    public Tile createRandomTile(Position screenPosition, Position gridCoordinates) {
        boolean isBomb = Math.random() < 0.1; // chance of being a bomb
        return isBomb
                ? new BombTile(screenPosition, gridCoordinates, Color.DEFAULT)
                :  new GemTile(screenPosition, gridCoordinates, getRandomGemColor());
    }

    public Color getRandomGemColor() {
        List<Color> GEM_COLORS = List.of(Color.DIAMOND, Color.EMERALD, Color.RUBY, Color.SAPPHIRE, Color.AMETHYST);
        Random RANDOM = new Random();
        return GEM_COLORS.get(RANDOM.nextInt(GEM_COLORS.size()));
    }

}
