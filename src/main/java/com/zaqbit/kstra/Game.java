package com.zaqbit.kstra;

import processing.core.PApplet;

public class Game extends PApplet {
    public int CIZE;

    private Maze maze;
    private Kstror kstror;

    public void settings() {
        maze = new Maze00Wells5x5();

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];

        maze.pushApple();

        kstror = new Kstror(0, 0);
        maze.pushOnRandomPosition(kstror);
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        maze.draw(this);

        if ((maze.portalA.x < mouseX && mouseX < maze.portalA.x + CIZE)
                && (maze.portalA.y < mouseY && mouseY < maze.portalA.y + CIZE)) {
            overPortalA = true;
        } else {
            overPortalA = false;
        }
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }

        if (keyCode >= 37 && keyCode <= 40) {
            handleInput();
        }
    }

    boolean overPortalA = false;
    boolean lockedA = false;
    int xOffset = 0;
    int yOffset = 0;

    public void mousePressed() {
        if (overPortalA) {
            lockedA = true;
        } else {
            lockedA = false;
        }
        xOffset = mouseX - maze.portalA.x;
        yOffset = mouseY - maze.portalA.y;
    }

    public void mouseDragged() {
        if (lockedA) {
            maze.portalA.x = mouseX - xOffset;
            maze.portalA.y = mouseY - yOffset;
        }
    }

    public void mouseReleased() {
        if (overPortalA) {
            maze.portalA.x = 0;
            maze.portalA.y = 0;

            maze.portalA.row = (int) mouseY / CIZE;
            maze.portalA.column = (int) mouseX / CIZE;
        }

        lockedA = false;
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
