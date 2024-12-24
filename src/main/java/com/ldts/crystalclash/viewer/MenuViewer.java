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

    @Override
    public void drawElements(GUI gui) {
        try {
            gui.clear();
            gui.drawGameBackground(120, 40);
            for (int i = 0; i < getModel().getNumberOptions(); i++) {
                // Ajusta a posição horizontal com base no índice
                int xPosition;
                if (i == 2) {
                    xPosition = 50; // Para i == 2
                } else if (i == 1) {
                    xPosition = 53; // Para i == 1
                } else {
                    xPosition = 54;
                }

                gui.drawText(
                        new Position(xPosition, 20 + i),
                        getModel().getSelectedOption(i),
                        getModel().isSelected(i) ? "#00FFFF" : "#FFFFFF");
            }

            gui.drawLine(44, 16, 66, 16, "*", "#5ad4f2");
            gui.drawLine(42, 17, 68, 17, "*", "#5ad4f2");//1linha horizonta de cima
            gui.drawLine(40, 18, 70, 18, "*", "#5ad4f2");//2linha horizonta de cima
            gui.drawLine(40, 25, 70, 25, "*", "#5ad4f2");//1linha horizontal de baixo
            gui.drawLine(41, 26, 69, 26, "*", "#5ad4f2");//2linha horizontal de baixo
            gui.drawLine(43, 27, 67, 27, "*", "#5ad4f2");
            gui.drawLine(40, 18, 40, 25, "*", "#5ad4f2");//linha mais a direita da esquerda
            gui.drawLine(39, 19, 39, 25, "*", "#5ad4f2");//2linha mais a direita da esquerda
            gui.drawLine(38, 20, 38, 24, "*", "#5ad4f2");//3linha mais a direita da esquerda
            gui.drawLine(37, 21, 37, 23, "*", "#5ad4f2");//4linha mais a direita da esquerda
            gui.drawLine(36, 22, 36, 22, "*", "#5ad4f2");//5linha mais a direita da esquerda
            gui.drawLine(70, 19, 70, 25, "*", "#5ad4f2");//1 linhas da direita
            gui.drawLine(71, 19, 71, 25, "*", "#5ad4f2");
            gui.drawLine(72, 20, 72, 24, "*", "#5ad4f2");
            gui.drawLine(73, 21, 73, 23, "*", "#5ad4f2");
            gui.drawLine(74, 22, 74, 22, "*", "#5ad4f2");

            gui.drawLogo(17, 5, "#f7fbfc");
            gui.refresh();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}