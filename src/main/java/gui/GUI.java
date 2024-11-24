package gui;

import model.Position;
import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawText(Position position, String text, String color);

    void drawCharacter(int x, int y, char c, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, QUIT}
}
