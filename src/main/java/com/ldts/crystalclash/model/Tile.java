package com.ldts.crystalclash.model;

public abstract class Tile {
    String symbol;
    Position screenPosition; // (X, Y), being X for columns since it aligns with the X-axis on screen, and Y for rows
    Position gridCoordinates; // (X, Y) being X for rows and Y for columns to access 2D-arrays with grid[x][y]
    boolean cursorOn;
    Color color;

    public Tile(Position screenPosition, Position gridCoordinates, Color color) {
        this.screenPosition = screenPosition;
        this.gridCoordinates = gridCoordinates;
        this.cursorOn = false;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public Position getScreenPosition() {
        return screenPosition;
    }

    public Position getGridCoordinates() {
        return gridCoordinates;
    }

    public void setScreenPosition(Position screenPosition) {
        this.screenPosition = screenPosition;
    }

    public void setGridCoordinates(Position gridCoordinates) {
        this.gridCoordinates = gridCoordinates;
    }

    public boolean isCursorOn() {
        return cursorOn;
    }

    public void setCursorOn(boolean cursorOn) {
        this.cursorOn = cursorOn;
    }

    public String getColor() {
        return color.getHexCode();
    }

    public int getColorRarity() {
        return color.getRarity();
    }
}