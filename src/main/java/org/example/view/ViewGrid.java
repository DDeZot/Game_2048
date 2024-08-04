package org.example.view;

import org.example.models.Grid;

import javax.swing.*;
import java.awt.*;

public class ViewGrid extends JComponent implements Grid.GridListener {
    private final Grid grid;
    private final int cellSize;
    private final int borderSize;
    private final int arcSize;
    private final int fontSize;
    private final int shiftSize;

    public ViewGrid(Grid grid){
        this.grid = grid;
        this.cellSize = 460 / grid.getHeight();
        this.borderSize = cellSize / 6;
        this.arcSize = cellSize / 6;
        this.fontSize = cellSize / 4;
        this.shiftSize = cellSize / 20;;
        this.setSize(grid.getWidth() * (borderSize + cellSize) + borderSize * 2, grid.getHeight() * (borderSize + cellSize) + borderSize * 4);
        this.grid.addListener(this);
    }

    public void paint(Graphics g) {
        Font numFont = new Font(Font.SERIF, Font.PLAIN, fontSize);
        Color gridColor = new Color(224, 255, 255);

        g.setColor(gridColor);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        for(int i = 0; i < grid.getWidth(); i++){
            int x = borderSize * (i + 1) + cellSize * i;

            for(int j = 0; j < grid.getHeight(); j++){
                int y = borderSize * (j + 1) + cellSize * j;
                int digitsInValue = (int) Math.log10(grid.getCell(i, j).getValueContains()) + 1;

                //drawing cell
                g.setColor(getColorByValue(grid.getCell(i, j).getValueContains()));
                g.fillRoundRect(x, y, cellSize, cellSize, arcSize, arcSize);

                //drawing value on cell
                g.setColor(Color.white);
                g.setFont(numFont);
                g.drawString(Integer.toString(grid.getCell(i, j).getValueContains()), x + cellSize / 2 - shiftSize * digitsInValue, y + cellSize * 3/8 + fontSize);
            }
        }
    }

    private Color getColorByValue(int value){
        if(value == 0)
            return new Color(176, 196, 222);

        try {
            return new Color(255, (int) (228-15*(Math.log(value)/Math.log(2))), (int) (184-30*(Math.log(value)/Math.log(4))));
        } catch (Exception e) {
            return Color.MAGENTA;
        }
    }

    @Override
    public void onUpdate() {
        this.repaint();
        this.setFocusable(true);
    }
}
