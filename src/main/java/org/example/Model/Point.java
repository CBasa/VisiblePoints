package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    private double x;
    private double y;
    private int number;
    private Direction direction;

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }
}
