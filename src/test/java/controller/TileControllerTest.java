package controller;

import com.ldts.crystalclash.controller.TileController;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;


class TileControllerTest {
    private TileController controller;

    @BeforeEach
    void setUp() {
        Board board = mock(Board.class);
        controller = new TileController(board);
    }

    @Test
    void testStep() {

        Game game = mock(Game.class);
        LanternaGUI gui = mock(LanternaGUI.class);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.LEFT));
    }

}
