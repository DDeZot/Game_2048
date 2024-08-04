package org.example.view;

import lombok.AllArgsConstructor;
import org.example.models.Grid;

import javax.swing.*;
import java.awt.*;

public class ViewScore extends JLabel implements Grid.GridListener {
    private final Grid grid;

    public ViewScore(Grid grid){
        this.grid = grid;
        this.grid.addListener(this);
        this.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        this.setForeground(new Color(70, 130, 180));
        this.setText(STR."Score: \{this.grid.sumOfAllCells}");
    }

    @Override
    public void onUpdate() {
        this.setText(STR."Score: \{this.grid.sumOfAllCells}");
        this.repaint();
    }
}
