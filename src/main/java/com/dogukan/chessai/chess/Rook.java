package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    Rook(PieceColour colour) {
        super(colour);
        setStrength(50);
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(Direction.eastMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.westMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.northMove(board, position, getColour(), board.columnLength()));
        moves.addAll(Direction.southMove(board, position, getColour(), board.columnLength()));

        return moves;
    }
}