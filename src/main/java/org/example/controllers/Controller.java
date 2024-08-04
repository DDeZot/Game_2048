package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.example.models.Grid;

import static org.example.controllers.Keys.*;

@AllArgsConstructor
@Setter
public class Controller {
    private Grid grid;

    public boolean move(int direction, boolean justCheck) {
        boolean wasSaved = false; //for save grid memory

        int width = grid.getWidth();
        int height = grid.getHeight();

        int startX, startY, endX, endY, stepX, stepY;

        switch (direction) {
            case LEFT:
                startX = 1; startY = 0; endX = width; endY = height; stepX = 1; stepY = 1;
                break;
            case RIGHT:
                startX = width - 2; startY = 0; endX = -1; endY = height; stepX = -1; stepY = 1;
                break;
            case UP:
                startX = 0; startY = 1; endX = width; endY = height; stepX = 1; stepY = 1;
                break;
            case DOWN:
                startX = 0; startY = height - 2; endX = width; endY = -1; stepX = 1; stepY = -1;
                break;
            default:
                return false;
        }

        for (int i = startX; i != endX; i += stepX) {
            for (int j = startY; j != endY; j += stepY) {
                if (grid.getCell(i, j).isVacant())
                    continue;

                int newPosition = (direction == LEFT || direction == RIGHT) ? i : j;
                for (int k = (direction == LEFT || direction == UP) ? newPosition - 1 : newPosition + 1;
                     (direction == LEFT || direction == UP) ? k >= 0 : k < (direction == LEFT || direction == RIGHT ? width : height);
                     k += (direction == LEFT || direction == UP) ? -1 : 1) {

                    int targetX = (direction == LEFT || direction == RIGHT) ? k : i;
                    int targetY = (direction == UP || direction == DOWN) ? k : j;

                    if (grid.getCell(targetX, targetY).isVacant() || grid.getCell(targetX, targetY).equals(grid.getCell(i, j))) {
                        if (justCheck)
                            return true;

                        if (!wasSaved) {
                            grid.saveMemory();
                            wasSaved = true;
                        }

                        newPosition += (direction == LEFT || direction == UP) ? -1 : 1;
                    } else {
                        break;
                    }
                }

                if (newPosition != (direction == LEFT || direction == RIGHT ? i : j)) {
                    int targetX = (direction == LEFT || direction == RIGHT) ? newPosition : i;
                    int targetY = (direction == UP || direction == DOWN) ? newPosition : j;
                    grid.combineCells(grid.getCell(targetX, targetY), grid.getCell(i, j));
                }
            }
        }

        if(wasSaved) {
            grid.occupyRandomCell();
        }

        return wasSaved;
    }

    public void rollback(){
        if(this.grid.getVacantCellsMemory().size() == this.grid.getTotalCells())
            return;

        this.grid.rollback();
    }

    public void restart() {
        this.grid.restart();
        this.grid.occupyRandomCell();
    }
}
