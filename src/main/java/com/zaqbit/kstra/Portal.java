package com.zaqbit.kstra;

public class Portal extends Cell {
    public Portal(int row, int column) {
        super(row, column);
        type = CellType.PORTAL;
    }

    public void draw(Game game) {
        game.fill(75, 0, 130);

        final int gc = game.CIZE;
        game.rect(column * gc, row * gc, gc, gc, gc / 4);
    }
}
