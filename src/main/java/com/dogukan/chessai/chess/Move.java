package com.dogukan.chessai.chess;

import java.util.Objects;

public class Move {

    private Position from;
    private Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    private boolean isDiagonal() {
        return (manhattanDistance() % 2 == 0) && (from.getY() != to.getY()) && (from.getX() != to.getX());
    }

    private int manhattanDistance() {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    public int distance() {
        if (isDiagonal()) {
            return manhattanDistance() / 2;
        } else {
            return manhattanDistance();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Move move = (Move) object;
        return (this == object) || (Objects.equals(from, move.from)
                && Objects.equals(to, move.to));
    }

    @Override
    public int hashCode() {
        return from.hashCode() * to.hashCode();
    }
}