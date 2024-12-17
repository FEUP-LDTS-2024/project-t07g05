package controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameControllerTest {
    private GameController controller;
    private BoardController boardController;
    private Board board;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        boardController = mock(BoardController.class);
        controller = new GameController(board);
    }

    @Test
    void testStep() throws Exception {
        BoardController boardController = mock(BoardController.class);
        GameController spyController = spy(controller);

        doReturn(boardController).when(spyController).getModel();

        spyController.step(mock(Game.class), GUI.ACTION.UP);

        verify(boardController).step(any(Game.class), eq(GUI.ACTION.UP));
    }
}
