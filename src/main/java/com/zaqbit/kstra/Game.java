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

        maze.pushApple();

        kstror = new Kstror(0, 0);
        maze.pushOnRandomPosition(kstror);
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        maze.draw(this);

        updateIfMouseIsOver(maze.portalA);
        updateIfMouseIsOver(maze.portalB);
    }

    private void updateIfMouseIsOver(Portal portal) {
        portal.mouseIsOver = (portal.x < mouseX && mouseX < portal.x + CIZE)
                && (portal.y < mouseY && mouseY < portal.y + CIZE);
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }

        if (keyCode >= 37 && keyCode <= 40) {
            handleInput();
        }
    }

    public void mousePressed() {
        maze.portalA.mouseIsLocked = maze.portalA.mouseIsOver;
        maze.portalA.xOffset = mouseX - maze.portalA.x;
        maze.portalA.yOffset = mouseY - maze.portalA.y;

        maze.portalB.mouseIsLocked = maze.portalB.mouseIsOver;
        maze.portalB.xOffset = mouseX - maze.portalB.x;
        maze.portalB.yOffset = mouseY - maze.portalB.y;
    }

    public void mouseDragged() {
        if (maze.portalA.mouseIsLocked) {
            maze.portalA.x = mouseX - maze.portalA.xOffset;
            maze.portalA.y = mouseY - maze.portalA.yOffset;
        } else if (maze.portalB.mouseIsLocked) {
            maze.portalB.x = mouseX - maze.portalB.xOffset;
            maze.portalB.y = mouseY - maze.portalB.yOffset;
        }
    }

    private void handleMouseReleased(Portal portal) {
        if (portal.mouseIsOver) {
            portal.x = 0;
            portal.y = 0;

            portal.row = (int) mouseY / CIZE;
            portal.column = (int) mouseX / CIZE;

            if (portal.row < 0) {
                portal.row = 0;
            }
            if (portal.row > maze.rows - 1) {
                portal.row = maze.rows - 1;
            }

            if (portal.column < 0) {
                portal.column = 0;
            }
            if (portal.column > maze.columns - 1) {
                portal.column = maze.columns - 1;
            }
        }
        portal.mouseIsLocked = false;
    }

    public void mouseReleased() {
        handleMouseReleased(maze.portalA);
        handleMouseReleased(maze.portalB);
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
