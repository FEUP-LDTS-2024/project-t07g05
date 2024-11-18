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

    public void logodraw(TextGraphics graphics, int startX, int startY){
        String[] crystalClash = new String[]{
                "                              _             _            _                 _       " +
                        "                             | |           | |          | |               | |      \n" ,
                "   ___   _ __   _   _   ___  | |_    __ _  | |     ___  | |   __ _   ___  | |__    \n" ,
                "  / __| | '__| | | | | / __| | __|  / _` | | |    / __| | |  / _` | / __| | '_ \\   \n" ,
                " | (__  | |    | |_| | \\__ \\ | |_  | (_| | | |   | (__  | | | (_| | \\__ \\ | | | |  \n",
                "  \\___| |_|     \\__, | |___/  \\__|  \\__,_| |_|    \\___| |_|  \\__,_| |___/ |_| |_|  \n" ,
                "                 __/ |                                                             \n" ,
                "                |___/                                                              \n",};

        for (int i = 0; i < crystalClash.length; i++) {
            graphics.putString(startX, startY + i, crystalClash[i]);

            //usei https://patorjk.com/software/taag/#p=display&h=0&v=0&f=Big&t=crystal%20clash%20
        }

    }

    public int show() {
        try {
            String[] menuOptions = {"PLAY", "SCORES", "EXIT"};
            int selectedOption = 0;
            TextGraphics graphics = screen.newTextGraphics();

            while (true) {

                screen.clear();


                graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                logodraw(graphics,17,5);


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
                if (key.getKeyType() == KeyType.ArrowUp){
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