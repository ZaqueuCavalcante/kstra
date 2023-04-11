package com.zaqbit.kstra;

import processing.core.PApplet;

public class Portal extends Cell {
    public int x;
    public int y;

    boolean mouseIsOver = false;
    boolean mouseIsLocked = false;
    int xOffset = 0;
    int yOffset = 0;

    public Portal(int row, int column) {
        super(row, column);
        type = CellType.PORTAL;
    }

    public void draw(Game game) {
        game.stroke(75, 0, 130);
        game.strokeWeight(16);

        final int gc = game.CIZE;

        if (x == 0 && y == 0) {
            x = column * gc;
            y = row * gc;
        }

        rectBorders(game, x, y, x + gc, y + gc, gc / 4);

        game.strokeWeight(1);
    }

    private void rectBorders(Game game, float x1, float y1, float x2, float y2, float r) {
        game.noFill();

        game.beginShape();

        game.vertex(x2 - r, y1);
        game.quadraticVertex(x2, y1, x2, y1 + r);

        game.vertex(x2, y2 - r);
        game.quadraticVertex(x2, y2, x2 - r, y2);

        game.vertex(x1 + r, y2);
        game.quadraticVertex(x1, y2, x1, y2 - r);

        game.vertex(x1, y1 + r);
        game.quadraticVertex(x1, y1, x1 + r, y1);

        game.endShape(PApplet.CLOSE);
    }
}
