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
        List<Piece> checkingPieces = checkingPieces(playerTurn);

        if ((piece != null) && (piece.getColour() == playerTurn) && (checkingPieces.isEmpty())) {
            piece.move(this, move);
        } else if((piece != null) && (piece.getColour() == playerTurn) && (!checkingPieces.isEmpty())) {

        }
    }

    // change this to a set of checking pieces as this can imply the colour of the
    // king being checked. This can also be used to find the moves that need to be
    // blocked to get out of a check or moves that can take the checking piece.
    public List<Piece> checkingPieces(PieceColour playerTurn) {
        Piece[][] board = this.board.getSquares();
        Position king = this.board.findKing(playerTurn);
        List<Piece> checkingPieces = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if(piece != null && piece.getColour() == playerTurn.opponent()) {
                    piece.legalMoves(getBoard(), new Position(i, j));
                    Set<Move> moves = piece.getLegalMoves();
                    for(Move move : moves) {
                        if(move.getTo().equals(king)) {
                            checkingPieces.add(piece);
                            break;
                        }
                    }
                }
            }
        }
        return checkingPieces;
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