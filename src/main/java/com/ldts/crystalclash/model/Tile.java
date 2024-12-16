package com.ldts.crystalclash.model;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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

    private String determineSymbol() {
        switch (type) {
            case "bomb":
                return "⊖";
            default:
                return "◼";
        }
    }

    public String getType() {return type;}

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

    public void setScreenPosition(Position screenPosition) {
        this.screenPosition = screenPosition;
    }

    public Position getGridCoordinates() {
        return gridCoordinates;
    }

    public void setGridCoordinates(Position gridCoordinates) {
        this.gridCoordinates = gridCoordinates;
    }

    public void setCursorOn(boolean cursorOn) {
        this.cursorOn = cursorOn;
    }

    public boolean isCursorOn() {
        return cursorOn;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        if (isCursorOn()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        }
        graphics.putString(new TerminalPosition(this.getScreenPosition().getX(), this.getScreenPosition().getY()), getSymbol());
    }
}
