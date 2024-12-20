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
        gui.drawText(new Position(10, 5), "Welcome to Crystal Clash!", "#ffffff");
        gui.drawText(new Position(10, 7), "1. The game board is an 8x8 grid of crystals.", "#ffffff");
        gui.drawText(new Position(10, 9), "2. The crystals are: DIAMOND, EMERALD, RUGBY,", "#ffffff");
        gui.drawText(new Position(10, 10), "   SAPPHIRE, AMETHYST, and DEFAULT.", "#ffffff");
        gui.drawText(new Position(10, 12), "3. Match 3 or more identical crystals in a row or", "#ffffff");
        gui.drawText(new Position(10, 13), "   column to score points.", "#ffffff");
        gui.drawText(new Position(10, 15), "4. Larger matches score more points.", "#ffffff");
        gui.drawText(new Position(10, 17), "5. Crystal point values (highest to lowest):", "#ffffff");
        gui.drawText(new Position(12, 19), "   - DIAMOND", "#00ffff");
        gui.drawText(new Position(12, 20), "   - EMERALD", "#00ff00");
        gui.drawText(new Position(12, 21), "   - RUGBY", "#ff8800");
        gui.drawText(new Position(12, 22), "   - SAPPHIRE", "#0000ff");
        gui.drawText(new Position(12, 23), "   - AMETHYST", "#ff00ff");
        gui.drawText(new Position(12, 24), "   - DEFAULT", "#aaaaaa");
        gui.drawText(new Position(10, 26), "6. Aim for the highest score by forming the", "#ffffff");
        gui.drawText(new Position(10, 27), "   largest and highest value combinations!", "#ffffff");

        gui.drawText(new Position(42,38), "click 'q' to return to the menu","#ff3300");
    }
}
