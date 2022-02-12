package com.dogukan.chessai.chess;

public class Game {

    private GameState currentState;

    Game() {
        newGame2();
    }

    public void newGame2() {
        Board board = new Board(new Piece[8][8]);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 0) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.BLACK));
                    } else if (i == 4) {
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

    public void newGame() {
        Board board = new Board(new Piece[8][8]);
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
        currentState.move(move);
        GameState next = currentState.getNext();
        if(next != null) {
            currentState = next;
        }
    }

    public boolean isGameOver() {
        return currentState.isCheckmate();
    }
}