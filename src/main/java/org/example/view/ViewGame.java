package org.example.view;

import org.example.controllers.Controller;
import org.example.controllers.Keys;
import org.example.models.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewGame extends JFrame {
    Grid grid;
    Controller controller;
    public ViewGame(int width, int height){
        this.grid = new Grid(width, height);
        ViewGrid viewGrid = new ViewGrid(grid);
        ViewScore score = new ViewScore(grid);
        ViewGameOver gameOver = new ViewGameOver();
        this.controller = new Controller(grid);

        this.grid.occupyRandomCell();

        this.setLayout(null);
        this.setBounds(600, 0, viewGrid.getWidth(), viewGrid.getHeight() + 150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        gameOver.setBounds(0, 0, viewGrid.getWidth(), viewGrid.getHeight());

        viewGrid.setBounds(0, 0, viewGrid.getWidth(), viewGrid.getHeight());
        viewGrid.setFocusable(true);
        viewGrid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(!isGameOver()) {
                    controller.move(e.getKeyCode(), false);
                }
                else {
                    gameOver.setVisible(true);
                    repaint();
                }
            }
        });

        score.setBounds(viewGrid.getWidth() * 5/16, viewGrid.getHeight(), 200, 50);
        score.setFocusable(false);

        JButton rollback = new JButton(new ImageIcon("src/main/resources/back.png"));
        rollback.setBounds(viewGrid.getWidth() * 3/4, viewGrid.getHeight() + 10, 64, 64);
        rollback.addActionListener(e -> {
            controller.rollback();
            gameOver.setVisible(false);
        });
        rollback.setBorder(null);

        JButton restart = new JButton(new ImageIcon("src/main/resources/restart.png"));
        restart.setBounds(viewGrid.getWidth()/4 - 64, viewGrid.getHeight() + 10, 64, 64);
        restart.addActionListener(e -> {
            controller.restart();
            gameOver.setVisible(false);
        });
        restart.setBorder(null);

        JButton mainMenu = new JButton("Menu");
        mainMenu.setBackground(Color.PINK);
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        mainMenu.setBounds(viewGrid.getWidth() * 5/16, viewGrid.getHeight() + 60, viewGrid.getWidth()/3, 50);
        mainMenu.addActionListener(e -> {
            StartWindow window = new StartWindow();
            dispose();
        });

        this.getContentPane().add(gameOver);
        this.getContentPane().add(viewGrid);
        this.getContentPane().add(rollback);
        this.getContentPane().add(restart);
        this.getContentPane().setBackground(Color.WHITE);
        this.getContentPane().add(score);
        this.getContentPane().add(mainMenu);
        this.setVisible(true);

        rollback.setFocusable(false);
        restart.setFocusable(false);
        viewGrid.requestFocusInWindow();
    }

    private boolean isGameOver() {
        if(grid.isGridFull())
            return !(controller.move(Keys.LEFT, true) || controller.move(Keys.RIGHT, true) || controller.move(Keys.UP, true) || controller.move(Keys.DOWN, true));
        return false;
    }
}
