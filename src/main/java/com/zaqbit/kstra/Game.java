package com.zaqbit.kstra;

import processing.core.PApplet;

public class Game extends PApplet {
    public int CIZE;

    private Maze maze;
    private Kstror kstror;

    public void settings() {
        maze = new Maze00Mini5x5();

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];

        kstror = new Kstror(0, 0);
        maze.pushOnRandomPosition(kstror);
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
        kstror.updateNeighbors(maze);

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

            maze.pushApple();
        }
    }
}
