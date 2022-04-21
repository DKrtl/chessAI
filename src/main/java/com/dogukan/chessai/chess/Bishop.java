package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(PieceColour colour) {
        super(colour);
        setStrength(330);
        int[][] pieceSquareTable = {{-20, -10, -10, -10, -10, -10, -10, -20},
                                    {-10, 0, 0, 0, 0, 0, 0, -10},
                                    {-10, 0, 5, 10, 10, 5, 0, -10},
                                    {-10, 5, 5, 10, 10, 5, 5, -10},
                                    {-10, 0, 10, 10, 10, 10, 0, -10},
                                    {-10, 10, 10, 10, 10, 10, 10, -10},
                                    {-10, 5, 0, 0, 0, 0, 5, -10},
                                    {-20, -10, -10, -10, -10, -10, -10, -20}};
        setPieceSquareTable(pieceSquareTable);
    }

    @Override
    public Piece copy() {
        Bishop copy = new Bishop(getColour());
        copy.setPieceSquareTable(getPieceSquareTable());
        copy.setStrength(getStrength());
        return copy;
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