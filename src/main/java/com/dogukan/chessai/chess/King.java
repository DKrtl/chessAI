package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    King(PieceColour colour) {
        super(colour);
        setStrength(20000);
        int[][] pieceSquareTable = {{-30, -40, -40, -50, -50, -40, -40, -30},
                                    {-30, -40, -40, -50, -50, -40, -40, -30},
                                    {-30, -40, -40, -50, -50, -40, -40, -30},
                                    {-30, -40, -40, -50, -50, -40, -40, -30},
                                    {-20, -30, -30, -40, -40, -30, -30, -20},
                                    {-10, -20, -20, -20, -20, -20, -20, -10},
                                    {20, 20, 0, 0, 0, 0, 20, 20},
                                    {20, 30, 10, 0, 0, 10, 30, 20}};
        setPieceSquareTable(pieceSquareTable);
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
