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
        return isInRange(position) && board[position.getX()][position.getY()] == null;
    }

    public boolean isInRange(Position position) {
        return position.getY() < board[1].length && position.getX() < board.length
                && position.getY() >= 0 && position.getX() >= 0;
    }

    public Position findKing(PieceColour colour) {
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = this.board[i][j];
                if((piece instanceof King) && (piece.getColour() == colour)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public int rowLength() {
        return board[0].length;
    }

    public int columnLength() {
        return board.length;
    }
}