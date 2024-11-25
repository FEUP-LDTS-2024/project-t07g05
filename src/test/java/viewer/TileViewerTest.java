package viewer;

import gui.LanternaGUI;

import com.ldts.crystalclash.viewer.TileViewer;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

 class TileViewerTest {
     private Tile mockTile;
     private TileViewer tileViewer;
     private LanternaGUI mockGUI;

     @BeforeEach
     void setUp() {
         mockTile = mock(Tile.class);
         mockGUI = mock(LanternaGUI.class);
         tileViewer = new TileViewer(mockTile);
     }

     @Test
     void testDrawWithCursorOn(){
         Position position = new Position(2, 3);
         String symbol = "X";
         String color = "#FF0000";

         when(mockTile.getSymbol()).thenReturn(symbol);
         when(mockTile.getColor()).thenReturn(color);
         when(mockTile.getScreenPosition()).thenReturn(position);
         when(mockTile.isCursorOn()).thenReturn(true);

         tileViewer.draw(mockGUI);

         verify(mockGUI).drawTile(mockTile);
     }

     @Test
     void testDrawWithCursorOff(){
         Position position = new Position(5, 7);
         String symbol = "0";
         String color = "#00F00";

         when(mockTile.getSymbol()).thenReturn(symbol);
         when(mockTile.getColor()).thenReturn(color);
         when(mockTile.getScreenPosition()).thenReturn(position);
         when(mockTile.isCursorOn()).thenReturn(false);

         tileViewer.draw(mockGUI);

         verify(mockGUI).drawTile(mockTile);

     }
}

