package com.dogukan.chessai.chess;

import java.util.Set;

public abstract class Piece {

    private PieceColour colour;
    private Square currentSquare;

    Piece(Square currentSquare, PieceColour colour) {
        this.colour = colour;
        this.currentSquare = currentSquare;
    }

    public abstract Set<Square> legalMoves();

    public PieceColour getColour() {
        return colour;
    }

    public void changeCurrentSquare(Square nextSquare) {
        currentSquare = nextSquare;
    }
}