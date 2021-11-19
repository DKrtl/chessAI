package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pawn extends Piece {

    private boolean firstMove;
//    private boolean tookTwoSquareMove;
//    private Set<Move> enPassantMoves;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
//        tookTwoSquareMove = false;
    }

    @Override
    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares());
        legalMoves(newBoard, move.getFrom());

//        if (enPassantMoves.contains(move)) {
//            Position position = move.getTo();
//            int nextX = position.getX();
//            int nextY = position.getY();
//
//            if (getColour() == PieceColour.WHITE) {
//                newBoard.removePiece(new Position(nextX, nextY - 1));
//            } else {
//                newBoard.removePiece(new Position(nextX, nextY + 1));
//            }
//            newBoard.addPiece(move.getTo(), this);
//            gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));
//        } else if ()
        if (getLegalMoves().contains(move)) {
            newBoard.removePiece(move.getFrom());
            newBoard.addPiece(move.getTo(), this);
            gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));
        }
    }

    @Override
    public void legalMoves(Board board, Position position) {

        oneSquareMove(board, position);
        twoSquareMove(board, position);
        takeLeft(board, position);
        takeRight(board, position);
//        Optional<Move> enPassantLeft = enPassantLeft(board, position);
//        Optional<Move> enPassantRight = enPassantRight(board, position);

        firstMove = false;
    }

    private Optional<Move> enPassantRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        }
        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
            if (getColour() == PieceColour.WHITE) {
                if (board.isInRange(new Position(currentX + 1, currentY - 1))
                        && board.isEmpty(new Position(currentX + 1, currentY - 1))) {
                    return Optional.of(new Move(position, new Position(currentX + 1, currentY - 1)));
                } else {
                    return Optional.empty();
                }
            } else {
                if (board.isInRange(new Position(currentX - 1, currentY + 1))
                        && board.isEmpty(new Position(currentX - 1, currentY + 1))) {
                    return Optional.of(new Move(position, new Position(currentX - 1, currentY + 1)));
                } else {
                    return Optional.empty();
                }
            }
        } else {
            return Optional.empty();
        }
    }

//    private Optional<Move> enPassantLeft(Board board, Position position) {
//        int currentX = position.getX();
//        int currentY = position.getY();
//        Piece piece;
//
//        if (getColour() == PieceColour.WHITE) {
//            piece = board.getSquare(new Position(currentX - 1, currentY));
//        } else {
//            piece = board.getSquare(new Position(currentX + 1, currentY));
//        }
//
//        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
//            if (getColour() == PieceColour.WHITE) {
//                if (board.isInRange(new Position(currentX - 1, currentY - 1))
//                        && board.isEmpty(new Position(currentX - 1, currentY - 1))) {
//                    return Optional.of(new Move(position, new Position(currentX - 1, currentY - 1)));
//                } else {
//                    return Optional.empty();
//                }
//            } else {
//                if (board.isInRange(new Position(currentX + 1, currentY + 1))
//                        && board.isEmpty(new Position(currentX + 1, currentY + 1))) {
//                    return Optional.of(new Move(position, new Position(currentX + 1, currentY + 1)));
//                } else {
//                    return Optional.empty();
//                }
//            }
//        } else {
//            return Optional.empty();
//        }
//    }

    private void takeLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeLeft;

        if (getColour() == PieceColour.WHITE) {
            takeLeft = new Move(position, new Position(currentX - 1, currentY - 1));
        } else {
            takeLeft = new Move(position, new Position(currentX + 1, currentY + 1));
        }

        if (board.isInRange(takeLeft.getTo()) && !board.isEmpty(takeLeft.getTo()) &&
                board.getSquare(takeLeft.getTo()).getColour() == getColour().opponent()) {
            getLegalMoves().add(takeLeft);
        }
    }

    private void takeRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeRight;

        if (getColour() == PieceColour.WHITE) {
            takeRight = new Move(position, new Position(currentX + 1, currentY - 1));
        } else {
            takeRight = new Move(position, new Position(currentX - 1, currentY + 1));
        }

        if (board.isInRange(takeRight.getTo()) && !board.isEmpty(takeRight.getTo()) &&
                board.getSquare(takeRight.getTo()).getColour() == getColour().opponent()) {
            getLegalMoves().add(takeRight);
        }
    }

    private void oneSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        Move oneSquareMove;

        if (getColour() == PieceColour.WHITE) {
            oneSquareMove = new Move(position, new Position(currentX, currentY - 1));
        } else {
            oneSquareMove = new Move(position, new Position(currentX, currentY + 1));
        }

        if (board.isInRange(oneSquareMove.getTo()) && board.isEmpty(oneSquareMove.getTo())) {
            getLegalMoves().add(oneSquareMove);
        }
    }

    private void twoSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move twoSquareMove;

        if (firstMove) {
            if (getColour() == PieceColour.WHITE) {
                twoSquareMove = new Move(position, new Position(currentX, currentY - 2));
            } else {
                twoSquareMove = new Move(position, new Position(currentX, currentY + 2));
            }

            if (board.isInRange(twoSquareMove.getTo()) && board.isEmpty(twoSquareMove.getTo())) {
                getLegalMoves().add(twoSquareMove);
            }
        }
    }
}