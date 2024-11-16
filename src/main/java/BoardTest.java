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
}


