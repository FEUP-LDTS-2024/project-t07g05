import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tile {
    private String type;
    private String color;
    private String symbol;
    private Position position;

    public Tile(String type, String color, Position position) {
        // Generates a tile
        this.type = type;
        this.color = determineColor(color);
        this.position = position;
        this.symbol = determineSymbol();
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
            case "colorbomb":
                return "⊛";
            case "rowbomb":
                return "⊖";
            default:
                return "◼";
        }
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(this.getPosition().getX(), this.getPosition().getY()), getSymbol());
    }
}
