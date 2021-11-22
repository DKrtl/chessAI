package com.dogukan.chessai.chess;

public class Knight extends Piece {

    Knight(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        northEast(board, position);
        southEast(board, position);
        southWest(board, position);
        northWest(board, position);
    }

    private void northEast(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        if (board.getSquare(new Position(currentX + 1, currentY - 2)).getColour() != getColour()) {
            getLegalMoves().add(new Move(position, new Position(currentX + 1, currentY - 2)));
        }
    }

    private void southEast(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        if (board.getSquare(new Position(currentX + 1, currentY + 2)).getColour() != getColour()) {
            getLegalMoves().add(new Move(position, new Position(currentX + 1, currentY + 2)));
        }
    }

    private void southWest(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        if (board.getSquare(new Position(currentX - 1, currentY + 2)).getColour() != getColour()) {
            getLegalMoves().add(new Move(position, new Position(currentX - 1, currentY + 2)));
        }
    }

    private void northWest(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        if (board.getSquare(new Position(currentX - 1, currentY - 2)).getColour() != getColour()) {
            getLegalMoves().add(new Move(position, new Position(currentX - 1, currentY - 2)));
        }
    }
}
