package com.dogukan.chessai.chess;

public class Queen extends Piece {

    Queen(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.northMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.northEastMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.eastMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southEastMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southWestMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.westMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.northWestMove(board, position, getColour(), board.columnLength()));
    }
}
