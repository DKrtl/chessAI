package com.dogukan.chessai.chess;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    private boolean initialMove;
    private boolean tookTwoSquareMove;

    Pawn(PieceColour colour) {
        super(colour);
        initialMove = true;
        tookTwoSquareMove = false;
    }

    @Override
    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares());
        legalMoves(newBoard, move.getFrom());

        if (getLegalMoves().contains(move)) {
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
            gameState.setNext(new GameState(gameState, gameState.getPlayerTurn().opponent(), newBoard));

            initialMove = false;
        }
    }

    @Override
    public void legalMoves(Board board, Position position) {
        forwardMove(board, position);
        takePiece(board, position);
    }

    private void forwardMove(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        if(initialMove) {
            moves.addAll(Direction.northMove(board, position, getColour(), 2));
        } else {
            moves.addAll(Direction.northMove(board, position, getColour(), 1));
        }

        moves.removeIf(move -> !board.isEmpty(move.getTo()));

        getLegalMoves().addAll(moves);
    }

    private void takePiece(Board board, Position position) {
        Set<Move> moves = new HashSet<>();

        moves.addAll(Direction.northEastMove(board, position, getColour(), 1));
        moves.removeIf(move -> board.isEmpty(move.getTo()) && !isEnPassantEastMove(board, move));

        moves.addAll(Direction.northWestMove(board, position, getColour(), 1));
        moves.removeIf(move -> board.isEmpty(move.getTo()) && !isEnPassantWestMove(board, move));

        getLegalMoves().addAll(moves);
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

    public boolean tookTwoSquareMove() {
        return tookTwoSquareMove;
    }
}