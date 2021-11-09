package com.dogukan.chessai.chess;

import java.util.Set;

public class GameState {

    private GameState prev;
    private GameState next;
    private PieceColour playerTurn;
    private final Board board;

    public GameState(GameState prev, PieceColour playerTurn, Board board) {
        this.prev = prev;
        this.next = null;
        this.playerTurn = playerTurn;
        this.board = board;
    }

    public void move(Move move) {
        Board newBoard = new Board(board.getBoard());
        Piece piece = newBoard.getSquare(move.getFrom());
        if (piece != null) {
            Set<Move> legalMoves = piece.legalMoves(newBoard, move.getFrom());
            if (legalMoves.contains(move)) {
                newBoard.removePiece(move.getFrom());
                newBoard.addPiece(move.getTo(), piece);
                next = new GameState(this, playerTurn.opponent(), newBoard);
            }
        }
    }

//    public Square[][] getBoard() {
//        return board.clone();
//    }

    public GameState getPrev() {
        return prev;
    }

    public GameState getNext() {
        return next;
    }
}
