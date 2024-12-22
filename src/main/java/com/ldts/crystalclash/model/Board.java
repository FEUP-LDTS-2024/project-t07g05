package com.ldts.crystalclash.model;

import com.ldts.crystalclash.factories.TileFactory;

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
    Score score;
    Timer timer;
    TileFactory tileFactory;

    public Board(int rows, int columns, int width, int height, int rowSpacing, int columnSpacing) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive integers");
        }
        this.rows = rows;
        this.columns = columns;
        this.grid = new Tile[rows][columns];
        this.width = width;
        this.height = height;
        this.rowSpacing = rowSpacing;
        this.columnSpacing = columnSpacing;
        this.startX = Math.round(width * 0.1f);
        this.startY = Math.round(height * 0.1f);
        this.tileFactory = new TileFactory();
        this.score = new Score();
        this.timer = new Timer();
        timer.start();

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

    public Score getScore() {
        return score;
    }

    public Timer getTimer() {
        return timer;
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
            if (newTile != null) {
                newTile.setGridCoordinates(new Position(row, column));
            }
        }
    }

    ///
    public void setScore(Score score) {
        this.score = score;
    }


    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Tile getTileToTheRight(Tile tile) {
        Position gridco = tile.getGridCoordinates();
        int row = gridco.getX(); int col = gridco.getY();
        return getTile(row, col+1);
    }

    public Tile getTileToTheLeft(Tile tile) {
        Position gridco = tile.getGridCoordinates();
        int row = gridco.getX(); int col = gridco.getY();
        return getTile(row, col-1);
    }

    public Tile getTileOnBottom(Tile tile) {
        Position gridco = tile.getGridCoordinates();
        int row = gridco.getX(); int col = gridco.getY();
        return getTile(row+1, col);
    }

    public Tile getTileOnTop(Tile tile) {
        Position gridco = tile.getGridCoordinates();
        int row = gridco.getX(); int col = gridco.getY();
        return getTile(row-1, col);
    }

    public boolean isValidPosition(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }

    private void initializeBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Position gridco = new Position(row, col);
                Position screenpos = calculateScreenPosition(gridco);
                Tile tile = tileFactory.createRandomTile(screenpos, gridco);
                if (tile == null) {
                    throw new RuntimeException("Failed to create tile at position (" + row + ", " + col + ")");
                }
                grid[row][col] = tile;
            }
        }
        this.currentTile = getTile(0, 0);
        currentTile.setCursorOn(true);
    }


    public Position calculateScreenPosition(Position gridcoordinate) {
        int row = gridcoordinate.getX();
        int column = gridcoordinate.getY();
        return new Position(startX + column * columnSpacing, startY + row * rowSpacing);
    }
}