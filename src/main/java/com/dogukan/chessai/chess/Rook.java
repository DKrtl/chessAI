package com.dogukan.chessai.chess;

public class Rook extends Piece {

    Rook(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.eastMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.westMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.northMove(board, position, getColour(), board.columnLength()));
        getLegalMoves().addAll(Direction.southMove(board, position, getColour(), board.columnLength()));
    }
}