package com.dogukan.chessai.chess;

import java.util.Arrays;

public class Board {

    private Piece[][] board;

    public Board(Piece[][] board, boolean deepCopy) {
        if(deepCopy) {
            this.board = new Piece[board.length][board[0].length];
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    Piece piece = board[i][j];
                    if(piece != null) {
                        this.board[i][j] = board[i][j];
                    }
                }
            }
        } else {
            this.board = board;
        }
    }

    public Piece getSquare(Position position) {
        if (isInRange(position)) {
            return board[position.getX()][position.getY()];
        } else {
            return null;
        }
    }

    public Piece[][] getSquares() {
        return board;
    }

    public void removePiece(Position position) {
        board[position.getX()][position.getY()] = null;
    }

    public void addPiece(Position position, Piece piece) {
        board[position.getX()][position.getY()] = piece;
    }

    public boolean isEmpty(Position position) {
        return isInRange(position) && board[position.getX()][position.getY()] == null;
    }

    public boolean isInRange(Position position) {
        return position.getY() < board[1].length && position.getX() < board.length
                && position.getY() >= 0 && position.getX() >= 0;
    }

    public Position findKing(PieceColour colour) {
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Piece piece = this.board[i][j];
                if((piece instanceof King) && (piece.getColour() == colour)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public int rowLength() {
        return board[0].length;
    }

    public int columnLength() {
        return board.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece[][] board1 = ((Board) o).getSquares();
        for(int i = 0; i < rowLength(); i++) {
            for(int j = 0; j < columnLength(); j++) {
                if(board[i][j] == null && board1[i][j] != null ||
                        board[i][j] != null && board1[i][j] == null) {
                    return false;
                } else if(board[i][j] == null && board1[i][j] == null) {
                } else {
                    if(board[i][j].getClass() != board1[i][j].getClass()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public void makeBoard() {
        for (int y = 0; y < columnLength(); y++) {
            for (int x = 0; x < rowLength(); x++) {
                Piece piece = getSquare(new Position(x, y));
                if (piece instanceof Pawn) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WP ");
                    } else {
                        System.out.print("BP ");
                    }
                } else if (piece instanceof Rook) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WR ");
                    } else {
                        System.out.print("BR ");
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WB ");
                    } else {
                        System.out.print("BB ");
                    }
                } else if (piece instanceof Knight) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WK ");
                    } else {
                        System.out.print("BK ");
                    }
                } else if (piece instanceof Queen) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WQ ");
                    } else {
                        System.out.print("BQ ");
                    }
                } else if (piece instanceof King) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WKi ");
                    } else {
                        System.out.print("BKi ");
                    }
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
}