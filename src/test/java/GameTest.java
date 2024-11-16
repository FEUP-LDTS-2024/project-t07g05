import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;


public class GameTest {

    private Game game;
    private Screen mockScreen;
    private Board mockBoard;
    private TextGraphics mockGraphics;

    @BeforeEach
    public void setUp(){

        mockScreen = mock(Screen.class);
        mockBoard = mock(Board.class);
        mockGraphics = mock(TextGraphics.class);

        game = new Game();
        injectMockDependencies();
    }

    private void injectMockDependencies(){
        try{
            var screenField = Game.class.getDeclaredField("screen");
            screenField.setAccessible(true);
            screenField.set(game, mockScreen);

            var boardField = Game.class.getDeclaredField("board");
            boardField.setAccessible(true);
            boardField.set(game, mockBoard);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testDrawGameBackground() throws IOException{
        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);

        game.drawGameBackground();

        verify(mockGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        verify(mockGraphics).fillRectangle(any(), eq(new TerminalSize(120,40)), eq(' '));

    }


}
