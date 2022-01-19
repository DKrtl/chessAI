package com.dogukan.chessai.chess;

public class Pawn extends Piece {

    private boolean firstMove;
    private boolean tookTwoSquareMove;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
        tookTwoSquareMove = false;
    }

    @Override
    public void move(GameState gameState, Move move) {
        Board board = gameState.getBoard();
        Board newBoard = new Board(board.getSquares());
        legalMoves(newBoard, move.getFrom());

        if (getLegalMoves().contains(move)) {
            if (move.distance() == 2) {
                tookTwoSquareMove = true;
            } else {
                tookTwoSquareMove = false;
            }

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

            firstMove = false;
        }
    }

    @Override
    public void legalMoves(Board board, Position position) {
//        getLegalMoves().addAll(Direction.enPassantRight(board, position, this));
//        getLegalMoves().addAll(Direction.enPassantLeft(board, position, this));
//        getLegalMoves().addAll(Direction.oneSquareMove(board, position, this));
//        getLegalMoves().addAll(Direction.twoSquareMove(board, position, this));
//        getLegalMoves().addAll(Direction.takeLeft(board, position, this));
//        getLegalMoves().addAll(Direction.takeRight(board, position, this));
    }

    private boolean isEnPassantMove(Board board, Move move) {
        Piece pieceR = Direction.getRightPawnPiece(board, move.getFrom(), this);
        Piece pieceL = Direction.getLeftPawnPiece(board, move.getFrom(), this);

        return (((pieceR instanceof Pawn && ((Pawn) pieceR).tookTwoSquareMove)) ||
                ((pieceL instanceof Pawn) && ((Pawn) pieceL).tookTwoSquareMove)) &&
                (board.isEmpty(move.getTo()));
    }

    public boolean hasTookTwoSquareMove() {
        return tookTwoSquareMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }
}