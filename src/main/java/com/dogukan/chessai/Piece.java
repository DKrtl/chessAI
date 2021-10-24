package com.dogukan.chessai;

import java.util.Set;

public abstract class Piece {

    private static Board board = new Board();
    private PieceColour colour;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public abstract void move(Square currentSquare, Square nextSquare);

    public abstract Set<Square> legalMoves(Square currentSquare);

    public Board getBoard() {
        return board;
    }

    public PieceColour getColour() {
        return colour;
    }
}
