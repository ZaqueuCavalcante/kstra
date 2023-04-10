package com.zaqbit.kstra;

import java.util.ArrayList;

import processing.core.PApplet;

public class Game extends PApplet {
    public int CIZE;

    private Maze maze;

    private Kstror kstror;
    private Cell apple;

    public void settings() {
        CIZE = 80;
        size(880, 880);

        maze = new Maze();

        kstror = new Kstror(5, 3);
        maze.push(kstror);

        apple = new Apple(0, 0);
        maze.push(apple);
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        translate(CIZE / 2, CIZE / 2);

        maze.draw(this);
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }

        if (keyCode >= 37 && keyCode <= 40) {
            handleInput();
        }
    }

    private void handleInput() {
        kstror.updateNeighbors(maze.getNeighbors(kstror.row, kstror.column));

        if (kstror.isParked()) {
            return;
        }

        int oldRow = kstror.row;
        int oldColumn = kstror.column;

        if (keyCode == 38) {
            kstror.up();
        } else if (keyCode == 39) {
            kstror.right();
        } else if (keyCode == 40) {
            kstror.down();
        } else if (keyCode == 37) {
            kstror.left();
        }

        if (kstror.row == oldRow && kstror.column == oldColumn) {
            return;
        }

        Cell belowCell = maze.cells[kstror.row][kstror.column].peek();
        if (belowCell.type == CellType.EMPTY) {
            maze.cells[kstror.row][kstror.column].push(maze.cells[oldRow][oldColumn].pop());
        } else if (belowCell.type == CellType.APPLE) {
            maze.cells[kstror.row][kstror.column].pop();

            maze.pushRock(kstror.row, kstror.column);

            maze.cells[kstror.row][kstror.column].push(maze.cells[oldRow][oldColumn].pop());

            // Insert new apple
            ArrayList<Integer> emptyCellsIds = new ArrayList<>();
            for (int row = 0; row < maze.rows; row++) {
                for (int column = 0; column < maze.columns; column++) {
                    if (maze.cells[row][column].peek().type == CellType.EMPTY) {
                        emptyCellsIds.add(maze.cellsIds[row][column]);
                    }
                }
            }

            int index = (int) (Math.random() * emptyCellsIds.size());
            int newAppleCellId = emptyCellsIds.get(index);

            int appleNewRow = maze.getCellRow(newAppleCellId);
            int appleNewColumn = maze.getCellColumn(newAppleCellId);

            apple.row = appleNewRow;
            apple.column = appleNewColumn;
            maze.push(apple);
        }
    }
}
