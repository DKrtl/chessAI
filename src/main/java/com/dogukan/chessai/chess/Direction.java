package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Direction {

    public static Set<Move> horizontalMove(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(emptySquareOnLeft(board, position, colour));
        moves.addAll(emptySquareOnRight(board, position, colour));

        return moves;
    }

    public static Set<Move> verticalMove(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(emptySquareAbove(board, position, colour));
        moves.addAll(emptySquareBelow(board, position, colour));

        return moves;
    }

    private static Set<Move> emptySquareAbove(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY - i))) {
            moves.add(new Move(position, new Position(currentX, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX, currentY - i)));
        }

        return moves;
    }

    private static Set<Move> emptySquareBelow(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX, currentY + i))) {
            moves.add(new Move(position, new Position(currentX, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX, currentY + i)));
        }

        return moves;
    }

    private static Set<Move> emptySquareOnLeft(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY))) {
            moves.add(new Move(position, new Position(currentX - i, currentY)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX - i, currentY)));
        }

        return moves;
    }

    private static Set<Move> emptySquareOnRight(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY))) {
            moves.add(new Move(position, new Position(currentX + i, currentY)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX + i, currentY)));
        }

        return moves;
    }

    public static Set<Move> northEastDiagonal(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY - i))) {
            moves.add(new Move(position, new Position(currentX + i, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX + i, currentY - i)));
        }

        return moves;
    }

    public static Set<Move> southEastDiagonal(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX + i, currentY + i))) {
            moves.add(new Move(position, new Position(currentX + i, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX + i, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX + i, currentY + i)));
        }

        return moves;
    }

    public static Set<Move> southWestDiagonal(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY + i))) {
            moves.add(new Move(position, new Position(currentX - i, currentY + i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY + i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX - i, currentY + i)));
        }

        return moves;
    }

    public static Set<Move> northWestDiagonal(Board board, Position position, PieceColour colour) {
        Set<Move> moves = new HashSet<>();
        int currentX = position.getX();
        int currentY = position.getY();

        int i = 1;
        while (board.isEmpty(new Position(currentX - i, currentY - i))) {
            moves.add(new Move(position, new Position(currentX - i, currentY - i)));
            i++;
        }

        Piece blockingPiece = board.getSquare(new Position(currentX - i, currentY - i));

        if (blockingPiece != null && blockingPiece.getColour() == colour.opponent()) {
            moves.add(new Move(position, new Position(currentX - i, currentY - i)));
        }

        return moves;
    }
}
