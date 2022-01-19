package com.dogukan.chessai.chess;

public class Knight extends Piece {

    Knight(PieceColour colour) {
        super(colour);
    }

    @Override
    public void legalMoves(Board board, Position position) {
//        int currentX = position.getX();
//        int currentY = position.getY();
//
//        Position northEast = new Position(currentX + 1, currentY - 2);
//        Position southEast = new Position(currentX + 1, currentY + 2);
//        Position southWest = new Position(currentX - 1, currentY + 2);
//        Position northWest = new Position(currentX - 1, currentY - 2);
//
//        createMove(board, new Move(position, northEast));
//        createMove(board, new Move(position, southEast));
//        createMove(board, new Move(position, southWest));
//        createMove(board, new Move(position, northWest));
    }

    private void createMove(Board board, Move move) {
        if (board.isEmpty(move.getTo()) ||
                (board.isInRange(move.getTo()) && board.getSquare(move.getTo()).getColour() != getColour())) {
            getLegalMoves().add(move);
        }
    }
}
