package com.zaqbit.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.zaqbit.kstra.CellType;
import com.zaqbit.kstra.Maze;

public class MazeAddAppleTests {
    @Test
    public void should_create_only_a_random_apple_on_maze_creation() {
        // Arrange / Act
        Maze maze = new Maze();

        // Assert
        int appleCounter = 0;
        for (int row = 0; row < maze.rows; row++) {
            for (int column = 0; column < maze.columns; column++) {
                if (maze.cells[row][column].peek().type == CellType.APPLE) {
                    appleCounter++;
                }
            }
        }

        assertThat(appleCounter).isEqualTo(1);
    }
}
