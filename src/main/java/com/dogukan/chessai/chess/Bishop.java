package com.dogukan.chessai.chess;

public class Bishop extends Piece {

    Bishop(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.northEastMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southEastMove(board, position,getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southWestMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.northWestMove(board, position, getColour(), board.columnLength()));
    }
}