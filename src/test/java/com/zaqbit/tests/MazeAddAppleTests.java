package com.zaqbit.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.zaqbit.kstra.CellType;
import com.zaqbit.kstra.Maze;
import com.zaqbit.kstra.Maze00Mini5x5;

public class MazeAddAppleTests {
    @Test
    public void should_create_only_a_random_apple_on_maze_creation() {
        // Arrange
        Maze maze = new Maze00Mini5x5();

        // Act
        maze.pushApple();

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
