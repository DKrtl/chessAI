package com.dogukan.chessai.chess;

public class King extends Piece {

    King(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.northMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.northEastMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.eastMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.southEastMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.southMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.southWestMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.westMove(board, position, getColour(), 1));
        getLegalMoves().addAll(Direction.northWestMove(board, position, getColour(), 1));
    }
}
