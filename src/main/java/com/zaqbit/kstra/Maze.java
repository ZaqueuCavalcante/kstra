package com.zaqbit.kstra;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Maze {
    public int rows;
    public int columns;

    public int[][] cellsIds;
    public CellStack[][] cells;

    private boolean showCellsIds;
    private boolean showCellsTypes;

    private Apple apple;

    public Portal portalA;
    public Portal portalB;

    public abstract int[] getDrawSizes();

    public Maze() {
        loadInitialState();
        calculateCellsIds();

        apple = new Apple(-1, -1);

        portalA = new Portal(1, 1);
        portalB = new Portal(rows - 1, columns - 1);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public int getCellRow(int cellId) {
        return cellId / columns;
    }

    public int getCellColumn(int cellId) {
        return cellId % columns;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private void loadInitialState() {
        String className = this.getClass().getSimpleName();

        File file = new File("src/mazes/" + className + ".txt");
        InputStream input;
        try {
            input = new FileInputStream(file);

            String[] lines = PApplet.loadStrings(input);
            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].replaceAll(" ", "");
            }

            rows = lines.length;
            columns = lines[0].length();

            cells = new CellStack[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    int value = Integer.parseInt(String.valueOf(lines[row].charAt(column)));
                    cells[row][column] = new CellStack(new Empty(row, column));
                    if (value == CellType.ROCK) {
                        cells[row][column].push(new Rock(row, column));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void pushRock(int row, int column) {
        Cell rock = new Rock(row, column);
        cells[rock.row][rock.column].push(rock);
    }

    public void pushOnRandomPosition(Kstror kstror) {
        ArrayList<Integer> emptyCellsIds = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cells[row][column].peek().type == CellType.EMPTY) {
                    emptyCellsIds.add(cellsIds[row][column]);
                }
            }
        }

        int index = (int) (Math.random() * emptyCellsIds.size());
        int newKstrorCellId = emptyCellsIds.get(index);

        kstror.row = getCellRow(newKstrorCellId);
        kstror.column = getCellColumn(newKstrorCellId);
        push(kstror);
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

    public void pushApple() {
        ArrayList<Integer> emptyCellsIds = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cells[row][column].peek().type == CellType.EMPTY) {
                    emptyCellsIds.add(cellsIds[row][column]);
                }
            }
        }

        int index = (int) (Math.random() * emptyCellsIds.size());
        int newAppleCellId = emptyCellsIds.get(index);

        apple.row = getCellRow(newAppleCellId);
        apple.column = getCellColumn(newAppleCellId);
        push(apple);
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

        portalA.draw(game);
        portalB.draw(game);
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
