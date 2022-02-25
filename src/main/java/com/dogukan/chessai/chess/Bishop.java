package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    Bishop(PieceColour colour) {
        super(colour);
        setStrength(30);
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();
        moves.addAll(Direction.northEastMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.southEastMove(board, position,getColour(), board.columnLength()));
        moves.addAll(Direction.southWestMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.northWestMove(board, position, getColour(), board.columnLength()));
        return moves;
    }
}