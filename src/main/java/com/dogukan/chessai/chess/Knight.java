package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(PieceColour colour) {
        super(colour);
        setStrength(320);
        int[][] pieceSquareTable = {{-50,-40,-30,-30,-30,-30,-40,-50},
                                    {-40,-20,  0,  0,  0,  0,-20,-40},
                                    {-30,  0, 10, 15, 15, 10,  0,-30},
                                    {-30,  5, 15, 20, 20, 15,  5,-30},
                                    {-30,  0, 15, 20, 20, 15,  0,-30},
                                    {-30,  5, 10, 15, 15, 10,  5,-30},
                                    {-40,-20,  0,  5,  5,  0,-20,-40},
                                    {-50,-40,-30,-30,-30,-30,-40,-50}};
        setPieceSquareTable(pieceSquareTable);
    }

    @Override
    public Piece copy() {
        Knight copy = new Knight(getColour());
        copy.setPieceSquareTable(getPieceSquareTable());
        copy.setStrength(getStrength());
        return copy;
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        Position topEast = new Position(currentX + 1, currentY - 2);
        Position bottomEast = new Position(currentX + 1, currentY + 2);
        Position bottomWest = new Position(currentX - 1, currentY + 2);
        Position topWest = new Position(currentX - 1, currentY - 2);
        Position leftNorth = new Position(currentX - 2, currentY + 1);
        Position leftSouth = new Position(currentX - 2, currentY - 1);
        Position rightNorth = new Position(currentX + 2, currentY + 1);
        Position rightSouth = new Position(currentX + 2, currentY - 1);

        createMove(board, new Move(position, topEast), moves);
        createMove(board, new Move(position, bottomEast), moves);
        createMove(board, new Move(position, bottomWest), moves);
        createMove(board, new Move(position, topWest), moves);
        createMove(board, new Move(position, leftNorth), moves);
        createMove(board, new Move(position, leftSouth), moves);
        createMove(board, new Move(position, rightNorth), moves);
        createMove(board, new Move(position, rightSouth), moves);

        return moves;
    }

    private void createMove(Board board, Move move, Set<Move> moves) {
        if (board.isEmpty(move.getTo()) ||
                (board.isInRange(move.getTo()) && board.getSquare(move.getTo()).getColour() != getColour())) {
            moves.add(move);
        }
    }
}
