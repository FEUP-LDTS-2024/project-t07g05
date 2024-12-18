package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TileViewerTest {
    private TileViewer tileViewer;
    private GUI gui;
    private Tile tile;

    @BeforeEach
    public void setUp() {

        tile = mock(Tile.class);

        tileViewer = new TileViewer(tile);

        gui = mock(GUI.class);
    }

    @Test
    public void testDrawElementsCallsDrawTile() {

        tileViewer.drawElements(gui);

        verify(gui).drawTile(tile);
    }
}