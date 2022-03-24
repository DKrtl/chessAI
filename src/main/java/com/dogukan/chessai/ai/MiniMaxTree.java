package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.Set;

public class MiniMaxTree {
    private final MiniMaxNode root;

    public MiniMaxTree(GameState currentGameState, int depth) {
        this.root = createTree(currentGameState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
    }

    private MiniMaxNode createTree(GameState currentGameState, int alpha, int beta, int depth) {
        MiniMaxNode node = new MiniMaxNode(currentGameState);
        depth--;
        if (depth > 0 && !currentGameState.isCheckmate(currentGameState.getPlayerTurn())) {
            Board board = currentGameState.getBoard();
            for (int i = 0; i < board.columnLength(); i++) {
                for (int j = 0; j < board.rowLength(); j++) {
                    Piece piece = board.getSquare(new Position(i, j));
                    if (piece != null) {
                        Set<Move> legalMoves = piece.legalMoves(board, new Position(i, j));
                        for(Move move : legalMoves) {
                            GameState next = currentGameState.move(move);
                            if(next != null) {
                                MiniMaxNode child = createTree(next, alpha, beta, depth);
                                int childNetStrength = child.getUtility();
                                int currentUtility = node.getUtility();
                                if(currentGameState.getPlayerTurn() == PieceColour.WHITE) {
                                    if(childNetStrength > currentUtility) {
                                        node.setUtility(childNetStrength);
                                    }
                                    if(currentUtility > alpha) {
                                        alpha = currentUtility;
                                    }
                                } else {
                                    if(childNetStrength < currentUtility) {
                                        node.setUtility(childNetStrength);
                                    }
                                    if(currentUtility < beta) {
                                        beta = currentUtility;
                                    }
                                }
                                if(beta <= alpha) {
                                    break;
                                }
                                node.addChild(child);
                            }
                        }
                    }
                }
            }
        } else {
            node.setUtility(currentGameState.getNetStrength());
        }

        return node;
    }

    public MiniMaxNode getRoot() {
        return root;
    }

//     Testing purposes
//    private void makeBoard() {
//        com.dogukan.chessai.chess.Board board = game.getBoard();
//        com.dogukan.chessai.chess.Board board = currentState.getBoard();
//        for (int y = 0; y < board.columnLength(); y++) {
//            for (int x = 0; x < board.rowLength(); x++) {
//                Piece piece = board.getSquare(new Position(x, y));
//                if (piece instanceof Pawn) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WP ");
//                    } else {
//                        System.out.print("BP ");
//                    }
//                } else if (piece instanceof Rook) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WR ");
//                    } else {
//                        System.out.print("BR ");
//                    }
//                } else if (piece instanceof Bishop) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WB ");
//                    } else {
//                        System.out.print("BB ");
//                    }
//                } else if (piece instanceof Knight) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WK ");
//                    } else {
//                        System.out.print("BK ");
//                    }
//                } else if (piece instanceof Queen) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WQ ");
//                    } else {
//                        System.out.print("BQ ");
//                    }
//                } else if (piece instanceof King) {
//                    if (piece.getColour() == PieceColour.WHITE) {
//                        System.out.print("WKi ");
//                    } else {
//                        System.out.print("BKi ");
//                    }
//                } else {
//                    System.out.print(" x ");
//                }
//            }
//            System.out.println();
//        }
//    }
}