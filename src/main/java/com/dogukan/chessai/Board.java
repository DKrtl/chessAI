package com.dogukan.chessai;

public class Board {

    private Square[][] board;

    Board() {
        board = new Square[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
                if (j == 1) {
                    board[i][j].setCurrentPiece(new Pawn(PieceColour.WHITE));
                } else if (j == 6) {
                    board[i][j].setCurrentPiece(new Pawn(PieceColour.BLACK));
                }
            }
        }
    }

    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    public Square[][] getBoard() {
        return board;
    }
}
