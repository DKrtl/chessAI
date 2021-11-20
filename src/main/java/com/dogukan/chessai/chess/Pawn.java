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
                System.out.println("entered");
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
        oneSquareMove(board, position);
        twoSquareMove(board, position);
        takeLeft(board, position);
        takeRight(board, position);
        enPassantLeft(board, position);
        enPassantRight(board, position);
    }

    private boolean isEnPassantMove(Board board, Move move) {
        Piece pieceR = getRightPawnPiece(board, move.getFrom());
        Piece pieceL = getLeftPawnPiece(board, move.getFrom());

        if (pieceR instanceof Pawn) {
            System.out.println(((Pawn) pieceR).tookTwoSquareMove);
        }

        return (((pieceR instanceof Pawn && ((Pawn) pieceR).tookTwoSquareMove)) ||
                ((pieceL instanceof Pawn) && ((Pawn) pieceL).tookTwoSquareMove)) &&
                (board.isEmpty(move.getTo()));
    }

    private Piece getRightPawnPiece(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        }

        return piece;
    }

    private Piece getLeftPawnPiece(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece;

        if (getColour() == PieceColour.WHITE) {
            piece = board.getSquare(new Position(currentX - 1, currentY));
        } else {
            piece = board.getSquare(new Position(currentX + 1, currentY));
        }

        return piece;
    }

    private void enPassantRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece = getRightPawnPiece(board, position);

        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
            if (getColour() == PieceColour.WHITE) {
                if (board.isInRange(new Position(currentX + 1, currentY - 1))
                        && board.isEmpty(new Position(currentX + 1, currentY - 1))) {
                    getLegalMoves().add((new Move(position, new Position(currentX + 1, currentY - 1))));
                }
            } else {
                if (board.isInRange(new Position(currentX - 1, currentY + 1))
                        && board.isEmpty(new Position(currentX - 1, currentY + 1))) {
                    getLegalMoves().add((new Move(position, new Position(currentX - 1, currentY + 1))));
                }
            }
        }
    }

    private void enPassantLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Piece piece = getLeftPawnPiece(board, position);

        if ((piece instanceof Pawn) && ((Pawn) piece).tookTwoSquareMove) {
            if (getColour() == PieceColour.WHITE) {
                if (board.isInRange(new Position(currentX - 1, currentY - 1))
                        && board.isEmpty(new Position(currentX - 1, currentY - 1))) {
                    getLegalMoves().add((new Move(position, new Position(currentX - 1, currentY - 1))));
                }
            } else {
                if (board.isInRange(new Position(currentX + 1, currentY + 1))
                        && board.isEmpty(new Position(currentX + 1, currentY + 1))) {
                    getLegalMoves().add((new Move(position, new Position(currentX + 1, currentY + 1))));
                }
            }
        }
    }

    private void takeLeft(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeLeft;

        if (getColour() == PieceColour.WHITE) {
            takeLeft = new Move(position, new Position(currentX - 1, currentY - 1));
        } else {
            takeLeft = new Move(position, new Position(currentX + 1, currentY + 1));
        }

        if (board.isInRange(takeLeft.getTo()) && !board.isEmpty(takeLeft.getTo()) &&
                board.getSquare(takeLeft.getTo()).getColour() == getColour().opponent()) {
            getLegalMoves().add(takeLeft);
        }
    }

    private void takeRight(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move takeRight;

        if (getColour() == PieceColour.WHITE) {
            takeRight = new Move(position, new Position(currentX + 1, currentY - 1));
        } else {
            takeRight = new Move(position, new Position(currentX - 1, currentY + 1));
        }

        if (board.isInRange(takeRight.getTo()) && !board.isEmpty(takeRight.getTo()) &&
                board.getSquare(takeRight.getTo()).getColour() == getColour().opponent()) {
            getLegalMoves().add(takeRight);
        }
    }

    private void oneSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();

        Move oneSquareMove;

        if (getColour() == PieceColour.WHITE) {
            oneSquareMove = new Move(position, new Position(currentX, currentY - 1));
        } else {
            oneSquareMove = new Move(position, new Position(currentX, currentY + 1));
        }

        if (board.isInRange(oneSquareMove.getTo()) && board.isEmpty(oneSquareMove.getTo())) {
            getLegalMoves().add(oneSquareMove);
        }
    }

    private void twoSquareMove(Board board, Position position) {
        int currentX = position.getX();
        int currentY = position.getY();
        Move twoSquareMove;

        if (firstMove) {
            if (getColour() == PieceColour.WHITE) {
                twoSquareMove = new Move(position, new Position(currentX, currentY - 2));
            } else {
                twoSquareMove = new Move(position, new Position(currentX, currentY + 2));
            }

            if (board.isInRange(twoSquareMove.getTo()) && board.isEmpty(twoSquareMove.getTo())) {
                getLegalMoves().add(twoSquareMove);
            }
        }
    }
}