package controller;
import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ldts.crystalclash.Game;
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

        BoardController boardController = mock(BoardController.class);

        GameController spyController = spy(controller);

        spyController.step(mock(Game.class), GUI.ACTION.UP, 0);

        verify(boardController).step(any(Game.class), eq(GUI.ACTION.UP), anyLong());
    }
}
