package com.dogukan.chessai.chess;

public class Bishop extends Piece {

    Bishop(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        northEastDiagonal(board, position);
        southEastDiagonal(board, position);
        southWest(board, position);
        northWest(board, position);
    }

    private void northEastDiagonal(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY - i))) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY - i)));
        }
    }

    private void southEastDiagonal(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY + i))) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY + i)));
        }
    }

    private void southWest(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY + i))) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY + i)));
        }
    }

    private void northWest(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY - i))) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY - i)));
        }
    }
}
