package com.dogukan.chessai.chess;

public class Test {

    public static void main(String[] args) {
        Game game = new Game();

        Board board = game.getBoard();

        for (int y = 0; y < board.columnLength(); y++) {
            for (int x = 0; x < board.rowLength(); x++) {
                Piece piece = board.getSquare(new Position(x, y));
                if (piece instanceof Pawn) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WP ");
                    } else {
                        System.out.print("BP ");
                    }
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println("");
        }
    }
}
