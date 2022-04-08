package com.dogukan.chessai.chess;

import java.util.Set;

public abstract class Piece {

    private PieceColour colour;
    private int strength;
    private int[][] pieceSquareTable;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public Board move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares(), true);
        Set<Move> legalMoves = legalMoves(newBoard, move.getFrom());
        if(legalMoves.contains(move) && !(board.getSquare(move.getTo()) instanceof King)) {
            if(newBoard.isInRange(move.getTo())) {
                newBoard.removePiece(move.getFrom());
                newBoard.addPiece(move.getTo(), this);
            }
        }
        return newBoard;
    }

    public Board creativeModeMove(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares(), true);
        newBoard.removePiece(move.getFrom());
        newBoard.addPiece(move.getTo(), this);
        return newBoard;
    }

    public abstract Set<Move> legalMoves(Board board, Position position);

    public PieceColour getColour() {
        return colour;
    }

    public void setStrength(int strength) {
        if(colour == PieceColour.WHITE) {
            this.strength = strength;
        } else {
            this.strength = strength * -1;
        }
    }

    public void setPieceSquareTable(int[][] pieceSquareTable) {
        this.pieceSquareTable = pieceSquareTable;
    }

    public int getStrength(int x, int y) {
        if(colour == PieceColour.WHITE) {
            return strength + pieceSquareTable[y][x];
        } else {
            int length = pieceSquareTable.length;
            return strength - pieceSquareTable[length - 1 - y][length - 1 - x];
        }
    }
}