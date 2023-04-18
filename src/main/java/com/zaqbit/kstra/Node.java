package com.zaqbit.kstra;

public class Node {
    public int id;

    public int row;
    public int column;

    public int cost;

    public Node parent;

    public Node[] children;

    public Node(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;

        cost = Integer.MAX_VALUE;

        children = new Node[4];
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Node) obj).id;
    }
}
