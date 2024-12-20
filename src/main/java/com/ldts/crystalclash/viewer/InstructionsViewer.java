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
        gui.drawText(new Position(50, 2), "INSTRUCTIONS", "#ffff00");

        gui.drawText(new Position(10, 5),  "Welcome to Crystal Clash!", "#ffffff");
        gui.drawText(new Position(10, 7),  "1. The game board is an 8x8 grid of crystals.", "#ffffff");
        gui.drawText(new Position(10, 9),  "2. The crystals are: DIAMOND, EMERALD, RUGBY,", "#ffffff");
        gui.drawText(new Position(10, 10), "   SAPPHIRE, AMETHYST, and DEFAULT.", "#ffffff");
        gui.drawText(new Position(10, 12), "3. Match 3 or more identical crystals in a row or", "#ffffff");
        gui.drawText(new Position(10, 13), "   column to score points.", "#ffffff");
        gui.drawText(new Position(10, 15), "4. Larger matches score more points.", "#ffffff");
        gui.drawText(new Position(10, 17), "5. Bombs --> @", "#ffffff");
        gui.drawText(new Position(10, 19), "   - Bombs are special tiles that, when matched", "#ffffff");
        gui.drawText(new Position(10, 20), "     in a group of 3 or more, eliminate all the adjacent tiles", "#ffffff");
        gui.drawText(new Position(10, 22), "6. Crystal point values (highest to lowest):", "#ffffff");
        gui.drawText(new Position(12, 24), "   - DIAMOND --> 5 points", "#00ffff");
        gui.drawText(new Position(12, 25), "   - EMERALD --> 4 points", "#00ff00");
        gui.drawText(new Position(12, 26), "   - RUGBY --> 3", "#ff8800");
        gui.drawText(new Position(12, 27), "   - SAPPHIRE --> 2", "#0000ff");
        gui.drawText(new Position(12, 28), "   - AMETHYST --> 1", "#ff00ff");
        gui.drawText(new Position(12, 29), "   - DEFAULT --> 1", "#aaaaaa");
        gui.drawText(new Position(10, 31), "7. Combinations points:", "#ffffff");
        gui.drawText(new Position(10, 33), "   -[tile value] x [number of tiles congregated]", "#ffffff");
        gui.drawText(new Position(10, 34), "   ex: Combinations of 4 diamonds is 5 * 4 = 20", "#ffffff");
        gui.drawText(new Position(10, 36), "8. Objective: Do the largest and highest value combinations in 90s!", "#ffffff");


        gui.drawText(new Position(42,38), "click 'q' to return to the menu","#ff3300");
    }
}
