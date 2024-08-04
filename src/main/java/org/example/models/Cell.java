package org.example.models;

import lombok.*;

@Getter
@Setter
public class Cell {
    private int valueContains = 0;
    private boolean isVacant = true;
    private int xPos, yPos;

    public Cell(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public boolean equals(Cell cell) {
        return this.valueContains == cell.getValueContains();
    }
    public void occupy(int value){
        this.isVacant = false;
        this.valueContains = value;
    }
    public void release(){
        this.isVacant = true;
        this.valueContains = 0;
    }
    public void combine(Cell cell){
        if(cell.getXPos() == this.xPos && cell.getYPos() == this.yPos)
            return;

        this.valueContains += cell.getValueContains();
        this.isVacant = false;
        cell.release();
    }
}
