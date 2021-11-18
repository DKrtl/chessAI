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

//                for (Move m : game.getBoard().getSquare(new Position(x1, y1)).getLegalMoves()) {
//                    System.out.println("(" + m.getTo().getX() + "," + m.getTo().getY() + ")");
//                }
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
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
}