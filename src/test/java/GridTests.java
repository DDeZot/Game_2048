import org.example.models.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@SuppressWarnings("deprecation")
public class GridTests {
    Grid grid = new Grid(4, 4);
    @Test
    public void occupyAllCellsTest(){
        assertTrue("Occupy all cells test: ", occupyAllCells());
    }

    @Test
    public void checkGridTest() {
        int[][] needed = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertEquals("Check grid test: ", needed, checkGrid());
    }

    @Test
    public void checkGridMemoryTest() {
        int[][] needed = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertEquals("Check grid test: ", needed, checkGridMemory());
    }

    @Test
    public void occupyCellTest() {
        int[][] needed = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2}
        };
        assertEquals("Occupy cell test: ", needed, occupyCell());
    }

    private boolean occupyAllCells(){
        for(int i = 0; i < 16; i++){
            grid.occupyRandomCell();
        }
        return grid.isGridFull();
    }

    private int[][] checkGrid() {
        int[][] result = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = grid.getCell(i, j).getValueContains();
            }
        }
        return result;
    }

    private int[][] checkGridMemory() {
        int[][] result = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = grid.getMemory()[i][j].getValueContains();
            }
        }
        return result;
    }

    private int[][] occupyCell(){
        int[][] result = new int[4][4];
        grid.occupyCell(3,3, 2);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = grid.getCell(i, j).getValueContains();
            }
        }
        return result;
    }
}
