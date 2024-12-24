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

        gui.drawText(new Position(1, 1),  "Welcome to Crystal Clash!", "#ffffff");
        gui.drawText(new Position(1, 3),  "1. The game board is an 8x8 grid of crystals.", "#ffffff");
        gui.drawText(new Position(1, 5),  "2. The crystals are: DIAMOND, EMERALD, RUBY,", "#ffffff");
        gui.drawText(new Position(1, 6), "   SAPPHIRE, AMETHYST, and DEFAULT.", "#ffffff");
        gui.drawText(new Position(1, 8), "3. Match 3 or more identical crystals in a row or", "#ffffff");
        gui.drawText(new Position(1, 9), "   column to score points.", "#ffffff");
        gui.drawText(new Position(1, 11), "4. Larger matches score more points.", "#ffffff");
        gui.drawText(new Position(1, 13), "5. Bombs --> @", "#ffffff");
        gui.drawText(new Position(1, 15), "   - Bombs are special tiles that, when matched", "#ffffff");
        gui.drawText(new Position(1, 16), "     in a group of 3 or more, eliminate all the adjacent tiles", "#ffffff");
        gui.drawText(new Position(1, 18), "6. Crystal point values (highest to lowest):", "#ffffff");
        gui.drawText(new Position(3, 20), "   - DIAMOND --> 5 points", "#00ffff");
        gui.drawText(new Position(3, 21), "   - EMERALD --> 4 points", "#00ff00");
        gui.drawText(new Position(3, 22), "   - RUBY --> 3", "#ff8800");
        gui.drawText(new Position(3, 23), "   - SAPPHIRE --> 2", "#0000ff");
        gui.drawText(new Position(3, 24), "   - AMETHYST --> 1", "#ff00ff");
        gui.drawText(new Position(3, 25), "   - DEFAULT --> 1", "#aaaaaa");
        gui.drawText(new Position(1, 27), "7. Tiles combinations points:", "#ffffff");
        gui.drawText(new Position(1, 29), "   -[tile value] x [number of tiles congregated]", "#ffffff");
        gui.drawText(new Position(1, 30), "   ex: Combinations of 4 diamonds is 5 * 4 = 20", "#ffffff");
        gui.drawText(new Position(1,32), "8.  Bombs combinations points:","#ffffff");
        gui.drawText(new Position(1,34), "    -[number of consecutive bombs] + [sum of all adjacent tiles]","#ffffff");
        gui.drawText(new Position(1, 36), "9. Objective: Do the largest and highest value combinations in 90s!", "#ffffff");

        //how to play
        gui.drawText(new Position(83, 1), "HOW TO PLAY? ", "#00d327");
        gui.drawText(new Position(59, 3), "Press 'space' to SELECT the tile you want to change position", "#ffffff");
        gui.drawText(new Position(59, 5), "Then if you press...", "#ffffff");
        gui.drawText(new Position(60, 7), "'Arrow Up'    -  change with the adjacent tile on top", "#ffffff");
        gui.drawText(new Position(60, 9), "'Arrow Down'  -  change with the adjacent tile below", "#ffffff");
        gui.drawText(new Position(60, 11), "'Arrow Left'  -  change with the adjacent tile on the left", "#ffffff");
        gui.drawText(new Position(60, 13), "'Arrow Right' -  change with the adjacent tile on the right", "#ffffff");

        gui.drawText(new Position(42,38), "click 'q' to return to the menu","#ff3300");
    }
}
