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

        maze.portalA.mouseIsOver = mouseIsOver(maze.portalA);
        maze.portalB.mouseIsOver = mouseIsOver(maze.portalB);
    }

    private boolean mouseIsOver(Portal portal) {
        return (portal.x < mouseX && mouseX < portal.x + CIZE)
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
        if (maze.portalA.mouseIsOver) {
            maze.portalA.mouseIsLocked = true;
        } else {
            maze.portalA.mouseIsLocked = false;
        }
        maze.portalA.xOffset = mouseX - maze.portalA.x;
        maze.portalA.yOffset = mouseY - maze.portalA.y;

        if (maze.portalB.mouseIsOver) {
            maze.portalB.mouseIsLocked = true;
        } else {
            maze.portalB.mouseIsLocked = false;
        }
        maze.portalB.xOffset = mouseX - maze.portalB.x;
        maze.portalB.yOffset = mouseY - maze.portalB.y;
    }

    public void mouseDragged() {
        if (maze.portalA.mouseIsLocked) {
            maze.portalA.x = mouseX - maze.portalA.xOffset;
            maze.portalA.y = mouseY - maze.portalA.yOffset;
        }

        if (maze.portalB.mouseIsLocked) {
            maze.portalB.x = mouseX - maze.portalB.xOffset;
            maze.portalB.y = mouseY - maze.portalB.yOffset;
        }
    }

    public void mouseReleased() {
        if (maze.portalA.mouseIsOver) {
            maze.portalA.x = 0;
            maze.portalA.y = 0;

            maze.portalA.row = (int) mouseY / CIZE;
            maze.portalA.column = (int) mouseX / CIZE;

            if (maze.portalA.row < 0) {
                maze.portalA.row = 0;
            }
            if (maze.portalA.row > maze.rows - 1) {
                maze.portalA.row = maze.rows - 1;
            }

            if (maze.portalA.column < 0) {
                maze.portalA.column = 0;
            }
            if (maze.portalA.column > maze.columns - 1) {
                maze.portalA.column = maze.columns - 1;
            }
        }
        maze.portalA.mouseIsLocked = false;

        //

        if (maze.portalB.mouseIsOver) {
            maze.portalB.x = 0;
            maze.portalB.y = 0;

            maze.portalB.row = (int) mouseY / CIZE;
            maze.portalB.column = (int) mouseX / CIZE;

            if (maze.portalB.row < 0) {
                maze.portalB.row = 0;
            }
            if (maze.portalB.row > maze.rows - 1) {
                maze.portalB.row = maze.rows - 1;
            }

            if (maze.portalB.column < 0) {
                maze.portalB.column = 0;
            }
            if (maze.portalB.column > maze.columns - 1) {
                maze.portalB.column = maze.columns - 1;
            }
        }
        maze.portalB.mouseIsLocked = false;
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
