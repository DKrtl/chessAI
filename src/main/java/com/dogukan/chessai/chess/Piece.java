package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    private PieceColour colour;
    private Set<Move> legalMoves;

    Piece(PieceColour colour) {
        this.colour = colour;
        this.legalMoves = new HashSet<>();
    }

    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares(), true);
        legalMoves(newBoard, move.getFrom());
        if (legalMoves.contains(move)) {
            if (newBoard.isInRange(move.getTo())) {
                newBoard.removePiece(move.getFrom());
                newBoard.addPiece(move.getTo(), this);
                legalMoves = new HashSet<>();
                gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));
            }
        }
    }

    public abstract void legalMoves(Board board, Position position);

    public PieceColour getColour() {
        return colour;
    }

    public Set<Move> getLegalMoves() {
        return legalMoves;
    }
}