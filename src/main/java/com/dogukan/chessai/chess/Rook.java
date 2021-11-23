package com.dogukan.chessai.chess;

public class Rook extends Piece {


    Rook(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        getLegalMoves().addAll(Direction.horizontalMove(board, position, getColour()));
        getLegalMoves().addAll(Direction.verticalMove(board, position, getColour()));
    }
}
