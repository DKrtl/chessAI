package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    private PieceColour colour;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares(), true);
        Set<Move> legalMoves = legalMoves(newBoard, move.getFrom());
        if (legalMoves.contains(move)) {
            if (newBoard.isInRange(move.getTo())) {
                newBoard.removePiece(move.getFrom());
                newBoard.addPiece(move.getTo(), this);
                gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));
            }
        }
    }

    public abstract Set<Move> legalMoves(Board board, Position position);

    public PieceColour getColour() {
        return colour;
    }
}