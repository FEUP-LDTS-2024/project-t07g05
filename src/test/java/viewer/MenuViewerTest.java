package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.viewer.MenuViewer;
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
        menu = mock(Menu.class);

        menuViewer = new MenuViewer(menu);
    }

    @Test
    public void testDrawElementsCallsDrawTextWithCorrectArguments() {

        when(menu.getNumberOptions()).thenReturn(3);
        when(menu.getSelectedOption(0)).thenReturn("Option 1");
        when(menu.getSelectedOption(1)).thenReturn("Option 2");
        when(menu.getSelectedOption(2)).thenReturn("Option 3");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);
        when(menu.isSelected(2)).thenReturn(false);

        menuViewer.drawElements(gui);

        ArgumentCaptor<Position> positionCaptor = ArgumentCaptor.forClass(Position.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> colorCaptor = ArgumentCaptor.forClass(String.class);

        verify(gui, times(3)).drawText(positionCaptor.capture(), textCaptor.capture(), colorCaptor.capture());

        assertEquals(new Position(52, 20), positionCaptor.getAllValues().getFirst());
        assertEquals("Option 1", textCaptor.getAllValues().getFirst());
        assertEquals("#00FFFF", colorCaptor.getAllValues().getFirst());

        assertEquals(new Position(52, 21), positionCaptor.getAllValues().get(1));
        assertEquals("Option 2", textCaptor.getAllValues().get(1));
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(1));

        assertEquals(new Position(52, 22), positionCaptor.getAllValues().get(2));
        assertEquals("Option 3", textCaptor.getAllValues().get(2));
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(2));
    }

    @Test
    public void testDrawElementsCallsDrawLine() {
        menuViewer.drawElements(gui);

        verify(gui, times(14)).drawLine(anyInt(), anyInt(), anyInt(), anyInt(), eq("*"), eq("#5ad4f2"));
    }


    @Test
    public void testDrawLogo() {
        menuViewer.drawElements(gui);

        verify(gui).drawLogo(17, 5, "#f7fbfc");
    }

}
