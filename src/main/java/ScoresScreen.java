import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class ScoresScreen {

        private final Screen screen;

        public ScoresScreen(Screen screen) {
            this.screen = screen;
        }

        public void showScores() {
            try {

                screen.clear();


                TextGraphics graphics = screen.newTextGraphics();

                String[] scores = {
                        "Last Scores ",
                        "858",
                        "443"
                };

                // Exibir título
                graphics.putString(10, 2, "HIGHSCORE", SGR.BOLD);
                graphics.putString(10, 4, "LAST SCORES", SGR.BOLD);


                int startY = 5; // Posição inicial no eixo Y
                for (String score : scores) {
                    graphics.putString(10, startY++, score);
                }


                screen.refresh();

                 //RETORNAR AO MENU

                KeyStroke key;
                do {
                    key = screen.readInput();
                } while (key.getKeyType() != KeyType.Character || key.getCharacter() != 'q');

                screen.clear();
                screen.refresh();
                screen.readInput();

            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

