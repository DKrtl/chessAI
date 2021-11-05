package com.dogukan.chessai.chess;

public class GameState {

    private GameState prev;
    private GameState next;
    private PieceColour playerTurn;
    private Piece[][] board;

    public GameState(GameState prev, PieceColour playerTurn, Piece[][] board) {
        this.prev = prev;
        this.next = null;
        this.playerTurn = playerTurn;
        this.board = board;
    }

    public void makeMove(Move move) {
//        this.next = new GameState(this, playerTurn.opponent());

    }
}
