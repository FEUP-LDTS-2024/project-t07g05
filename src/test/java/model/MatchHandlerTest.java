package model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchHandlerTest {
    @Test
    void testFindMatches(){
        Board board = new Board(5,5,800, 600,10,10);
        MatchHandler handler = new MatchHandler(board);

        Tile matchTile = new Tile("jewel", "ruby", new Position(0,0), new Position(0,0));
        board.setTile(0,0, matchTile);
        board.setTile(0,1, matchTile);
        board.setTile(0,2, matchTile);

        handler.findMatches();
        List<Tile> matches = handler.matches;

        assertEquals(3, matches.size());
    }

    @Test
    void testPopMatches(){
        Board board = new Board(5,5,800, 600,10,10);
        MatchHandler handler = new MatchHandler(board);

        Tile matchTile = new Tile("jewel", "ruby", new Position(0,0), new Position(0,0));
        board.setTile(0,0, matchTile);
        board.setTile(1,0, matchTile);
        board.setTile(2,0, matchTile);

        handler.findMatches();
        handler.popMatches();

        assertInstanceOf(EmptyTile.class, board.getTile(0, 0));
        assertInstanceOf(EmptyTile.class, board.getTile(1, 0));
        assertInstanceOf(EmptyTile.class, board.getTile(2, 0));

    }
}
