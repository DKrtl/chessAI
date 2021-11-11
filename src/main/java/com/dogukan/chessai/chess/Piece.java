package com.dogukan.chessai.chess;

import java.util.Set;

public abstract class Piece {

    private PieceColour colour;
    private Set<Move> legalMoves;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares());
        legalMoves(newBoard, move.getFrom());
        if (legalMoves.contains(move)) {
            newBoard.removePiece(move.getFrom());
            newBoard.addPiece(move.getTo(), this);
            gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));
        }
    }

    public abstract void legalMoves(Board board, Position position);

    public PieceColour getColour() {
        return colour;
    }

    public void setLegalMoves(Set<Move> legalMoves) {
        this.legalMoves = legalMoves;
    }

    public Set<Move> getLegalMoves() {
        return legalMoves;
    }
}