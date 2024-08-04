package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ViewGameOver extends JComponent {
    private Image img;

    public ViewGameOver(){
        this.img = new ImageIcon("src/main/resources/sad_sponge.jpg").getImage();

        this.setFocusable(false);
        this.setVisible(false);
    }
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(255, 160, 122, 127));
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        g.drawString("GAME OVER!", this.getWidth()/4, this.getHeight() * 15/16);

        g.drawImage(img, getWidth()/2-img.getWidth(null)/2, 30, null);
    }
}
