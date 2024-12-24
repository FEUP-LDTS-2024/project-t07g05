package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuViewerTest {

    private GUI gui;
    private Menu menu;
    private MenuViewer menuViewer;

    @BeforeEach
    public void setup() {
        gui = mock(GUI.class);
        menu = new Menu();
        menuViewer = new MenuViewer(menu);
    }

    @Test
    public void testDrawElementsCallsDrawTextWithCorrectArguments() {
        menu.selectNext();

        menuViewer.drawElements(gui);

        ArgumentCaptor<Position> positionCaptor = ArgumentCaptor.forClass(Position.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> colorCaptor = ArgumentCaptor.forClass(String.class);

        verify(gui, times(4)).drawText(positionCaptor.capture(), textCaptor.capture(), colorCaptor.capture());

        assertEquals(new Position(54, 20), positionCaptor.getAllValues().get(0));
        assertEquals("PLAY", textCaptor.getAllValues().get(0));
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(0));

        assertEquals(new Position(53, 21), positionCaptor.getAllValues().get(1));
        assertEquals("SCORES", textCaptor.getAllValues().get(1));
        assertEquals("#00FFFF", colorCaptor.getAllValues().get(1));

        assertEquals(new Position(50, 22), positionCaptor.getAllValues().get(2));
        assertEquals("INSTRUCTIONS", textCaptor.getAllValues().get(2)); // Added INSTRUCTIONS
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(2));

        assertEquals(new Position(54, 23), positionCaptor.getAllValues().get(3));
        assertEquals("EXIT", textCaptor.getAllValues().get(3));
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(3));
    }

    @Test
    public void testDrawElementsCallsDrawLine() {
        menuViewer.drawElements(gui);

        verify(gui, times(16)).drawLine(anyInt(), anyInt(), anyInt(), anyInt(), eq("*"), eq("#5ad4f2"));
    }

    @Test
    public void testDrawLogo() {
        menuViewer.drawElements(gui);

        // Verify that drawLogo is called with the correct parameters
        verify(gui).drawLogo(17, 5, "#f7fbfc");
    }
}

