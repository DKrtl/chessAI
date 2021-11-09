package com.dogukan.chessai.chess;

public class Board {

    private Piece[][] board;

    Board(Piece[][] board) {
        this.board = board;
    }

//    board = new Piece[8][8];
//        for(int i = 0; i < 8; i++) {
//        for(int j = 0; j < 8; j++) {
//            if (j == 1) {
//                board[i][j] = new Pawn(PieceColour.WHITE);
//            } else if (j == 6) {
//                board[i][j] = new Pawn(PieceColour.BLACK);
//            } else {
//                board[i][j] = null;
//            }
//        }
//    }

    public Piece getSquare(Position position) {
        return board[position.getX()][position.getY()];
    }

    public Piece[][] getBoard() {
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