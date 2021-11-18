package com.dogukan.chessai.chess;

public class Board {

    private Piece[][] board;

    Board(Piece[][] board) {
        this.board = board;
    }

    public Piece getSquare(Position position) {
        if (isInRange(position)) {
            return board[position.getX()][position.getY()];
        } else {
            return null;
        }
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

    public boolean isInRange(Position position) {
        return position.getY() < board[1].length && position.getX() < board.length
                && position.getY() >= 0 && position.getX() >= 0;
    }

    public int rowLength() {
        return board[0].length;
    }

    public int columnLength() {
        return board.length;
    }
}