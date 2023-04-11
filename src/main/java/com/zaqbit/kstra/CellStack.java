package com.zaqbit.kstra;

import java.util.Stack;

public class CellStack {
    private Stack<Cell> cells;

    public CellStack(Cell cell) {
        cells = new Stack<>();
        cells.push(cell);
    }

    public void push(Cell cell) {
        cells.push(cell);
    }

    public Cell peek() {
        return cells.peek();
    }

    public Cell penult() {
        return cells.elementAt(cells.size() - 2);
    }

    public Cell pop() {
        return cells.pop();
    }
}
