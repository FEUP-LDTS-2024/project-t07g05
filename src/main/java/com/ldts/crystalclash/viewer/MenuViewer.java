package com.ldts.crystalclash.viewer;
import com.googlecode.lanterna.TextColor;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;


public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu model) {
        super(model);
    }

    public void drawElements(GUI gui) {
        try {
            gui.clear();
            gui.drawGameBackground(120,40);
            for (int i = 0; i < getModel().getNumberOptions(); i++)
                gui.drawText(
                        new Position(52, 20 + i),
                        getModel().getSelectedOption(i),
                        getModel().isSelected(i) ? "#00FFFF" : "#FFFFFF");

            gui.drawLine(41, 17, 69, 17, "*", "#5ad4f2");//1linha horizonta de cima
            gui.drawLine(40, 18, 70, 18, "*", "#5ad4f2");//2linha horizonta de cima
            gui.drawLine(40, 24, 70, 24, "*", "#5ad4f2");//1linha horizontal de baixo
            gui.drawLine(41, 25, 69, 25, "*", "#5ad4f2");//2linha horizontal de baixo
            gui.drawLine(40, 18, 40, 24, "*", "#5ad4f2");//linha mais a direita da esquerda
            gui.drawLine(39, 18, 39, 24, "*", "#5ad4f2");//2linha mais a direita da esquerda
            gui.drawLine(38, 19, 38, 23, "*", "#5ad4f2");//3linha mais a direita da esquerda
            gui.drawLine(37, 20, 37, 22, "*", "#5ad4f2");//4linha mais a direita da esquerda
            gui.drawLine(36, 21, 36, 21, "*", "#5ad4f2");//5linha mais a direita da esquerda
            gui.drawLine(70, 18, 70, 24, "*", "#5ad4f2");//1 linhas da direita
            gui.drawLine(71, 18, 71, 24, "*", "#5ad4f2");
            gui.drawLine(72, 19, 72, 23, "*", "#5ad4f2");
            gui.drawLine(73, 20, 73, 22, "*", "#5ad4f2");
            gui.drawLine(74, 21, 74, 21, "*", "#5ad4f2");

            gui.drawLogo(17, 5, "#f7fbfc");
            gui.refresh();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}