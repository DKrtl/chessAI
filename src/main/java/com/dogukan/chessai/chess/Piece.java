package com.dogukan.chessai.chess;

import java.util.Set;

public abstract class Piece {

    private PieceColour colour;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public abstract Set<Move> legalMoves();

    public PieceColour getColour() {
        return colour;
    }
}