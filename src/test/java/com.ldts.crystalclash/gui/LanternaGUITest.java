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
import java.io.IOException;


public class LanternaGUITest {
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;
    private KeyStroke mockKeyStroke;
    private Tile mockTile;
    private Board mockBoard;
    private Position mockPosition;

    private LanternaGUI lanternaGUI;

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

    @Test
    void testGetNextActionArrowUp() throws IOException {
        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);

        ACTION action = lanternaGUI.getNextAction();
        assertEquals(ACTION.UP, action, "Expected ACTION.UP for ArrowUp key");
    }

    @Test
    void testGetNextActionQuit() throws IOException {
        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.EOF);

        ACTION action = lanternaGUI.getNextAction();
        assertEquals(ACTION.QUIT, action, "Expected ACTION.QUIT for EOF key");
    }

    @Test
    void testDrawTile() {
        when(mockTile.getColor()).thenReturn("#FFFFFF");
        when(mockTile.getSymbol()).thenReturn("*");
        when(mockTile.getScreenPosition()).thenReturn(mockPosition);
        when(mockPosition.getX()).thenReturn(5);
        when(mockPosition.getY()).thenReturn(10);

        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        TerminalPosition terminalPosition = new TerminalPosition(5, 10);

        lanternaGUI.drawTile(mockTile);

        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e4045"));

        verify(mockTextGraphics).putString(eq(terminalPosition), eq("*"));
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

        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#143b5e"));
        verify(mockTextGraphics).fillRectangle(any(), any(), eq(' '));
    }

    @Test
    void testDrawTextInGame() {
        when(mockPosition.getX()).thenReturn(5);
        when(mockPosition.getY()).thenReturn(10);

        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGUI.drawTextInGame(mockPosition, "Test", "#FFFFFF");

        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        verify(mockTextGraphics).putString(5, 10, "Test");
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
}

