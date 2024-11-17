import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;

public class Menu {

    private final Screen screen;

    public Menu(Screen screen) {
        this.screen = screen;
    }

    public int show() {
        try {
            String[] menuOptions = {"PLAY", "SCORES", "EXIT"};
            int selectedOption = 0;
            TextGraphics graphics = screen.newTextGraphics();

            while (true) {
                screen.clear();


                graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                graphics.putString(50, 3, "CRYSTAL CLASH");
                graphics.enableModifiers(SGR.BOLD);//negrito


                for (int i = 0; i < menuOptions.length; i++) {
                    if (i == selectedOption) {
                        graphics.setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
                        graphics.drawLine(40, 18, 70, 18, '*');
                        graphics.drawLine(40, 24, 70, 24, '*');
                        graphics.drawLine(40, 24, 40, 18, '*');
                        graphics.drawLine(70, 24, 70, 18, '*');
                        graphics.enableModifiers(SGR.BLINK);//pisca

                        graphics.putString(50, 20 + i, "> " + menuOptions[i]);

                        graphics.disableModifiers(SGR.BLINK);//paradepiscar

                    } else {
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                        graphics.putString(50, 20 + i, "  " + menuOptions[i]);
                    }
                }

                screen.refresh();


                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.ArrowUp) {
                    selectedOption = (selectedOption == 0) ? menuOptions.length - 1 : selectedOption - 1;
                } else if (key.getKeyType() == KeyType.ArrowDown) {
                    selectedOption = (selectedOption == menuOptions.length - 1) ? 0 : selectedOption + 1;

                } else if (key.getKeyType() == KeyType.Enter) {
                    return selectedOption;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}