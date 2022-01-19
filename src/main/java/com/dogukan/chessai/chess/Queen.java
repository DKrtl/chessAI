package com.dogukan.chessai.chess;

public class Queen extends Piece {

    Queen(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
//        getLegalMoves().addAll(Direction.horizontalMove(board, position, getColour()));
//        getLegalMoves().addAll(Direction.verticalMove(board, position, getColour()));
//        getLegalMoves().addAll(Direction.northEastDiagonal(board, position, getColour()));
//        getLegalMoves().addAll(Direction.southEastDiagonal(board, position, getColour()));
//        getLegalMoves().addAll(Direction.southWestDiagonal(board, position, getColour()));
//        getLegalMoves().addAll(Direction.northWestDiagonal(board, position, getColour()));
    }
}
