import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board {
    private final Tile[][] grid;
    private final int rows;
    private final int columns;
    private final int rowSpacing;
    private final int columnSpacing;
    private final int startX;
    private final int startY;
    int width;
    int height;
    Tile currentTile;


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
        this.currentTile = getTile(0, 0);
        currentTile.setCursorOn(true);
    }

    public Tile[][] getGrid() {
        return grid;
    }
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private void initializeBoard() {
        String[] TYPES = {"jewel", "bomb"};
        String[] COLORS = {"diamond", "ruby", "emerald", "sapphire", "amethyst"};
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Position gridco = new Position(row, col);
                Position screenpos = calculateScreenPosition(gridco);
                String color = COLORS[(int) (Math.random() * COLORS.length)];
                Tile tile = new Tile("jewel", color, screenpos, gridco);
                if (tile == null) {
                    System.out.println("Tile initialization failed at (" + row + "," + col + ")");
                }
                grid[row][col] = tile;
            }
        }
    }

    protected Position calculateScreenPosition(Position gridcoordinate) {
        int row = gridcoordinate.getX();
        int column = gridcoordinate.getY();
        return new Position(startX + column * columnSpacing, startY + row * rowSpacing);
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

    public void swapTiles(Tile t1, Tile t2) {
        System.out.println("Swapping tiles: " + t1 + " and " + t2);
        // Swaps tile t1 with tile t2
        if (t1 == null || t2 == null) {
            return;
        }
        Position t1GridCoord = t1.getGridCoordinates();
        Position t2GridCoord = t2.getGridCoordinates();

            int t1Row = t1GridCoord.getX();
            int t1Col = t1GridCoord.getY();
            int t2Row = t2GridCoord.getX();
            int t2Col = t2GridCoord.getY();

            Tile temp = getTile(t1Row, t1Col);
            setTile(t1Row, t1Col, getTile(t2Row, t2Col));
            setTile(t2Row, t2Col, temp);

            // Updating grid coordinates and screen positions for each tile
            t1.setGridCoordinates(t2GridCoord);
            t2.setGridCoordinates(t1GridCoord);

            Position t1ScreenPos = calculateScreenPosition(t2GridCoord);
            Position t2ScreenPos = calculateScreenPosition(t1GridCoord);
            t1.setScreenPosition(t1ScreenPos);
            t2.setScreenPosition(t2ScreenPos);
            System.out.println("Tile positions before swap: t1: " + t1.getGridCoordinates() + " and t2: " + t2.getGridCoordinates());
    }

    private boolean areTilesAdjacent (Position pos1, Position pos2) {
        // validate if tiles are next to each other horizontally or vertically
        int deltaX = Math.abs(pos1.getX() - pos2.getX());
        int deltaY = Math.abs(pos1.getY() - pos2.getY());

        return (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1);
    }

    private boolean isValidPosition(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }

    public void moveCurrentTile(int row, int column) {
        if (isValidPosition(row, column)) {
            Tile prev = currentTile;
            prev.setCursorOn(false);
            Tile curr = getTile(row, column);
            this.currentTile = curr;
            curr.setCursorOn(true);
        }
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
