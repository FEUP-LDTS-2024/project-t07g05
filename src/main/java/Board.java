import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board {
    private Tile[][] grid;
    private final int rows;
    private final int columns;
    private final int rowSpacing;
    private final int columnSpacing;
    private int startX;
    private int startY;
    int width;
    int height;

    public Board(int rows, int columns, int width, int height, int rowSpacing, int columnSpacing) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Tile[rows][columns];
        this.width = width;
        this.height = height;
        this.rowSpacing = rowSpacing;
        this.columnSpacing = columnSpacing;
        this.startX = Math.round(width * 0.1f);
        this.startY = Math.round(height * 0.1f);

        initializeBoard();
    }

    private void initializeBoard() {
        String[] TYPES = {"jewel", "bomb", "powerup"};
        String[] COLORS = {"diamond", "ruby", "emerald", "sapphire", "amethyst"};
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Position pos = new Position(startX + row * rowSpacing, startY + col * columnSpacing);
                String color = COLORS[(int) (Math.random() * COLORS.length)];
                grid[row][col] = new Tile("jewel", color, pos);
            }
        }
    }

    public Tile getTile(int row, int column) {
        if (isValidPosition(row, column)) {
            return grid[row][column];
        }
        return null;
    }

    public void setTile(int row, int column, Tile newTile) {
        if (isValidPosition(row, column)) {
            grid[row][column] = newTile;
        }
    }

    private boolean isValidPosition(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2e3440"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        for (Tile[] row : grid) {
            for (Tile cell : row) {
                cell.draw(graphics);
            }
        }
    }

}
