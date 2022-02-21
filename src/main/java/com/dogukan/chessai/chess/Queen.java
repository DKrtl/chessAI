package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    Queen(PieceColour colour) {
        super(colour);
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(Direction.northMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.northEastMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.eastMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.southEastMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.southMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.southWestMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.westMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.northWestMove(board, position, getColour(), board.columnLength()));

        return moves;
    }
}
