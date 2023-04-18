package com.zaqbit.kstra;

import java.util.PriorityQueue;

public class Dijkstra {
    public Node start;
    public Node end;

    public Node[][] nodes;

    public Dijkstra(Maze maze) {
        calculate(maze);
    }

    public void calculate(Maze maze) {
        nodes = new Node[maze.rows][maze.columns];

        for (int row = 0; row < maze.rows; row++) {
            for (int column = 0; column < maze.columns; column++) {
                int id = maze.cellsIds[row][column];

                Cell cell = maze.cells[row][column].peek();

                if (cell.type == CellType.ROCK) {
                    continue;
                }
                if (cell.type == CellType.EMPTY) {
                    nodes[row][column] = new Node(id, row, column);
                }
                if (cell.type == CellType.KSTROR) {
                    start = new Node(id, row, column);
                    start.cost = 0;
                    nodes[row][column] = start;
                }
                if (cell.type == CellType.APPLE) {
                    end = new Node(id, row, column);
                    nodes[row][column] = end;
                }
            }
        }

        for (int row = 0; row < maze.rows; row++) {
            for (int column = 0; column < maze.columns; column++) {
                Node node = nodes[row][column];

                if (node == null) {
                    continue;
                }

                Node up = (row - 1 < 0) ? null : nodes[row - 1][column];
                Node right = (column + 1 >= maze.columns) ? null : nodes[row][column + 1];
                Node down = (row + 1 >= maze.rows) ? null : nodes[row + 1][column];
                Node left = (column - 1 < 0) ? null : nodes[row][column - 1];

                node.children[0] = up;
                node.children[1] = right;
                node.children[2] = down;
                node.children[3] = left;
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> Integer.compare(x.cost, y.cost));
        queue.add(start);

        boolean find = false;
        Node current = null;
        while (!find) {
            current = queue.poll();
            for (Node child : current.children) {
                if (child == null) {
                    continue;
                }
                if (child == end) {
                    find = true;
                }

                int newCost = current.cost + 1;
                if (newCost < child.cost) {
                    child.parent = current;
                    if (queue.contains(child)) {
                        queue.remove(child);
                    }
                }

                child.cost = newCost;
                queue.add(child);
            }
        }
    }

    public void draw(Game game) {
        game.stroke(0);
        game.strokeWeight(8);

        Node current = end;
        int gc = game.CIZE;
        while (current.parent != null) {
            game.line(current.column * gc + gc / 2, current.row * gc + gc / 2, current.parent.column * gc + gc / 2,
                    current.parent.row * gc + gc / 2);

            current = current.parent;
        }

        game.strokeWeight(1);
    }
}
