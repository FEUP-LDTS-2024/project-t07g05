package viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

 class TileViewerTest {
     private Tile tile;
     private TileViewer tileViewer;
     private LanternaGUI gui;

     @BeforeEach
     void setUp() {
         tile = mock(Tile.class);
         gui = mock(LanternaGUI.class);
         tileViewer = new TileViewer(tile);
     }

     @Test
     void testDraw(){
         tileViewer.draw(gui);

         verify(gui).drawTile(tile);
         verify(tileViewer).getModel();
     }
}

