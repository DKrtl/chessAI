package com.dogukan.chessai.chess;

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
        if (piece != null && (piece.getColour() == playerTurn) && !isCheck()) {
            piece.move(this, move);
        }
    }

    public boolean isCheck() {
        Piece[][] board = this.board.getSquares();
        Position whiteKing = this.board.findKing(PieceColour.WHITE);
        Position blackKing = this.board.findKing(PieceColour.BLACK);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if(piece != null) {
                    piece.legalMoves(this.board, new Position(i , j));
                    Set<Move> moves = piece.getLegalMoves();
                    if(piece.getColour().opponent() == this.board.getSquare(whiteKing).getColour()) {
                        for(Move move : moves) {
                            if(move.getTo().equals(whiteKing)) {
                                return true;
                            }
                        }
                    } else if(piece.getColour().opponent() == this.board.getSquare(blackKing).getColour()) {
                        for(Move move : moves) {
                            if(move.getTo().equals(blackKing)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
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