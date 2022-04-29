package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Direction {

    public static Set<Move> northMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();

        if(colour == PieceColour.WHITE) {
            upTheBoard(board, position, colour, amount, moves);
        } else {
            downTheBoard(board, position, colour, amount, moves);
        }

        return moves;
    }

    public static Set<Move> southMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();

        if(colour == PieceColour.WHITE) {
            downTheBoard(board, position, colour, amount, moves);
        } else {
            upTheBoard(board, position, colour, amount, moves);
        }

        return moves;
    }

    private static void downTheBoard(Board board, Position position, PieceColour colour, int amount, Set<Move> moves) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(position.getX(), position.getY() + i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    private static void upTheBoard(Board board, Position position, PieceColour colour, int amount, Set<Move> moves) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(position.getX(), position.getY() - i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    public static Set<Move> eastMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            rightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            leftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    public static Set<Move> westMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            leftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            rightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    private static void rightOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                    Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX + i, currentY);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    private static void leftOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                     Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX - i, currentY);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    public static Set<Move> northEastMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            topRightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            bottomLeftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    public static Set<Move> southWestMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            bottomLeftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            topRightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    private static void bottomLeftOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                             Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX - i, currentY + i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    private static void topRightOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                           Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX + i, currentY - i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    public static Set<Move> southEastMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            bottomRightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            topLeftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    public static Set<Move> northWestMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            topLeftOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            bottomRightOnTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    private static void bottomRightOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                              Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX + i, currentY + i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    private static void topLeftOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                          Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            Position to = new Position(currentX - i, currentY - i);
            if (createMove(board, position, colour, moves, to)) {
                break;
            }
        }
    }

    private static boolean createMove(Board board, Position position, PieceColour colour, Set<Move> moves,
                                      Position to) {
        if(board.isInRange(to)) {
            if(board.isEmpty(to)) {
                moves.add(new Move(position, to));
            } else {
                Piece blockingPiece = board.getSquare(to);
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, to));
                }
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
}
