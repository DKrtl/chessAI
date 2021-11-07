package com.dogukan.chessai.chess;

public class Square {

    private Piece piece;
    private boolean hasPiece;

    Square(Piece piece) {
        this.piece = piece;
        hasPiece = true;
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece() {
        hasPiece = false;
        piece = null;
    }

    public void addPiece(Piece piece) {
        hasPiece = true;
        this.piece = piece;
    }
}
