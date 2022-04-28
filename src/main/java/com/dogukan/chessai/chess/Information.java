package com.dogukan.chessai.chess;

public class Information {
    private Piece piece;
    private Move move;
    private int initialEval;
    private int finalEval;

    public Information(Piece piece, Move move, int initialEval, int finalEval) {
        this.piece = piece;
        this.move = move;
        this.initialEval = initialEval;
        this.finalEval = finalEval;
    }

    public String getPiece() {
        if(piece instanceof Pawn) {
            return "Pawn";
        } else if(piece instanceof Bishop) {
            return "Bishop";
        } else if(piece instanceof King) {
            return "King";
        } else if(piece instanceof Knight) {
            return "Knight";
        } else if(piece instanceof Queen) {
            return "Queen";
        } else if(piece instanceof Rook) {
            return "Rook";
        } else {
            return null;
        }
    }

    public String getFrom() {
        Position from = move.getFrom();

        return xValToLetter(from.getX()) + (8 - from.getY());
    }

    public String getTo() {
        Position to = move.getTo();

        return xValToLetter(to.getX()) + (8 - to.getY());
    }

    private String xValToLetter(int x) {
        return switch (x) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> null;
        };
    }

    public int getInitialEval() {
        return initialEval;
    }

    public int getFinalEval() {
        return finalEval;
    }
}
