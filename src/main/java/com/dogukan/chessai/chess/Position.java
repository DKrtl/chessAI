package com.dogukan.chessai.chess;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Position position = (Position) object;
        return (this == object) || (Objects.equals(x, position.x)
                && Objects.equals(y, position.y));
    }

    @Override
    public int hashCode() {
        return x * y;
    }
}
