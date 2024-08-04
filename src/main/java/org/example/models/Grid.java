package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Grid {
    private static Random randGenerator = new Random(); //for occupyRandomCell

    private int width, height;
    private int totalCells;
    private Cell[][] cells, memory;
    private Set<Cell> vacantCells = new HashSet<>();
    private Set<Cell> vacantCellsMemory = new HashSet<>();
    private List<GridListener> listeners = new ArrayList<>();
    private int sumOfAllCellsMemory = 0;

    public int sumOfAllCells = 0;

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        this.totalCells = width * height;
        this.cells = new Cell[width][height];
        this.memory = new Cell[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                this.cells[i][j] = new Cell(i, j);
                this.memory[i][j] = new Cell(i, j);
                this.vacantCells.add(cells[i][j]);
                this.vacantCellsMemory.add(cells[i][j]);
            }
        }
    }

    public void occupyCell(int x, int y, int value){
        this.cells[x][y].occupy(value);
        this.vacantCells.remove(cells[x][y]);
        this.sumOfAllCells += value;

        this.notifyAllListeners();
    }

    public void releaseCell(int x, int y){
        this.cells[x][y].release();
        this.vacantCells.add(this.cells[x][y]);

        this.notifyAllListeners();
    }

    public void combineCells(Cell cellAcceptor, Cell cellDonor){
        cellAcceptor.combine(cellDonor);
        this.vacantCells.add(cellDonor);
        this.vacantCells.remove(cellAcceptor);

        this.notifyAllListeners();
    }

    public void occupyRandomCell() {
        int randIndex = randGenerator.nextInt(vacantCells.size());
        Cell randomCell = vacantCells.stream().skip(randIndex).findFirst().orElse(null);

        assert randomCell != null;
        this.occupyCell(randomCell.getXPos(), randomCell.getYPos(), getRandValue());
    }

    public void rollback() {
        if(this.vacantCells.equals(this.vacantCellsMemory))
            return;

        for (int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                cells[i][j].setValueContains(memory[i][j].getValueContains());
                cells[i][j].setVacant((memory[i][j].isVacant()));
                this.vacantCells.clear();
                this.vacantCells.addAll(this.vacantCellsMemory);
            }
        }
        this.sumOfAllCells = getSumOfAllCellsMemory();

        this.notifyAllListeners();
    }
    public boolean isGridFull(){
        return this.vacantCells.isEmpty();
    }

    public void saveMemory() {
        for (int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                this.memory[i][j].setValueContains(cells[i][j].getValueContains());
                this.memory[i][j].setVacant((cells[i][j].isVacant()));
                this.vacantCellsMemory.clear();
                this.vacantCellsMemory.addAll(this.vacantCells);
            }
        }
        this.sumOfAllCellsMemory = getSumOfAllCells();
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    private int getRandValue() {
        double rand = Math.random();
        if (sumOfAllCells < (128 * width * height)) {
            if (rand > 0.75)
                return 4;
            else
                return 2;
        } else if (sumOfAllCells >= (128 * width * height)) {
            if (rand >= 0.5)
                return 4;
            else
                return 2;
        }
        return 2;
    }

    private void notifyAllListeners(){
        for(GridListener g : listeners)
            g.onUpdate();
    }

    public void addListener(GridListener listener){
        this.listeners.add(listener);
    }

    public void restart() {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i][j].setValueContains(0);
                this.cells[i][j].setVacant(true);
                this.memory[i][j].setValueContains(0);
                this.memory[i][j].setVacant(true);
                this.vacantCells.add(cells[i][j]);
                this.vacantCellsMemory.add(cells[i][j]);
            }
        }

        this.sumOfAllCells = 0;

        notifyAllListeners();
    }

    public interface GridListener {
        void onUpdate();
    }
}
