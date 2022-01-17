package com.dogukan.chessai.chess;

import java.util.Scanner;

public class Test {

    static Game game = new Game();
    static Board board = game.getBoard();

    public static void main(String[] args) {
        makeBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNext()) {
                int x1 = Integer.parseInt(scanner.next());
                int y1 = Integer.parseInt(scanner.next());

                int x2 = Integer.parseInt(scanner.next());
                int y2 = Integer.parseInt(scanner.next());
                game.move(new Move(new Position(x1, y1),
                        new Position(x2, y2)));
            }
            makeBoard();
        }
    }

    private static void makeBoard() {
        for (int y = 0; y < board.columnLength(); y++) {
            for (int x = 0; x < board.rowLength(); x++) {
                Piece piece = board.getSquare(new Position(x, y));
                if (piece instanceof Pawn) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WP ");
                    } else {
                        System.out.print("BP ");
                    }
                } else if (piece instanceof Rook) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WR ");
                    } else {
                        System.out.print("BR ");
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WB ");
                    } else {
                        System.out.print("BB ");
                    }
                } else if (piece instanceof Knight) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WK ");
                    } else {
                        System.out.print("BK ");
                    }
                } else if (piece instanceof Queen) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WQ ");
                    } else {
                        System.out.print("BQ ");
                    }
                } else if (piece instanceof King) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WKi ");
                    } else {
                        System.out.print("BKi ");
                    }
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
}