package viewer;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import static org.mockito.Mockito.*;

public class BoardViewerTest {

    @Test
    void draw() throws IOException {
        Board board = mock(Board.class);
        BoardViwer boardViwer = new BoardViewer(board);
        Terminal terminal = mock(Terminal.class);
        Screen screen = mock(Screen.class);
        ScreenWriter screenWriter = mock(ScreenWriter.class);
        TextGraphics textGraphics = mock(TextGraphics.class);

        when(terminal.newScreen()).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(board.getGrid()).thenReturn(new Tile[][]);{{mock(Tile.class);}});
        when(board.getW()).thenReturn(1);
        when(board.getH()).thenReturn(1);

        boardViwer.draw(terminal);

        verify(screen).newTextGraphics();
        verify(textGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e3440");
        verify(textGraphics).fillRectangle(any(), any());
        verify(screenWriter, times(1)).draw(textGraphics);
    }
}
