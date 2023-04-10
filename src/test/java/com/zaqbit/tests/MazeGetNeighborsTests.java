package com.zaqbit.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.zaqbit.kstra.CellType;
import com.zaqbit.kstra.Maze;
import com.zaqbit.kstra.Maze00Mini5x5;

public class MazeGetNeighborsTests {
    @Test
    public void should_get_correct_neighbors_for_cell_0_0() {
        // Arrange
        Maze maze = new Maze00Mini5x5();

        // Act
        int[] neighbors = maze.getNeighbors(0, 0);

        // Assert
        assertThat(neighbors[0]).isEqualTo(CellType.OUT);
        assertThat(neighbors[1]).isEqualTo(CellType.EMPTY);
        assertThat(neighbors[2]).isEqualTo(CellType.EMPTY);
        assertThat(neighbors[3]).isEqualTo(CellType.OUT);
    }

    @Test
    public void should_get_correct_neighbors_for_cell_1_1() {
        // Arrange
        Maze maze = new Maze00Mini5x5();

        // Act
        int[] neighbors = maze.getNeighbors(1, 1);

        // Assert
        assertThat(neighbors[0]).isEqualTo(CellType.EMPTY);
        assertThat(neighbors[1]).isEqualTo(CellType.ROCK);
        assertThat(neighbors[2]).isEqualTo(CellType.EMPTY);
        assertThat(neighbors[3]).isEqualTo(CellType.EMPTY);
    }

    @Test
    public void should_get_correct_neighbors_for_cell_2_2() {
        // Arrange
        Maze maze = new Maze00Mini5x5();

        // Act
        int[] neighbors = maze.getNeighbors(2, 2);

        // Assert
        assertThat(neighbors[0]).isEqualTo(CellType.ROCK);
        assertThat(neighbors[1]).isEqualTo(CellType.EMPTY);
        assertThat(neighbors[2]).isEqualTo(CellType.ROCK);
        assertThat(neighbors[3]).isEqualTo(CellType.EMPTY);
    }
}
