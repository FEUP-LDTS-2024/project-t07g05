package controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameControllerTest {
    private GameController controller;

    @BeforeEach
    void setUp() {

        Board board = mock(Board.class);
        controller = new GameController(board);
    }

    @Test
    void testStep() throws Exception {

        Board board = mock(Board.class);
        Tile currentTile = mock(Tile.class);
        Tile targetTile = mock(Tile.class);


        when(board.getCurrentTile()).thenReturn(currentTile);
        when(board.getTile(0, 1)).thenReturn(targetTile);

        GameController spyController = spy(controller);
        BoardController boardController = mock(BoardController.class);

        doReturn(board).when(spyController).getModel();

        doNothing().when(boardController).step(any(Game.class), any(GUI.ACTION.class));

        spyController.step(mock(Game.class), GUI.ACTION.UP);

        verify(boardController).step(any(Game.class), eq(GUI.ACTION.UP));

        verify(currentTile).setCursorOn(false);
        verify(targetTile).setCursorOn(true);

        verify(board).setCurrentTile(targetTile);
    }
}
