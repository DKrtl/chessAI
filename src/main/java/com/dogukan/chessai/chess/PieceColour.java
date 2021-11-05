package com.dogukan.chessai.chess;

public enum PieceColour {
    WHITE, BLACK;

    public PieceColour opponent() {
        if(this == WHITE) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
