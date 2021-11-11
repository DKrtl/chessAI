package com.dogukan.chessai.chess;

public class Test {

    public static void main(String[] args) {
        Game game = new Game();

        Piece[][] board = game.getBoard();

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] instanceof Pawn) {

                }
            }
        }
    }
}
