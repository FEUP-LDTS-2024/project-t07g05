import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;




public class BoardTest {

    @Test
    public void testBoardInitialization(){

        Board board = new Board(8,10,100, 40,4,4);

        Assertions.assertEquals(8, board.getRows(), "The number of rows should be 8.");

        Assertions.assertEquals(10, board.getColumns(), "The number of columns should be 10.");

        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 10; col++){
                Tile tile = board.getTile(row, col);

                Assertions.assertNotNull(tile, "The tile at position (" + row + ", " + col + ") should not be null.");
            }
        }
    }

    @Test
    public void testTileInitialization(){

        Board board = new Board(8,10,100, 40,4,4);

        String[] validColors = {"diamond", "ruby", "emerald", "sapphire", "amethyst"};

        for (int row = 0; row < board.getRows(); row++){
            for (int col = 0; col < board.getColumns(); col ++){
                Tile tile = board.getTile(row, col);

                Assertions.assertEquals("jewel", tile.getType(), "Tile type should be jewel.");

                Assertions.assertTrue(java.util.Arrays.asList(validColors).contains(tile.getColor()), "Tile color at position (" + row + ", " + col + ") should be valid.");
            }
        }
    }


    @Test
    public void testMoveCurrentTile(){

        Board board = new Board(8,10,100, 40,4,4);

        //Test movement from the top-left corner (0,0)
        testTileMovement(board, 0, 0,1,0); //Move right
        testTileMovement(board, 1, 0,1,1); //Move down
        testTileMovement(board, 1, 1,0,1); //Move left
        testTileMovement(board, 0, 1,0,0); //Move up

        //Test movement from a different position
        testTileMovement(board, 7, 9, 6, 9); // Move up
        testTileMovement(board, 6, 9, 6, 8); // Move left
        testTileMovement(board, 6, 8, 7, 8); // Move down
        testTileMovement(board, 7, 8, 7, 9); // Move right
    }


    private void testTileMovement(Board board, int startRow, int startCol, int endRow, int endCol){
        Tile initialTile = board.getTile(startRow, startCol);
        board.moveCurrentTile(endRow, endCol);

        Tile newTile = board.getTile(endRow, endCol);

        Assertions.assertEquals(newTile, board.currentTile, "The current tile should be moved to position (" + endRow + ", " + endCol + ").");

        Assertions.assertTrue(newTile.isCursorOn(), "The cursor should be on the tile at position (" + endRow + ", " + endCol + ").");

        Assertions.assertFalse(initialTile.isCursorOn(), "The cursor should be off the tile at position (" + startRow + ", " + startCol + ").");
    }


    @Test
    public void testInvalidMove(){

        Board board = new Board(8,10,100, 40,4,4);

        Tile initialTile = board.getTile(0,0);

        board.moveCurrentTile(-1, 0); //Invalid move: row <0
        Assertions.assertEquals(initialTile, board.currentTile, "The tile should not have moved outside the board.");

        board.moveCurrentTile(0, -1); //Invalid move: col <0
        Assertions.assertEquals(initialTile, board.currentTile, "The tile should not have moved outside the board.");

        board.moveCurrentTile(8,0); //Invalid move: row >= rows
        Assertions.assertEquals(initialTile, board.currentTile, "The tile should not have moved outside the board.");

        board.moveCurrentTile(0,10); //Invalid move: col >= columns
        Assertions.assertEquals(initialTile, board.currentTile, "The tile should not have moved outside the board.");
    }


    @Test
    public void testSwapTiles(){

        Board board = new Board(8,10,100, 40,4,4);

        Tile tile1 = board.getTile(0,0);
        Tile tile2 = board.getTile(0,1);

        Position originalPos1 = tile1.getGridCoordinates();
        Position originalPos2 = tile2.getGridCoordinates();

        board.swapTiles(tile1, tile2);

        Assertions.assertEquals(originalPos2, tile1.getGridCoordinates(), "tile1 should have moved to tile2's position.");
        Assertions.assertEquals(originalPos1, tile2.getGridCoordinates(), "tile2 should have moved to tile1's position.");

        Assertions.assertEquals(tile2, board.getTile(0,0), "tile1 should now be at tile1's position.");
        Assertions.assertEquals(tile1, board.getTile(0,1), "tile1 should now be at tile2's position.");
    }


    @Test
    public void testSwapTilesNonAdjacent(){

        Board board = new Board(8,10,100, 40,4,4);

        Tile tile1 = board.getTile(0,0);
        Tile tile2 = board.getTile(2,2);

        board.swapTiles(tile1,tile2);

        Assertions.assertEquals(tile1, board.getTile(0,0), "tile1 should not have moved.");
        Assertions.assertEquals(tile2, board.getTile(2,2), "tile2 should not have moved.");
    }

    @Test
    public void testTileColorDistribution(){

        String[] validColors = {"diamond", "ruby", "emerald", "sapphire", "amethyst"};
        Set<String> observedColors = new HashSet<>();

        for (int i = 0; i < 100; i++){
            Board board = new Board(8,10,100, 40,4,4);

            for(int row = 0; row < board.getRows(); row ++){
                for(int col = 0; col < board.getColumns(); col++){
                    Tile tile = board.getTile(row,col);
                    observedColors.add(tile.getColor());

                }
            }
            if (observedColors.size() == validColors.length){
                break;
            }
        }

        Assertions.assertTrue(observedColors.containsAll(Arrays.asList(validColors)), "All valid colors should appear after multiple board initializations.");
    }


    @Test
    public void testDraw(){

        Board board = new Board(2,2,20,10,4,4);

        TextGraphics mockGraphics = mock(TextGraphics.class);

        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        Tile mockTile3 = mock(Tile.class);
        Tile mockTile4 = mock(Tile.class);

        board.setTile(0,0,mockTile1);
        board.setTile(0,1,mockTile2);
        board.setTile(1,0,mockTile3);
        board.setTile(1,1,mockTile4);

        board.draw(mockGraphics);

        verify(mockGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e3440"));
        verify(mockGraphics).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));

        verify(mockTile1).draw(mockGraphics);
        verify(mockTile2).draw(mockGraphics);
        verify(mockTile3).draw(mockGraphics);
        verify(mockTile4).draw(mockGraphics);

    }


    @Test
    public void testScreenPositionCalculation(){

        Board board = new Board(3,3,100,40,10,10);

        Position pos = board.calculateScreenPosition(new Position(1,1));

        Assertions.assertEquals(20, pos.getX(),"X coordinate should match expected screen position.");
        Assertions.assertEquals(20, pos.getY(),"Y coordinate should match expected screen position.");
    }

}
