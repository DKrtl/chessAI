package com.dogukan.chessai.chess;

public class Board {

    private Piece[][] board;

    Board(Piece[][] board) {
        this.board = board;
    }

    public Piece getSquare(Position position) {
        return board[position.getX()][position.getY()];
    }

    public Piece[][] getSquares() {
        return board.clone();
    }

    public void removePiece(Position position) {
        board[position.getX()][position.getY()] = null;
    }

    public void addPiece(Position position, Piece piece) {
        board[position.getX()][position.getY()] = piece;
    }

    public boolean isEmpty(Position position) {
        return board[position.getX()][position.getY()] == null;
    }
}