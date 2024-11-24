package model;

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

    public int getRowSpacing() {
        return rowSpacing;
    }

    public int getColumnSpacing() {
        return columnSpacing;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public boolean isValidPosition(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }

    //TODO: Refactor it when FactoryTile is created
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

    public Position calculateScreenPosition(Position gridcoordinate) {
        int row = gridcoordinate.getX();
        int column = gridcoordinate.getY();
        return new Position(startX + column * columnSpacing, startY + row * rowSpacing);
    }
}