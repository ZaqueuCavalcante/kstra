package com.zaqbit.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.zaqbit.kstra.CellType;
import com.zaqbit.kstra.Maze;

public class KstrorIsParkedTests {
    @Test
    public void should_return_true_when_the_kstror_has_not_move_options() {
        // Arrange
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
