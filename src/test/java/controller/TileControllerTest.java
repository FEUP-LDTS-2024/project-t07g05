package controller;

import model.Board;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TileControllerTest {
    private TileController tileController;

    @BeforeEach
    void setUp() {
        Board mockBoard = Mockito.mock(Board.class);
        tileController = new TileController(mockBoard);
    }

    @Test
    void testStepDoesNotThrowException() {
        assertDoesNotThrow(() -> tileController.step());

    }
}
