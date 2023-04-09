package com.zaqbit.kstra;

import processing.core.PConstants;

public class Maze {
    public int rows;
    public int columns;

    public int[][] cells;
    public int[][] cellsIds;

    private boolean showCellsIds;

    public Maze() {
        loadInitialState();
        calculateCellsIds();

        showCellsIds = false;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private void loadInitialState() {
        rows = 10;
        columns = 10;

        cells = new int[rows][columns];

        cells[3][3] = CellType.ROCK;
        cells[4][5] = CellType.ROCK;
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

    public int[] getNeighbors(int row, int column) {
        int up = (row - 1 < 0) ? CellType.OUT : cells[row - 1][column];
        int right = (column + 1 >= columns) ? CellType.OUT : cells[row][column + 1];
        int down = (row + 1 >= rows) ? CellType.OUT : cells[row + 1][column];
        int left = (column - 1 < 0) ? CellType.OUT : cells[row][column - 1];

        return new int[] { up, right, down, left };
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private boolean isRock(int row, int column) {
        return cells[row][column] == CellType.ROCK;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public void draw(Game game) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawCell(game, row, column);
                drawCellId(game, row, column);
            }
        }
    }

    private void drawCell(Game game, int row, int column) {
        if (isRock(row, column)) {
            game.fill(160, 82, 45);
        } else {
            game.fill(200);
        }

        game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
    }

    private void drawCellId(Game game, int row, int column) {
        if (!showCellsIds) {
            return;
        }

        final int gc = game.CIZE;
        game.textSize((float) (gc * 0.35));
        game.textAlign(PConstants.CENTER);
        game.fill(0);
        game.text(cellsIds[row][column], column * gc + gc * 0.48f, row * gc + gc * 0.63f);
    }
}
