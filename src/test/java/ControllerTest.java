import org.example.controllers.Controller;
import org.example.controllers.Keys;
import org.example.models.Grid;

import org.example.view.ViewGrid;
import org.junit.Test;

import static org.example.controllers.Keys.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerTest {
    Grid grid = new Grid(4, 4);
    ViewGrid viewGrid = new ViewGrid(grid);
    Controller controller = new Controller(grid);

    @Test
    public void moveTest(){
        int[][] needed = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,16}
        };

        assertEquals("Move test: ", needed, move());
    }

    @Test
    public void rollbackTest(){
        int[][] needed = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        };
        assertEquals("Rollback test: ", needed, rollback());
    }

    private int[][] move(){
        int[][] result = new int[4][4];

        for(int i = 0; i < 4; i++){
            grid.occupyCell(0, i, 2);
            grid.occupyCell(3, i, 2);
        }

        controller.move(LEFT, false);
        controller.move(RIGHT, false);
        controller.move(UP, false);
        controller.move(DOWN, false);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = grid.getCell(j,i).getValueContains();
            }
        }
        return result;
    }

    private int[][] rollback() {
        grid.occupyCell(3,3,2);
        controller.move(LEFT, false);
        controller.rollback();
        int[][] result = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = grid.getCell(j,i).getValueContains();
            }
        }
        return result;
    }

}
