package com.dogukan.chessai.chess;

import java.util.Set;

public class GameState {

    private GameState prev;
    private GameState next;
    private PieceColour playerTurn;
    private Square[][] board;

    public GameState(GameState prev, PieceColour playerTurn, Square[][] board) {
        this.prev = prev;
        this.next = null;
        this.playerTurn = playerTurn;
        this.board = board;
    }

    public void makeMove(Move move) {
//        this.next = new GameState(this, playerTurn.opponent());
//        Piece piece = move.getFrom().getPiece();
//        if (piece != null) {
//            Set<Move> legalMoves = piece.legalMoves();
//            if (legalMoves.contains())
//            move.getFrom().removePiece();
//            move.getTo().addPiece(piece);
//        }
    }
}
