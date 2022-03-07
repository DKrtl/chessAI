package com.dogukan.chessai.chess;

import java.util.Scanner;

public class FullGame {

    static Game game = new Game();
    static Board board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("This application starts off in 'creative mode' where the pieces can\n" +
                "be moved to the desired board setting before querying the program.");
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        System.out.println("Instructions on the commands can be prompted by typing 'help' into the\n" +
                "terminal at any time after the board is loaded.");
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        System.out.println("However, first state which piece colour you are playing as.");
        while(true) {
            System.out.println("Enter your colour as 'w' or 'b' corresponding to white and black respectively.");
            if(scanner.hasNext()) {
                String input = scanner.next();
                if(input.equals("w")) {
                    break;
                } else if(input.equals("b")) {
                    break;
                }
                generalInvalidInputMessage();
            }
        }

        makeBoard();

        while (!game.isGameOver()) {
            if (scanner.hasNext()) {
                if(scanner.nextLine().equals("help")) {
                    commandInstructions(scanner);
                } else {
                    int x1 = Integer.parseInt(scanner.next());
                    int y1 = Integer.parseInt(scanner.next());

                    int x2 = Integer.parseInt(scanner.next());
                    int y2 = Integer.parseInt(scanner.next());
                    game.move(new Move(new Position(x1, y1),
                            new Position(x2, y2)));
                }
            }
            makeBoard();
        }
    }

    private static void makeBoard() {
        board = game.getBoard();
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

    private static void commandInstructions(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void generalInvalidInputMessage() {
        System.out.println("Sorry, that's not a recognised input. Please try again.");
    }
}