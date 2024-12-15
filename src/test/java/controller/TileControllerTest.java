package controller;

import com.ldts.crystalclash.controller.TileController;
import com.ldts.crystalclash.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
        try{
            controller.step(null,null, 0);
        }catch(Exception e){
            assert false : "Step should not throw an exception";
        }
    }

}
