package com.dogukan.chessai.chess;

public class Game {

    private GameState currentState;

    public Game(PieceColour colour, boolean creativeMode) {
        newGame(colour, creativeMode);
    }

    private void newGame(PieceColour colour, boolean creativeMode) {
        Board board = new Board(new Piece[8][8], false);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 1) {
                    if(colour == PieceColour.WHITE) {
                        board.addPiece(new Position(i, j), new Pawn(PieceColour.BLACK));
                    } else {
                        board.addPiece(new Position(i, j), new Pawn(PieceColour.WHITE));
                    }
                } else if (j == 6) {
                    if(colour == PieceColour.WHITE) {
                        board.addPiece(new Position(i, j), new Pawn(PieceColour.WHITE));
                    } else {
                        board.addPiece(new Position(i, j), new Pawn(PieceColour.BLACK));
                    }
                } else if (j == 0) {
                    if (i == 0 || i == 7) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Rook(PieceColour.BLACK));
                        } else {
                            board.addPiece(new Position(i , j), new Rook(PieceColour.WHITE));
                        }
                    } else if (i == 1 || i == 6) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Bishop(PieceColour.BLACK));
                        } else {
                            board.addPiece(new Position(i , j), new Bishop(PieceColour.WHITE));
                        }
                    } else if (i == 2 || i == 5) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Knight(PieceColour.BLACK));
                        } else {
                            board.addPiece(new Position(i , j), new Knight(PieceColour.WHITE));
                        }
                    } else if (i == 3) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                        } else {
                            board.addPiece(new Position(i, j), new Queen(PieceColour.WHITE));
                        }
                    } else {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                        } else {
                            board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                        }
                    }
                } else if (j == 7) {
                    if (i == 0 || i == 7) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Rook(PieceColour.WHITE));
                        } else {
                            board.addPiece(new Position(i , j), new Rook(PieceColour.BLACK));
                        }
                    } else if (i == 1 || i == 6) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Bishop(PieceColour.WHITE));
                        } else {
                            board.addPiece(new Position(i , j), new Bishop(PieceColour.BLACK));
                        }
                    } else if (i == 2 || i == 5) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i , j), new Knight(PieceColour.WHITE));
                        } else {
                            board.addPiece(new Position(i , j), new Knight(PieceColour.BLACK));
                        }
                    } else if (i == 3) {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i, j), new Queen(PieceColour.WHITE));
                        } else {
                            board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                        }
                    } else {
                        if(colour == PieceColour.WHITE) {
                            board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                        } else {
                            board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                        }
                    }
                }
            }
        }
        currentState = new GameState(null, PieceColour.WHITE, board, creativeMode);
    }

    public Board getBoard() {
        return currentState.getBoard();
    }

    public void move(Move move) {
        GameState next = currentState.move(move);
        if(getCreativeMode()) {
            currentState = next;
        } else if(next != null) {
            currentState = currentState.setNext(next);
        }
    }

    public void remove(Position position) {
        GameState next = currentState.remove(position);
        if(next != null) {
            currentState = next;
        }
    }

    public boolean isGameOver() {
        return currentState.isCheckmate(currentState.getPlayerTurn());
    }

    public boolean getCreativeMode() {
        return currentState.getCreativeMode();
    }

    public void setCreativeMode(boolean creativeMode) {
        currentState.setCreativeMode(creativeMode);
    }
}