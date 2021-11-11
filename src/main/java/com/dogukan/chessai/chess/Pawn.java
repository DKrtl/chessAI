package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pawn extends Piece {

    private boolean firstMove;
    private boolean tookTwoSquareMove;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
        tookTwoSquareMove = false;
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> legalMoves = new HashSet<>();

        Optional<Move> oneSquareMove = oneSquareMove(board, position);
        Optional<Move> twoSquareMove = twoSquareMove(board, position);
        Optional<Move> takeLeft = takeLeft(board, position);
        Optional<Move> takeRight = takeRight(board, position);
        Optional<Move> enPassantLeft = enPassantLeft(board, position);
        Optional<Move> enPassantRight = enPassantRight(board, position);

        oneSquareMove.ifPresent(legalMoves::add);
        twoSquareMove.ifPresent(legalMoves::add);
        takeLeft.ifPresent(legalMoves::add);
        takeRight.ifPresent(legalMoves::add);
        enPassantLeft.ifPresent(legalMoves::add);
        enPassantRight.ifPresent(legalMoves::add);

        return legalMoves;
    }

    private Optional<Move> enPassantRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (super.getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        }
        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
            if (getColour() == PieceColour.WHITE) {
                if (board.isEmpty(new Position(currentX + 1, currentY + 1))) {
                    return Optional.of(new Move(position, new Position(currentX + 1, currentY + 1)));
                } else {
                    return Optional.empty();
                }
            } else {
                if (board.isEmpty(new Position(currentX - 1, currentY - 1))) {
                    return Optional.of(new Move(position, new Position(currentX - 1, currentY - 1)));
                } else {
                    return Optional.empty();
                }
            }
        } else {
            return Optional.empty();
        }
    }

    private Optional<Move> enPassantLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (super.getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        }
        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
            if (getColour() == PieceColour.WHITE) {
                if (board.isEmpty(new Position(currentX - 1, currentY + 1))) {
                    return Optional.of(new Move(position, new Position(currentX - 1, currentY + 1)));
                } else {
                    return Optional.empty();
                }
            } else {
                if (board.isEmpty(new Position(currentX + 1, currentY - 1))) {
                    return Optional.of(new Move(position, new Position(currentX + 1, currentY - 1)));
                } else {
                    return Optional.empty();
                }
            }
        } else {
            return Optional.empty();
        }
    }

    private Optional<Move> takeLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeLeft;

        if (getColour() == PieceColour.WHITE) {
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

        if (getColour() == PieceColour.WHITE) {
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
            if (getColour() == PieceColour.WHITE) {
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