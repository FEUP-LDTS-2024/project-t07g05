package com.ldts.crystalclash.viewer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.Viewer;


public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(LanternaGUI gui) {
        gui.clear();
        try {

            gui.drawLine(40, 18, 70, 18, "*", "#000080");//2linha de
            gui.drawLine(40, 24, 70, 24, "*", "#000080"); //2linha de baixo
            gui.drawLine(41, 25, 69, 25, "*", "#000080");
            gui.drawLine(40, 24, 40, 18, "*", "#000080");//linha da esquerda
            gui.drawLine(39, 24, 39, 18, "*", "#000080");//2linda da esquerda
            gui.drawLine(38, 23, 38, 19, "*", "#000080");
            gui.drawLine(37, 22, 37, 20, "*", "#000080");
            gui.drawLine(36, 21, 36, 21, "*", "#000080");
            gui.drawLine(70, 24, 70, 18, "*", "#000080");//linhas da direita
            gui.drawLine(71, 24, 71, 18, "*", "#000080");
            gui.drawLine(72, 23, 72, 19, "*", "#000080");
            gui.drawLine(73, 22, 73, 20, "*", "#000080");
            gui.drawLine(74, 21, 74, 21, "*", "#000080");


            gui.drawLogo(5, 2, "#FFD700");

        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro no console para depuração
            throw new RuntimeException(e);
        }
    }
}