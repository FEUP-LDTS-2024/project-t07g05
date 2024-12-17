package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Timer;

public class TimerViewer extends Viewer<Timer> {
    public TimerViewer(Timer model) { super(model); }

    @Override
    public void drawElements(GUI gui) {
        long timeLeft = getModel().getTimeLeft();
        gui.drawTextInGame(new Position(100, 12),
                "TIME LEFT:",
                "#FFFFFF");
        gui.drawTextInGame(new Position(100, 14),
                "" + getModel().getTimeLeft(),
                "#FFFFFF");
    }
}
