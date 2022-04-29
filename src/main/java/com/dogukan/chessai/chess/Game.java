package com.dogukan.chessai.chess;

import com.dogukan.chessai.ai.AI;

import java.util.List;

public class Game {

    private GameState currentState;
    private final AI ai;

    public Game(boolean creativeMode) {
        newGame(creativeMode);
        this.ai = new AI();
    }

    private void newGame(boolean creativeMode) {
        Board board = new Board(new Piece[8][8], false);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 1) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.BLACK));
                } else if (j == 6) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.WHITE));
                } else if (j == 0) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.BLACK));
                    } else if (i == 1 || i == 6) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.BLACK));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.BLACK));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                    }
                } else if (j == 7) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.WHITE));
                    } else if (i == 1 || i == 6) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.WHITE));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.WHITE));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.WHITE));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                    }
                }
            }
        }
        currentState = new GameState(board, creativeMode);
    }

    public void move(Move move) {
        if(getCreativeMode()) {
            currentState = currentState.creativeModeMove(move);
        }
    }

    public void remove(Position position) {
        GameState next = currentState.remove(position);
        if(next != null) {
            currentState = next;
        }
    }

    public boolean getCreativeMode() {
        return currentState.getCreativeMode();
    }

    public Board getBoard() {
        return currentState.getBoard();
    }

    public void setCreativeMode(boolean creativeMode) {
        currentState.setCreativeMode(creativeMode);
    }

    public List<Information> createInformation(PieceColour colour) {
        return ai.createInformation(colour, currentState);
    }

    public void addToAI(Piece piece) {
        ai.addToSelected(piece);
    }

    public void removeFromAI(Piece piece) {
        ai.removeFromSelected(piece);
    }
}