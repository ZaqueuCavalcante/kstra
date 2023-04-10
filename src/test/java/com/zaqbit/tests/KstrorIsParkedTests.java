package com.zaqbit.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.zaqbit.kstra.Kstror;
import com.zaqbit.kstra.Maze;
import com.zaqbit.kstra.Maze00Wells5x5;

public class KstrorIsParkedTests {
    @Test
    public void should_return_true_when_the_kstror_has_not_move_options() {
        // Arrange
        Kstror kstror = new Kstror(0, 0);
        Maze maze = new Maze00Wells5x5();
        kstror.updateNeighbors(maze);

        // Assert
        boolean isParked = kstror.isParked();

        assertThat(isParked).isTrue();
    }
}
