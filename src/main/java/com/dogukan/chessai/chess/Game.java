package com.dogukan.chessai.chess;

public class Game {

    private GameState currentState;

    Game() {
        newGame();
    }

    public void newGame() {
        Board board = new Board(new Piece[8][8]);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 1) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.BLACK));
                } else if (j == 6) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.WHITE));
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
    }
}
