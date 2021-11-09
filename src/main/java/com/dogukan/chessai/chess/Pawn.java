package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pawn extends Piece {

    private boolean firstMove;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> legalMoves = new HashSet<>();

        Optional<Move> oneSquareMove = oneSquareMove(board, position);
        Optional<Move> twoSquareMove = twoSquareMove(board, position);
        Optional<Move> takeLeft = takeLeft(board, position);
        Optional<Move> takeRight = takeRight(board, position);

        oneSquareMove.ifPresent(legalMoves::add);
        twoSquareMove.ifPresent(legalMoves::add);
        takeLeft.ifPresent(legalMoves::add);
        takeRight.ifPresent(legalMoves::add);

        return legalMoves;
    }

//        boolean pawnOnLeft;
//        boolean pawnOnRight;
//
//        if (super.getColour() == PieceColour.WHITE) {
//            takeLeft = getBoard().getSquare(currentX - 1, currentY + 1);
//            takeRight = getBoard().getSquare(currentX + 1, currentY + 1);
//            pawnOnLeft = getBoard().getSquare(currentX - 1, currentY).getCurrentPiece() instanceof Pawn;
//            pawnOnRight = getBoard().getSquare(currentX + 1, currentY).getCurrentPiece() instanceof Pawn;
//        } else {
//            takeLeft = getBoard().getSquare(currentX + 1, currentY - 1);
//            takeRight = getBoard().getSquare(currentX - 1, currentY - 1);
//            pawnOnLeft = getBoard().getSquare(currentX + 1, currentY).getCurrentPiece() instanceof Pawn;
//            pawnOnRight = getBoard().getSquare(currentX - 1, currentY).getCurrentPiece() instanceof Pawn;
//        }
//        if (!takeLeft.isEmpty() || pawnOnLeft && !takeLeft.isEmpty()) {
//            legalMoves.add(takeLeft);
//        }
//        if (!takeRight.isEmpty() || pawnOnRight && takeRight.isEmpty()) {
//            legalMoves.add(takeRight);
//        }
//        return legalMoves;
//    }

    private Optional<Move> takeLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeLeft;

        if (super.getColour() == PieceColour.WHITE) {
            takeLeft = new Move(position, new Position(currentX - 1, currentY + 1));
        } else {
            takeLeft = new Move(position, new Position(currentX + 1, currentY - 1));
        }

        if (!board.isEmpty(takeLeft.getTo())) {
            return Optional.of(takeLeft);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Move> takeRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeRight;

        if (super.getColour() == PieceColour.WHITE) {
            takeRight = new Move(position, new Position(currentX + 1, currentY + 1));
        } else {
            takeRight = new Move(position, new Position(currentX - 1, currentY - 1));
        }

        if (!board.isEmpty(takeRight.getTo())) {
            return Optional.of(takeRight);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Move> oneSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        Move oneSquareMove;

        if (getColour() == PieceColour.WHITE) {
            oneSquareMove = new Move(position, new Position(currentX, currentY + 1));
        } else {
            oneSquareMove = new Move(position, new Position(currentX, currentY - 1));
        }

        if (board.isEmpty(oneSquareMove.getTo())) {
            return Optional.of(oneSquareMove);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Move> twoSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move twoSquareMove;

        if (firstMove) {
            if (super.getColour() == PieceColour.WHITE) {
                twoSquareMove = new Move(position, new Position(currentX, currentY + 2));
            } else {
                twoSquareMove = new Move(position, new Position(currentX, currentY - 2));
            }

            if (board.isEmpty(twoSquareMove.getTo())) {
                return Optional.of(twoSquareMove);
            }
        }

        return Optional.empty();
    }
}