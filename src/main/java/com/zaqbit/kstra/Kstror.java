package com.zaqbit.kstra;

public class Kstror {
    public int row;
    public int column;

    public int[] neighbors;

    public Kstror(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void draw(Game game) {
        final int gc = game.CIZE;

        game.fill(219, 112, 147);
        game.rect(column * gc, row * gc, gc, gc, gc / 4);

        game.fill(255, 102, 102);
        game.circle(column * gc + gc / 2, row * gc + gc / 2, gc / 2);
    }

    public void updateNeighbors(int[] neighbors) {
        this.neighbors = neighbors;
    }

    public void up() {
        if (neighbors[0] == CellType.EMPTY) {
            row--;
        }
    }

    public void right() {
        if (neighbors[1] == CellType.EMPTY) {
            column++;
        }
    }

    public void down() {
        if (neighbors[2] == CellType.EMPTY) {
            row++;
        }
    }

    public void left() {
        if (neighbors[3] == CellType.EMPTY) {
            column--;
        }
    }
}
