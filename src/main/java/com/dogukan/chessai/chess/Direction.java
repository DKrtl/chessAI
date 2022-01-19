package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Direction {


    public static Set<Move> northMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            upTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            downTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    public static Set<Move> southMove(Board board, Position position, PieceColour colour, int amount) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        if(colour == PieceColour.WHITE) {
            downTheBoard(board, position, colour, amount, moves, currentX, currentY);
        } else {
            upTheBoard(board, position, colour, amount, moves, currentX, currentY);
        }

        return moves;
    }

    private static void downTheBoard(Board board, Position position, PieceColour colour, int amount,
                                    Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            if(board.isEmpty(new Position(currentX, currentY + i))) {
                moves.add(new Move(position, new Position(currentX, currentY + i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX, currentY + i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX, currentY + i)));
                }
                break;
            }
        }
    }

    private static void upTheBoard(Board board, Position position, PieceColour colour, int amount,
                                     Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            if(board.isEmpty(new Position(currentX, currentY - i))) {
                moves.add(new Move(position, new Position(currentX, currentY - i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX, currentY - i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX, currentY - i)));
                }
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
            if(board.isEmpty(new Position(currentX + i, currentY))) {
                moves.add(new Move(position, new Position(currentX + i, currentY)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX + i, currentY)));
                }
                break;
            }
        }
    }

    private static void leftOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                     Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            if(board.isEmpty(new Position(currentX - i, currentY))) {
                moves.add(new Move(position, new Position(currentX - i, currentY)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX - i, currentY)));
                }
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
            if(board.isEmpty(new Position(currentX - i, currentY + i))) {
                moves.add(new Move(position, new Position(currentX - i, currentY + i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY + i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX - i, currentY + i)));
                }
                break;
            }
        }
    }

    private static void topRightOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                           Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            if(board.isEmpty(new Position(currentX + i, currentY - i))) {
                moves.add(new Move(position, new Position(currentX + i, currentY - i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY - i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX + i, currentY - i)));
                }
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
            if(board.isEmpty(new Position(currentX + i, currentY + i))) {
                moves.add(new Move(position, new Position(currentX + i, currentY + i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY + i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX + i, currentY + i)));
                }
                break;
            }
        }
    }

    private static void topLeftOnTheBoard(Board board, Position position, PieceColour colour, int amount,
                                          Set<Move> moves, int currentX, int currentY) {
        for(int i = 1; i <= amount; i++) {
            if(board.isEmpty(new Position(currentX - i, currentY - i))) {
                moves.add(new Move(position, new Position(currentX - i, currentY - i)));
            } else {
                Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY - i));
                if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
                    moves.add(new Move(position, new Position(currentX - i, currentY - i)));
                }
                break;
            }
        }
    }

    public static Piece getRightPawnPiece(Board board, Position position, Piece currentPiece) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (currentPiece.getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        }

        return piece;
    }

    public static Piece getLeftPawnPiece(Board board, Position position, Piece currentPiece) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (currentPiece.getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        }

        return piece;
    }

    public static Set<Move> enPassantRight(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece = getRightPawnPiece(board, position, currentPiece);

        if ((piece instanceof Pawn) && ((Pawn) piece).hasTookTwoSquareMove()) {
            if (currentPiece.getColour() == PieceColour.WHITE) {
                if (board.isInRange(new Position(currentX + 1, currentY - 1))
                        && board.isEmpty(new Position(currentX + 1, currentY - 1))) {
                    moves.add((new Move(position, new Position(currentX + 1, currentY - 1))));
                }
            } else {
                if (board.isInRange(new Position(currentX - 1, currentY + 1))
                        && board.isEmpty(new Position(currentX - 1, currentY + 1))) {
                    moves.add((new Move(position, new Position(currentX - 1, currentY + 1))));
                }
            }
        }

        return moves;
    }

    public static Set<Move> enPassantLeft(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece = getLeftPawnPiece(board, position, currentPiece);

        if ((piece instanceof Pawn) && ((Pawn) piece).hasTookTwoSquareMove()) {
            if (currentPiece.getColour() == PieceColour.WHITE) {
                if (board.isInRange(new Position(currentX - 1, currentY - 1))
                        && board.isEmpty(new Position(currentX - 1, currentY - 1))) {
                    moves.add((new Move(position, new Position(currentX - 1, currentY - 1))));
                }
            } else {
                if (board.isInRange(new Position(currentX + 1, currentY + 1))
                        && board.isEmpty(new Position(currentX + 1, currentY + 1))) {
                    moves.add((new Move(position, new Position(currentX + 1, currentY + 1))));
                }
            }
        }

        return moves;
    }

    public static Set<Move> takeLeft(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeLeft;

        if (currentPiece.getColour() == PieceColour.WHITE) {
            takeLeft = new Move(position, new Position(currentX - 1, currentY - 1));
        } else {
            takeLeft = new Move(position, new Position(currentX + 1, currentY + 1));
        }

        if (board.isInRange(takeLeft.getTo()) && !board.isEmpty(takeLeft.getTo()) &&
                board.getSquare(takeLeft.getTo()).getColour() == currentPiece.getColour().opponent()) {
            moves.add(takeLeft);
        }

        return moves;
    }

    public static Set<Move> takeRight(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeRight;

        if (currentPiece.getColour() == PieceColour.WHITE) {
            takeRight = new Move(position, new Position(currentX + 1, currentY - 1));
        } else {
            takeRight = new Move(position, new Position(currentX - 1, currentY + 1));
        }

        if (board.isInRange(takeRight.getTo()) && !board.isEmpty(takeRight.getTo()) &&
                board.getSquare(takeRight.getTo()).getColour() == currentPiece.getColour().opponent()) {
            moves.add(takeRight);
        }

        return moves;
    }

    public static Set<Move> oneSquareMove(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        Move oneSquareMove;

        if (currentPiece.getColour() == PieceColour.WHITE) {
            oneSquareMove = new Move(position, new Position(currentX, currentY - 1));
        } else {
            oneSquareMove = new Move(position, new Position(currentX, currentY + 1));
        }

        if (board.isInRange(oneSquareMove.getTo()) && board.isEmpty(oneSquareMove.getTo())) {
            moves.add(oneSquareMove);
        }

        return moves;
    }

    public static Set<Move> twoSquareMove(Board board, Position position, Piece currentPiece) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();
        Move twoSquareMove;

        if (((Pawn)currentPiece).isFirstMove()) {
            if (currentPiece.getColour() == PieceColour.WHITE) {
                twoSquareMove = new Move(position, new Position(currentX, currentY - 2));
            } else {
                twoSquareMove = new Move(position, new Position(currentX, currentY + 2));
            }

            if (board.isInRange(twoSquareMove.getTo()) && board.isEmpty(twoSquareMove.getTo())) {
                moves.add(twoSquareMove);
            }
        }

        return moves;
    }
}
