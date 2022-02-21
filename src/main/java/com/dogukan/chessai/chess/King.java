package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    King(PieceColour colour) {
        super(colour);
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();
        moves.addAll(Direction.northMove(board, position, getColour(), 1));
        moves.addAll(Direction.northEastMove(board, position, getColour(), 1));
        moves.addAll(Direction.eastMove(board, position, getColour(), 1));
        moves.addAll(Direction.southEastMove(board, position, getColour(), 1));
        moves.addAll(Direction.southMove(board, position, getColour(), 1));
        moves.addAll(Direction.southWestMove(board, position, getColour(), 1));
        moves.addAll(Direction.westMove(board, position, getColour(), 1));
        moves.addAll(Direction.northWestMove(board, position, getColour(), 1));

        return moves;
    }
}
