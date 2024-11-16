import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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
}


