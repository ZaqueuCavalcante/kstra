package com.zaqbit.kstra;

public class Rock extends Cell {
    public Rock(int row, int column) {
        super(row, column);
        type = CellType.ROCK;
    }

    public void draw(Game game) {
        game.fill(160, 82, 45);

        final int gc = game.CIZE;
        game.rect(column * gc, row * gc, gc, gc, gc / 4);
    }
}
