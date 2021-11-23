package com.dogukan.chessai.chess;

public class Bishop extends Piece {

    Bishop(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.northEastDiagonal(board, position, getColour()));
        getLegalMoves().addAll(Direction.southEastDiagonal(board, position,getColour()));
        getLegalMoves().addAll(Direction.southWestDiagonal(board, position, getColour()));
        getLegalMoves().addAll(Direction.northWestDiagonal(board, position, getColour()));
    }
}
