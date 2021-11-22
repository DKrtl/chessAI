package com.dogukan.chessai.chess;

public class Rook extends Piece {


    Rook(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
        horizontalMove(board, position);
        verticalMove(board, position);
    }

    private void horizontalMove(Board board, Position position) {

        emptySquareOnLeft(board, position);
        emptySquareOnRight(board, position);
    }

    private void verticalMove(Board board, Position position) {
        emptySquareAbove(board, position);
        emptySquareBelow(board, position);
    }

    private void emptySquareAbove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY - i))) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY - i)));
        }
    }

    private void emptySquareBelow(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY + i))) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY + i)));
        }
    }

    private void emptySquareOnLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY))) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX - i, currentY)));
        }
    }

    private void emptySquareOnRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY))) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY));

        if (blockingPiece != null && blockingPiece.getColour() == getColour().opponent()) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY)));
        }
    }
}
