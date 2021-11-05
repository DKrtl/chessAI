package com.dogukan.chessai.chess;

public class Move {

    private Square startSquare;
    private Square endSquare;

    public Move(Square startSquare, Square endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }
}