package com.dogukan.chessai.chess;

import java.util.Objects;

public class Move {

    private Square from;
    private Square to;

    public Move(Square from, Square to) {
        this.from = from;
        this.to = to;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
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
        return 1;
    }
}