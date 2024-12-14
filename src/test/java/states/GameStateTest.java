package states;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class GameStateTest {

    private Board board;
    private GameState gameState;
    private LanternaGUI gui;
    private BoardController boardController;
    private GameViewer gameViewer;


    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        gui = mock(LanternaGUI.class);
        gameViewer = mock(GameViewer.class);
        boardController = mock(BoardController.class);

        gameState = new GameState(board){
            @Override
            protected GameViewer getViewer() {
                return gameViewer;
            }

            @Override
            protected BoardController getController() {
                return boardController;
            }
        }
    }
}



