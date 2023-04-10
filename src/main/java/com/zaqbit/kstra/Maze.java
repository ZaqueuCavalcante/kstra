package com.zaqbit.kstra;

import processing.core.PConstants;

public class Maze {
    public int rows;
    public int columns;

    public int[][] cellsIds;
    public CellStack[][] cells;

    private boolean showCellsIds;
    private boolean showCellsTypes;

    public Maze() {
        loadInitialState();
        calculateCellsIds();

        showCellsIds = false;
        showCellsTypes = false;
    }

    public int getCellRow(int cellId) {
        int cellRow = cellId / columns;

        return cellRow;
    }

    public int getCellColumn(int cellId) {
        int cellColumn = cellId % columns;

        return cellColumn;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private void loadInitialState() {
        rows = 10;
        columns = 10;

        cells = new CellStack[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new CellStack(new Empty(row, column));
            }
        }

        pushRock(3, 3);
    }

    public void pushRock(int row, int column) {
        Cell rock = new Rock(row, column);
        cells[rock.row][rock.column].push(rock);
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

    public void push(Cell cell) {
        cells[cell.row][cell.column].push(cell);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public int[] getNeighbors(int row, int column) {
        int up = (row - 1 < 0) ? CellType.OUT : cells[row - 1][column].peek().type;
        int right = (column + 1 >= columns) ? CellType.OUT : cells[row][column + 1].peek().type;
        int down = (row + 1 >= rows) ? CellType.OUT : cells[row + 1][column].peek().type;
        int left = (column - 1 < 0) ? CellType.OUT : cells[row][column - 1].peek().type;

        return new int[] { up, right, down, left };
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public void draw(Game game) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawCell(game, row, column);
                drawCellId(game, row, column);
                drawCellType(game, row, column);
            }
        }
    }

    private void drawCell(Game game, int row, int column) {
        cells[row][column].peek().draw(game);
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

    private void drawCellType(Game game, int row, int column) {
        if (!showCellsTypes) {
            return;
        }

        final int gc = game.CIZE;
        game.textSize((float) (gc * 0.35));
        game.textAlign(PConstants.CENTER);
        game.fill(0);
        game.text(cells[row][column].peek().type, column * gc + gc * 0.48f, row * gc + gc * 0.63f);
    }
}
