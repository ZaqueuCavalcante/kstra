package com.zaqbit.kstra;

public class Apple extends Cell {
    public Apple(int row, int column) {
        super(row, column);
        type = CellType.APPLE;
    }

    public void draw(Game game) {
        final int gc = game.CIZE;

        game.fill(200);
        game.rect(column * gc, row * gc, gc, gc, gc / 4);

        game.fill(144, 238, 144);
        game.circle(column * gc + gc / 2, row * gc + gc / 2, gc / 2);
    }
}
