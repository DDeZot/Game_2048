package org.example.view;

import lombok.AllArgsConstructor;
import org.example.models.Grid;
import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ViewLastScore extends JLabel  {
    private final JSONObject info;
    public ViewLastScore(){
        try {
            info = new JSONObject(new FileReader("src/main/resources/last_score.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        this.setForeground(new Color(70, 130, 180));
        this.setText(STR."Last score: \{info.getJSONObject("score")}");
    }

    public void refresh(int newScore) {
        this.info.put("score", newScore);
        this.setText(STR."Last score: \{info.get("score")}");
        this.repaint();
    }
}
