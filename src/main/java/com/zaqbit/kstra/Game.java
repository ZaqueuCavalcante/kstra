package com.zaqbit.kstra;

import java.io.Console;

import processing.core.PApplet;

public class Game extends PApplet {
    public int CIZE;

    private Maze maze;

    private Kstror kstror;

    public void settings() {
        CIZE = 80;
        size(880, 880);

        maze = new Maze();

        kstror = new Kstror(5, 3);
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        translate(CIZE / 2, CIZE / 2);

        maze.draw(this);

        kstror.draw(this);
    }

    public void keyPressed() {
        System.out.println(key + " " + keyCode);

        if (keyCode == 10) {

        }

        if (keyCode == 38) {
            kstror.up();
        } else if (keyCode == 39) {
            kstror.right();
        } else if (keyCode == 40) {
            kstror.down();
        } else if (keyCode == 37) {
            kstror.left();
        }
    }
}
