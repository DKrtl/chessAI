package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(PieceColour colour) {
        super(colour);
        setStrength(320);
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        Position northEast = new Position(currentX + 1, currentY - 2);
        Position southEast = new Position(currentX + 1, currentY + 2);
        Position southWest = new Position(currentX - 1, currentY + 2);
        Position northWest = new Position(currentX - 1, currentY - 2);

        createMove(board, new Move(position, northEast), moves);
        createMove(board, new Move(position, southEast), moves);
        createMove(board, new Move(position, southWest), moves);
        createMove(board, new Move(position, northWest), moves);

        return moves;
    }

    private void createMove(Board board, Move move, Set<Move> moves) {
        if (board.isEmpty(move.getTo()) ||
                (board.isInRange(move.getTo()) && board.getSquare(move.getTo()).getColour() != getColour()) &&
                        !(board.getSquare(move.getTo()) instanceof King)) {
            moves.add(move);
        }
    }
}
