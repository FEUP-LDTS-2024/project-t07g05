package com.ldts.crystalclash.gui;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.gui.GUI.ACTION;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

public class LanternaGUITest {
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;
    private KeyStroke mockKeyStroke;
    private Tile mockTile;
    private Board mockBoard;
    private Position mockPosition;
    private LanternaGUI lanternaGUI;

    private static final String FOREGROUND_COLOR = "#FFFFFF";
    private static final String BACKGROUND_COLOR = "#2e4045";
    private static final String GAME_BACKGROUND_COLOR = "#143b5e";

    @BeforeEach
    void setUp() {
        mockScreen = mock(Screen.class);
        mockTextGraphics = mock(TextGraphics.class);
        mockKeyStroke = mock(KeyStroke.class);
        mockTile = mock(Tile.class);
        mockBoard = mock(Board.class);
        mockPosition = mock(Position.class);

        lanternaGUI = new LanternaGUI(mockScreen);
    }

    @ParameterizedTest
    @CsvSource({
            "ArrowUp, UP",
            "ArrowDown, DOWN",
            "ArrowLeft, LEFT",
            "ArrowRight, RIGHT",
            "EOF, QUIT"
    })
    void testGetNextAction(KeyType keyType, ACTION expectedAction) throws IOException {
        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(keyType);

        ACTION action = lanternaGUI.getNextAction();

        assertEquals(expectedAction, action, "Expected " + expectedAction + " for key type " + keyType);
    }

    @Test
    void testGetNextActionNoInput() throws IOException {
        when(mockScreen.pollInput()).thenReturn(null);

        ACTION action = lanternaGUI.getNextAction();

        assertEquals(ACTION.NONE, action, "Expected ACTION.NONE when no input is received");
    }

    @Test
    void testDrawTile_ValidTile() {
        when(mockTile.getColor()).thenReturn(FOREGROUND_COLOR);
        when(mockTile.getSymbol()).thenReturn("*");
        when(mockTile.getScreenPosition()).thenReturn(mockPosition);
        when(mockPosition.getX()).thenReturn(5);
        when(mockPosition.getY()).thenReturn(10);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawTile(mockTile);

        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(FOREGROUND_COLOR));
        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        verify(mockTextGraphics).putString(new TerminalPosition(5, 10), "*");
        verifyNoMoreInteractions(mockTextGraphics);
    }

    @Test
    void testDrawBoard() {
        when(mockBoard.getWidth()).thenReturn(10);
        when(mockBoard.getHeight()).thenReturn(10);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawBoard(mockBoard);

        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e3440"));
        verify(mockTextGraphics).fillRectangle(any(), any(), eq(' '));
    }

    @Test
    void testDrawGameBackground() {
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawGameBackground(20, 30);

        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString(GAME_BACKGROUND_COLOR));
        verify(mockTextGraphics).fillRectangle(any(), any(), eq(' '));
    }

    @Test
    void testDrawTextInGame() {
        when(mockPosition.getX()).thenReturn(5);
        when(mockPosition.getY()).thenReturn(10);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawTextInGame(mockPosition, "Test", FOREGROUND_COLOR);

        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(FOREGROUND_COLOR));
        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        verify(mockTextGraphics).putString(5, 10, "Test");
    }

    @Test
    void testDrawLineHorizontal() {
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawLine(0, 0, 5, 0, "*", FOREGROUND_COLOR);

        verify(mockTextGraphics, times(6)).putString(anyInt(), eq(0), eq("*"));
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(FOREGROUND_COLOR));
    }

    @Test
    void testDrawLineVertical() {
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawLine(0, 0, 0, 5, "*", FOREGROUND_COLOR);

        verify(mockTextGraphics, times(6)).putString(eq(0), anyInt(), eq("*"));
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(FOREGROUND_COLOR));
    }

    @Test
    void testDrawLogo() {
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawLogo(0, 0, FOREGROUND_COLOR);

        verify(mockTextGraphics).setForegroundColor(any(TextColor.class));
        verify(mockTextGraphics).setBackgroundColor(any(TextColor.class));
    }

    @Test
    void testClear() {
        lanternaGUI.clear();
        verify(mockScreen).clear();
    }

    @Test
    void testRefresh() throws IOException {
        lanternaGUI.refresh();
        verify(mockScreen).refresh();
    }

    @Test
    void testClose() throws IOException {
        lanternaGUI.close();
        verify(mockScreen).close();
    }

    @Test
    void testWaitsNextActionNoKey() throws IOException {
        when(mockScreen.readInput()).thenReturn(null);

        ACTION action = lanternaGUI.waitsNextAction();

        assertEquals(ACTION.NONE, action, "Expected ACTION.NONE when no key is pressed");
    }

    @ParameterizedTest
    @CsvSource({
            "q, QUIT",
            "a, NONE"  // Assuming only 'q' maps to QUIT
    })
    void testWaitsNextActionWithCharacterKey(char inputChar, ACTION expectedAction) throws IOException {
        when(mockScreen.readInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(mockKeyStroke.getCharacter()).thenReturn(inputChar);

        ACTION action = lanternaGUI.waitsNextAction();

        assertEquals(expectedAction, action, "Expected " + expectedAction + " when '" + inputChar + "' is pressed");
    }
}
