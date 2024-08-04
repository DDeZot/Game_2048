package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class StartWindow extends JFrame {
    public StartWindow(){
        this.setTitle("2048");
        this.setBounds(600, 0, 220,250);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton[] buttonsForSquare = getButtonsForSquare();
        JButton[] buttonsForRectangle = getButtonsForRectangle();

        for(int i = 0; i < buttonsForSquare.length; i++){
            buttonsForSquare[i].setBackground(Color.WHITE);
            buttonsForSquare[i].setBounds(20, 30 + 40 * i, 70,30);
            this.getContentPane().add(buttonsForSquare[i]);
        }

        for(int i = 0; i < buttonsForRectangle.length; i++){
            buttonsForRectangle[i].setBackground(Color.WHITE);
            buttonsForRectangle[i].setBounds(120, 30 + 40 * i, 70,30);
            this.getContentPane().add(buttonsForRectangle[i]);
        }

        this.setVisible(true);
    }

    private JButton[] getButtonsForSquare() {
        JButton x3x3 = new JButton("3x3");
        x3x3.addActionListener(e -> {
            ViewGame game = new ViewGame(3,3);
            dispose();
        });

        JButton x4x4 = new JButton("4x4");
        x4x4.addActionListener(e -> {
            ViewGame game = new ViewGame(4,4);
            dispose();
        });

        JButton x6x6 = new JButton("6x6");
        x6x6.addActionListener(e -> {
            ViewGame game = new ViewGame(6,6);
            dispose();
        });

        JButton x8x8 = new JButton("8x8");
        x8x8.addActionListener(e -> {
            ViewGame game = new ViewGame(8,8);
            dispose();
        });

        return new JButton[]{
                x3x3, x4x4, x6x6, x8x8
        };
    }

    private JButton[] getButtonsForRectangle() {
        JButton x3x4 = new JButton("3x4");
        x3x4.addActionListener(e -> {
            ViewGame game = new ViewGame(3,4);
            dispose();
        });

        JButton x3x5 = new JButton("3x5");
        x3x5.addActionListener(e -> {
            ViewGame game = new ViewGame(3,5);
            dispose();
        });

        JButton x4x6 = new JButton("4x6");
        x4x6.addActionListener(e -> {
            ViewGame game = new ViewGame(4,6);
            dispose();
        });

        return new JButton[]{
                x3x4, x3x5, x4x6
        };
    }
}
