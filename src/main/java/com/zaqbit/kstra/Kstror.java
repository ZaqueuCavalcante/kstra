package com.zaqbit.kstra;

public class Kstror {
    public int row;
    public int column;

    public int[][] options;

    public Kstror(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void draw(Game game) {
        game.fill(219, 112, 147);
        game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
    }

    public void up() {
        row--;
    }

    public void right() {
        column++;
    }

    public void down() {
        row++;
    }

    public void left() {
        column--;
    }
}
