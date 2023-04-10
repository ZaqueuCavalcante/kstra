package com.zaqbit.kstra;

public abstract class Cell {
    public int row;
    public int column;

    public int type;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public abstract void draw(Game game);
}
