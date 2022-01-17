package com.dogukan.chessai.chess;

public class King extends Piece {

    King(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        getLegalMoves().add(new Move(position, new Position(currentX, currentY + 1)));
        getLegalMoves().add(new Move(position, new Position(currentX + 1, currentY + 1)));
        getLegalMoves().add(new Move(position, new Position(currentX + 1, currentY)));
        getLegalMoves().add(new Move(position, new Position(currentX + 1, currentY - 1)));
        getLegalMoves().add(new Move(position, new Position(currentX, currentY - 1)));
        getLegalMoves().add(new Move(position, new Position(currentX - 1, currentY - 1)));
        getLegalMoves().add(new Move(position, new Position(currentX - 1, currentY)));
    }
}
