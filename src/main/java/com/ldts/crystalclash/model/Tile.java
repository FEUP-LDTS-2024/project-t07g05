package com.ldts.crystalclash.model;

public class Tile {
    private String type;
    private String color;
    private String symbol;
    private Position screenPosition; // (X, Y), being X for columns since it aligns with the X-axis on screen, and Y for rows
    private Position gridCoordinates; // (X, Y) being X for rows and Y for columns to access 2D-arrays with grid[x][y]
    public boolean cursorOn;

    public Tile(String type, String color, Position screenPosition, Position gridCoordinates) {
        // Generates a tile
        this.type = type;
        this.color = determineColor(color);
        this.screenPosition = screenPosition;
        this.gridCoordinates = gridCoordinates;
        this.symbol = determineSymbol();
        this.cursorOn = false;
    }

    // TODO: Refactor it on Factory pattern
    private String determineColor(String color) {
        switch (color) {
            case "diamond":
                return "#70cdff";
            case "ruby":
                return "#ff1c20";
            case "emerald":
                return "#11a62f";
            case "sapphire":
                return "#0e3edd";
            case "amethyst":
                return "#a62dc5";
            default:
                return "#f3fafb";
        }
    }

    // TODO: Refactor it on Factory pattern
    private String determineSymbol() {
        switch (type) {
            case "empty":
                return " ";
            case "bomb":
                return "⊖";
            default:
                return "◼";
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public Position getScreenPosition() {
        return screenPosition;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setScreenPosition(Position screenPosition) {
        this.screenPosition = screenPosition;
    }

    public Position getGridCoordinates() {
        return gridCoordinates;
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
}
