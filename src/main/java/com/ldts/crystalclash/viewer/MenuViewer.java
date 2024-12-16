package com.ldts.crystalclash.viewer;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;


public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu model) {
        super(model);
    }

    public void draw(LanternaGUI gui) {
        try {
            gui.clear();
            for (int i = 0; i < getModel().getNumberOptions(); i++)
                gui.drawText(
                        new Position(52, 20 + i),
                        getModel().getSelectedOption(i),
                        getModel().isSelected(i) ? "#00FFFF" : "#FFFFFF");



            gui.drawLine(41, 17, 69, 17, "*", "#FFA500");//1linha horizonta de cima
            gui.drawLine(40, 18, 70, 18, "*", "#FFA500");//2linha horizonta de cima
            gui.drawLine(40, 24, 70, 24, "*", "#FFA500");//1linha horizontal de baixo
            gui.drawLine(41, 25, 69, 25, "*", "#FFA500");//2linha horizontal de baixo
            gui.drawLine(40, 18, 40, 24, "*", "#FFA500");//linha mais a direita da esquerda
            gui.drawLine(39, 18, 39, 24, "*", "#FFA500");//2linha mais a direita da esquerda
            gui.drawLine(38, 19, 38, 23, "*", "#FFA500");//3linha mais a direita da esquerda
            gui.drawLine(37, 20, 37, 22, "*", "#FFA500");//4linha mais a direita da esquerda
            gui.drawLine(36, 21, 36, 21, "*", "#FFA500");//5linha mais a direita da esquerda
            gui.drawLine(70, 18, 70, 24, "*", "#FFA500");//1 linhas da direita
            gui.drawLine(71, 18, 71, 24, "*", "#FFA500");
            gui.drawLine(72, 19, 72, 23, "*", "#FFA500");
            gui.drawLine(73, 20, 73, 22, "*", "#FFA500");
            gui.drawLine(74, 21, 74, 21, "*", "#FFA500");

            gui.drawLogo(17, 5, "#FFD700");
            gui.refresh();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}