package com.dogukan.chessai.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        if ((piece != null) && (piece.getColour() == playerTurn)) {
            piece.move(this, move);
            if(next.isCheck(getPlayerTurn())) {
                next = null;
            }
        }
    }

    public boolean isCheck(PieceColour playerTurn) {
        Piece[][] board = this.board.getSquares();
        Position king = this.board.findKing(playerTurn);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if(piece != null && piece.getColour() == playerTurn.opponent()) {
                    piece.legalMoves(getBoard(), new Position(i, j));
                    Set<Move> moves = piece.getLegalMoves();
                    for(Move move : moves) {
                        if(move.getTo().equals(king)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(PieceColour playerTurn) {
        Piece[][] board = this.board.getSquares();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Position currentPos = new Position(i, j);
                Piece piece = this.board.getSquare(currentPos);
                if(piece != null && piece.getColour() == playerTurn) {
                    piece.legalMoves(getBoard(), currentPos);
                    Set<Move> moves = piece.getLegalMoves();
                    for(Move move : moves) {
                        piece.move(this, move);
                        if(!next.isCheck(getPlayerTurn())) {
                            next = null;
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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