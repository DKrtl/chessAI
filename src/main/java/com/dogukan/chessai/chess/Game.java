package com.dogukan.chessai.chess;

import com.dogukan.chessai.ai.AI;

public class Game {

    private GameState currentState;

    Game() {
        newGame2();
    }

    public void newGame2() {
        Board board = new Board(new Piece[4][4], false);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 0) {
                    if (i == 0) {
                        board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                    } else if(i == 1) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                    }
                } else if (j == 3) {
                    if (i == 2) {
                        board.addPiece(new Position(i , j), new Queen(PieceColour.WHITE));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                    }
                } else if (j == 2) {
                    if (i == 3) {
                        board.addPiece(new Position(i , j), new Queen(PieceColour.WHITE));
                    }
                }
            }
        }
        currentState = new GameState(null, PieceColour.WHITE, board);
        // new AI(currentState);
    }

    public void newGame() {
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
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.BLACK));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.BLACK));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                    }
                } else if (j == 7) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.WHITE));
                    } else if (i == 1 || i == 6) {
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.WHITE));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.WHITE));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.WHITE));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                    }
                }
            }
        }
        currentState = new GameState(null, PieceColour.WHITE, board);
    }

    public Board getBoard() {
        return currentState.getBoard();
    }

    public void move(Move move) {
        GameState next = currentState.move(move);
        if(next != null) {
            currentState = currentState.setNext(next);;
        }
    }

    public boolean isGameOver() {
        return currentState.isCheckmate(currentState.getPlayerTurn());
    }
}