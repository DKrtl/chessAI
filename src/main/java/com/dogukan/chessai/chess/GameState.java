package com.dogukan.chessai.chess;

public class GameState {

    private final GameState prev;
    private GameState next;
    private final PieceColour playerTurn;
    private final Board board;

    public GameState(GameState prev, PieceColour playerTurn, Board board) {
        this.prev = prev;
        this.next = null;
        this.playerTurn = playerTurn;
        this.board = board;
    }

    public void move(Move move) {
        Piece piece = board.getSquare(move.getFrom());
        if (piece != null && (piece.getColour() == playerTurn)) {
            piece.move(this, move);
        }
    }

    public boolean isCheckmate() {
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public GameState getPrev() {
        return prev;
    }

    public GameState getNext() {
        return next;
    }

    public void setNext(GameState next) {
        this.next = next;
    }

    public PieceColour getPlayerTurn() {
        return playerTurn;
    }
}