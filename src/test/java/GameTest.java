import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);


        game = new Game();
        injectMockDependencies(game, "screen", mockScreen);
        injectMockDependencies(game, "board", mockBoard);
        injectMockDependencies(game, "graphics", mockGraphics);

    }


    private void injectMockDependencies(Object target, String fieldName, Object value){
        try{
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);

        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock dependency: " + fieldName, e);
        }
    }


    private KeyStroke createMockKeyStroke(KeyType keyType, Character character){
        KeyStroke mockKey = mock(KeyStroke.class);
        when(mockKey.getKeyType()).thenReturn(keyType);

        if(character != null){
            when(mockKey.getCharacter()).thenReturn(character);
        }
        return mockKey;
    }


    private void invokePrivateMethod(Object target, String methodName, Class <?>[] parameterTypes,Object... args){
        try{
            Method method = target.getClass().getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            method.invoke(target, args);
        } catch(Exception e){
            throw new RuntimeException("Failed to invoke private method: " + methodName,e);
        }
    }


    @Test
    public void testDrawGameBackground() {

        game.drawGameBackground();

        verify(mockGraphics).setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        verify(mockGraphics).fillRectangle(any(), eq(new TerminalSize(120,40)), eq(' '));

    }


    @Test
    public void testDraw() throws IOException{

        game.draw();

        verify(mockScreen).clear();
        verify(mockScreen).refresh();
        verify(mockBoard).draw(mockGraphics);
    }


    @Test
    public void testProcessKeyArrowKeys() {
        KeyStroke mockKey = createMockKeyStroke(KeyType.ArrowRight, null);

        Tile mockTile = mock(Tile.class);
        Position mockPosition = new Position(0, 0);

        when(mockBoard.currentTile).thenReturn(mockTile);
        when(mockTile.getGridCoordinates()).thenReturn(mockPosition);

        invokePrivateMethod(game, "processKey", new Class<?>[]{KeyStroke.class}, mockKey);

        verify(mockBoard).moveCurrentTile(1, 0);
        verifyNoMoreInteractions(mockBoard); // Ensure no other interactions occurred
    }

    @Test
    public void testProcessKeyInvalidKey() {
        KeyStroke invalidKey = createMockKeyStroke(KeyType.Character, 'z');

        invokePrivateMethod(game, "processKey", new Class<?>[]{KeyStroke.class}, invalidKey);

        verifyNoInteractions(mockBoard);
    }

    @Test
    public void testProcessKeyNullKey() {
        KeyStroke nullKey = null;

        assertThrows(RuntimeException.class, () ->
                invokePrivateMethod(game, "processKey", new Class<?>[]{KeyStroke.class}, nullKey)
        );
    }



    @Test
    public void testProcessKeyQuit() throws Exception{
        KeyStroke mockKey = createMockKeyStroke(KeyType.Character, 'q');

        invokePrivateMethod(game, "processKey", new Class<?>[]{KeyStroke.class}, mockKey);
        verify(mockScreen).stopScreen();
    }


    @Test
    public void testRunStopsOnEOF() throws IOException{
        KeyStroke eofKey = createMockKeyStroke(KeyType.EOF, null);

        when(mockScreen.readInput()).thenReturn(eofKey);

        game.run();

        verify(mockScreen, atLeastOnce()).readInput();
        verify(mockScreen).stopScreen();
    }


    @Test
    public void testRunProcessesValidInput() throws IOException{
        KeyStroke mockKeyArrowUp = createMockKeyStroke(KeyType.ArrowUp, null);
        KeyStroke mockKeyEOF = createMockKeyStroke(KeyType.EOF, null);

        when(mockScreen.readInput()).thenReturn(mockKeyArrowUp, mockKeyEOF);

        game.run();

        verify(mockBoard).moveCurrentTile(0, -1);
        verify(mockScreen).stopScreen();
    }


    @Test
    public void testProcessKeySpaceSwapsTiles() {
        KeyStroke spaceKey = createMockKeyStroke(KeyType.Character, ' ');

        Tile mockTile = mock(Tile.class);
        Tile anotherMockTile = mock(Tile.class);
        Position mockPosition = new Position(0,0);

        when(mockBoard.currentTile).thenReturn(mockTile);
        when(mockTile.getGridCoordinates()).thenReturn(mockPosition);
        when(mockBoard.getTile(anyInt(), anyInt())).thenReturn(anotherMockTile);

        invokePrivateMethod(game, "processKey", new Class<?>[]{KeyStroke.class}, spaceKey);

        verify(mockBoard).swapTiles(mockTile, anotherMockTile);
    }

}
