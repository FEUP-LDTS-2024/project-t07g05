package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.ScoresMenu;

public class InstructionsViewer extends Viewer<Instructions> {
    public InstructionsViewer(Instructions model) {
        super(model);}

    @Override
    protected void drawElements(GUI gui) {
        gui.drawGameBackground(120, 40);
        gui.drawText(new Position(50, 0), "INSTRUCTIONS", "#ffff00");

        gui.drawText(new Position(13, 1),  "Welcome to Crystal Clash!", "#ffffff");
        gui.drawText(new Position(13, 3),  "1. The game board is an 8x8 grid of crystals.", "#ffffff");
        gui.drawText(new Position(13, 5),  "2. The crystals are: DIAMOND, EMERALD, RUGBY,", "#ffffff");
        gui.drawText(new Position(13, 6), "   SAPPHIRE, AMETHYST, and DEFAULT.", "#ffffff");
        gui.drawText(new Position(13, 8), "3. Match 3 or more identical crystals in a row or", "#ffffff");
        gui.drawText(new Position(13, 9), "   column to score points.", "#ffffff");
        gui.drawText(new Position(13, 11), "4. Larger matches score more points.", "#ffffff");
        gui.drawText(new Position(13, 13), "5. Bombs --> @", "#ffffff");
        gui.drawText(new Position(13, 15), "   - Bombs are special tiles that, when matched", "#ffffff");
        gui.drawText(new Position(13, 16), "     in a group of 3 or more, eliminate all the adjacent tiles", "#ffffff");
        gui.drawText(new Position(13, 18), "6. Crystal point values (highest to lowest):", "#ffffff");
        gui.drawText(new Position(15, 20), "   - DIAMOND --> 5 points", "#00ffff");
        gui.drawText(new Position(15, 21), "   - EMERALD --> 4 points", "#00ff00");
        gui.drawText(new Position(15, 22), "   - RUGBY --> 3", "#ff8800");
        gui.drawText(new Position(15, 23), "   - SAPPHIRE --> 2", "#0000ff");
        gui.drawText(new Position(15, 24), "   - AMETHYST --> 1", "#ff00ff");
        gui.drawText(new Position(15, 25), "   - DEFAULT --> 1", "#aaaaaa");
        gui.drawText(new Position(13, 27), "7. Tiles combinations points:", "#ffffff");
        gui.drawText(new Position(13, 29), "   -[tile value] x [number of tiles congregated]", "#ffffff");
        gui.drawText(new Position(13, 30), "   ex: Combinations of 4 diamonds is 5 * 4 = 20", "#ffffff");
        gui.drawText(new Position(13,32), "8.  Bombs combinations points:","#ffffff");
        gui.drawText(new Position(13,34), "    -[number of consecutive bombs] + [sum of all adjacent tiles]","#ffffff");
        gui.drawText(new Position(13, 36), "9. Objective: Do the largest and highest value combinations in 90s!", "#ffffff");

        gui.drawText(new Position(42,38), "click 'q' to return to the menu","#ff3300");
    }
}
