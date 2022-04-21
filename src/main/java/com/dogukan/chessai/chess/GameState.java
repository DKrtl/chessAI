package com.dogukan.chessai.chess;

import java.util.Objects;
import java.util.Set;

public class GameState {

    private GameState prev;
    private GameState next;
    private final Board board;
    private final int evaluation;
    private boolean creativeMode;

    public GameState(GameState prev, Board board, boolean creativeMode) {
        this.prev = prev;
        this.next = null;
        this.board = board;
        this.evaluation = evaluateBoard();
        this.creativeMode = creativeMode;
    }

    private int evaluateBoard() {
        int total = 0;
        for(int i = 0; i < board.columnLength(); i++) {
            for(int j = 0; j < board.rowLength(); j++) {
                Piece piece = board.getSquare(new Position(i, j));
                if(piece != null) {
                    total += piece.getStrength(i, j);
                }
            }
        }
        return total;
    }

    public GameState move(Move move) {
        Piece piece = board.getSquare(move.getFrom());
        if((piece != null)) {
            GameState next = new GameState(this, piece.move(this, move), creativeMode);
            Position to = move.getTo();
            if(next.getBoard().getSquare(to) instanceof Pawn) {
                if((piece.getColour() == PieceColour.WHITE && to.equals(new Position(move.getTo().getX(), 0))) ||
                        (piece.getColour() == PieceColour.BLACK && to.equals(new Position(move.getTo().getX(), 7)))) {
                    next.getBoard().removePiece(to);
                    next.getBoard().addPiece(to, new Queen(piece.getColour()));
                }
            }
            if(!next.isCheck(piece.getColour())) {
                return next;
            }
        }
        return null;
    }

    public GameState creativeModeMove(Move move) {
        Piece piece = board.getSquare(move.getFrom());
        if(creativeMode) {
            return new GameState(this, piece.creativeModeMove(this, move), creativeMode);
        } else {
            return null;
        }
    }

    public GameState remove(Position position) {
        Board newBoard = new Board(board.getSquares(), true);
        if(getCreativeMode()) {
            newBoard.removePiece(position);
            return new GameState(this, newBoard, getCreativeMode());
        }
        return null;
    }

    public boolean isCheck(PieceColour playerTurn) {
        Piece[][] board = this.board.getSquares();
        Position king = this.board.findKing(playerTurn);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if(piece != null && piece.getColour() == playerTurn.opponent()) {
                    Set<Move> moves = piece.legalMoves(getBoard(), new Position(i, j));
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
        if(isCheck(playerTurn)) {
            Piece[][] board = this.board.getSquares();

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    Position currentPos = new Position(i, j);
                    Piece piece = this.board.getSquare(currentPos);
                    if(piece != null && piece.getColour() == playerTurn) {
                        Set<Move> moves = piece.legalMoves(getBoard(), currentPos);
                        for(Move move : moves) {
                            GameState next = move(move);
                            if(next != null && !next.isCheck(playerTurn)) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean gameOver() {
        return isCheckmate(PieceColour.WHITE) || isCheckmate(PieceColour.BLACK);
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

    public GameState setNext(GameState next) {
        this.next = next;
        return next;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public boolean getCreativeMode() {
        return creativeMode;
    }

    public void setCreativeMode(boolean creativeMode) {
        this.creativeMode = creativeMode;
    }

    public void setPrev(GameState prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return getBoard().equals(gameState.getBoard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}