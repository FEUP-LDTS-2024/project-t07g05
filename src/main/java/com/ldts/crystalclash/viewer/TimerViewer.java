package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Timer;

public class TimerViewer extends Viewer<Timer> {
    public TimerViewer(Timer model) { super(model); }

    @Override
    public void draw(LanternaGUI gui) {
        long timeLeft = getModel().getTimeLeft();
        Position TXTPOS = new Position(30, 35);
        String TXT = "Time left:\n" + timeLeft;
        System.out.println(TXT);
        gui.drawText(TXTPOS, TXT, "#F9F6EE");
    }
}
