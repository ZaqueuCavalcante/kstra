package com.zaqbit.kstra;

public class Maze {
    public int rows;
    public int columns;

    public int[][] cells;
    public int[][] cellsIds;

    public Maze() {
        loadInitialState();
        calculateCellsIds();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private void loadInitialState() {
        rows = 10;
        columns = 10;

        cells = new int[rows][columns];
    }

    private void calculateCellsIds() {
        int id = 0;
        cellsIds = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cellsIds[row][column] = id;
                id++;
            }
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private boolean isEmpty(int row, int column) {
        return cells[row][column] == CellType.EMPTY;
    }

    private boolean isRock(int row, int column) {
        return cells[row][column] == CellType.ROCK;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public void draw(Game game) {
        game.translate(game.CIZE / 2, game.CIZE / 2);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawCell(game, row, column);
            }
        }
    }

    private void drawCell(Game game, int row, int column) {
        if (isEmpty(row, column)) {
            game.fill(200);
        }
        if (isRock(row, column)) {
            game.fill(0, 128, 0);
        }

        game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
    }
}
