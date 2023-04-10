package com.zaqbit.kstra;

public class Empty extends Cell {
    public Empty(int row, int column) {
        super(row, column);
        type = CellType.EMPTY;
    }

    public void draw(Game game) {
        game.fill(200);

        final int gc = game.CIZE;
        game.rect(column * gc, row * gc, gc, gc, gc / 4);
    }
}
