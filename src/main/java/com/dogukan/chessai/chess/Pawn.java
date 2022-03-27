package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    private boolean tookTwoSquareMove;

    Pawn(PieceColour colour) {
        super(colour);
        tookTwoSquareMove = false;
        setStrength(100);
        int[][] pieceSquareTable = {{0,  0,  0,  0,  0,  0,  0,  0},
                                    {50, 50, 50, 50, 50, 50, 50, 50},
                                    {10, 10, 20, 30, 30, 20, 10, 10},
                                    {5,  5, 10, 25, 25, 10,  5,  5},
                                    {0,  0,  0, 20, 20,  0,  0,  0},
                                    {5, -5,-10,  0,  0,-10, -5,  5},
                                    {5, 10, 10,-20,-20, 10, 10,  5},
                                    {0,  0,  0,  0,  0,  0,  0,  0}};
        setPieceSquareTable(pieceSquareTable);
    }

    @Override
    public Board move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares(), true);
        Set<Move> moves = legalMoves(newBoard, move.getFrom());

        if (moves.contains(move) && !(board.getSquare(move.getTo()) instanceof King)) {
            tookTwoSquareMove = (move.distance() == 2);

            if (isEnPassantMove(board, move)) {
                Position position = move.getTo();
                int nextX = position.getX();
                int nextY = position.getY();

                if (getColour() == PieceColour.WHITE) {
                    newBoard.removePiece(new Position(nextX, nextY + 1));
                } else {
                    newBoard.removePiece(new Position(nextX, nextY - 1));
                }
            }

            newBoard.removePiece(move.getFrom());
            newBoard.addPiece(move.getTo(), this);

            return newBoard;
        }
        return null;
    }

    @Override
    public Set<Move> legalMoves(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(forwardMove(board, position));
        moves.addAll(takePiece(board, position));

        return moves;
    }

    private Set<Move> forwardMove(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        if((position.getY() == board.columnLength() - 2 && getColour() == PieceColour.WHITE) ||
                (position.getY() == 1 && getColour() == PieceColour.BLACK)) {
            moves.addAll(Direction.northMove(board, position, getColour(), 2));
        } else {
            moves.addAll(Direction.northMove(board, position, getColour(), 1));
        }

        moves.removeIf(move -> !board.isEmpty(move.getTo()));

        return moves;
    }

    private Set<Move> takePiece(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(Direction.northEastMove(board, position, getColour(), 1));
        moves.removeIf(move -> board.isEmpty(move.getTo()) && !isEnPassantEastMove(board, move));

        moves.addAll(Direction.northWestMove(board, position, getColour(), 1));
        moves.removeIf(move -> board.isEmpty(move.getTo()) && !isEnPassantWestMove(board, move));

        return moves;
    }

    private boolean isEnPassantMove(Board board, Move move) {
        Piece pieceEast = getEastPiece(board, move.getFrom());
        Piece pieceWest = getWestPiece(board, move.getFrom());

        return (((pieceEast instanceof Pawn && ((Pawn) pieceEast).tookTwoSquareMove)) ||
                ((pieceWest instanceof Pawn) && ((Pawn) pieceWest).tookTwoSquareMove)) &&
                (board.isEmpty(move.getTo()));
    }

    private boolean isEnPassantEastMove(Board board, Move move) {
        Piece pieceEast = getEastPiece(board, move.getFrom());

        return (pieceEast instanceof Pawn && ((Pawn) pieceEast).tookTwoSquareMove);
    }

    private boolean isEnPassantWestMove(Board board, Move move) {
        Piece pieceWest = getWestPiece(board, move.getFrom());

        return (pieceWest instanceof Pawn && ((Pawn) pieceWest).tookTwoSquareMove);
    }

    public Piece getEastPiece(Board board, Position position) {
        Set<Move> moves = (Direction.eastMove(board, position, getColour(), 1));

        if(!moves.isEmpty()) {
            Move eastMove = (Move) moves.toArray()[0];
            return board.getSquare(eastMove.getTo());
        } else {
            return null;
        }
    }

    public Piece getWestPiece(Board board, Position position) {
        Set<Move> moves = (Direction.westMove(board, position, getColour(), 1));

        if(!moves.isEmpty()) {
            Move westMove = (Move) moves.toArray()[0];
            return board.getSquare(westMove.getTo());
        } else {
            return null;
        }
    }
}