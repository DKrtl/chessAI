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
        emptySquareOnAbove(board, position);
        emptySquareBelow(board, position);
    }

    private void emptySquareOnAbove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY - 1))) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY - i)));
            i++;
        }
    }

    private void emptySquareBelow(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY + 1))) {
            getLegalMoves().add(new Move(position, new Position(currentX, currentY + i)));
            i++;
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
    }

    private void emptySquareOnRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY))) {
            getLegalMoves().add(new Move(position, new Position(currentX + i, currentY)));
            i++;
        }
    }
}
