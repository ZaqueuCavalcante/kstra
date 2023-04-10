package com.zaqbit.kstra;

public class Kstror extends Cell {
    public int[] neighbors;

    public Kstror(int row, int column) {
        super(row, column);
        type = CellType.KSTROR;
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

    public boolean isParked() {
        for (int i : neighbors) {
            if (i == CellType.EMPTY || i == CellType.APPLE) {
                return false;
            }
        }
        return true;
    }

    public void up() {
        if (neighbors[0] == CellType.EMPTY || neighbors[0] == CellType.APPLE) {
            row--;
        }
    }

    public void right() {
        if (neighbors[1] == CellType.EMPTY || neighbors[1] == CellType.APPLE) {
            column++;
        }
    }

    public void down() {
        if (neighbors[2] == CellType.EMPTY || neighbors[2] == CellType.APPLE) {
            row++;
        }
    }

    public void left() {
        if (neighbors[3] == CellType.EMPTY || neighbors[3] == CellType.APPLE) {
            column--;
        }
    }
}
