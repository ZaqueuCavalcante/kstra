package com.zaqbit.kstra;

import processing.core.PApplet;

public class Game extends PApplet {
    public int CIZE;

    private Maze maze;

    public void settings() {
        CIZE = 80;
        size(880, 880);

        maze = new Maze();
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        maze.draw(this);
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }
    }
}
