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
        if (depth > 0) {
            Board board = currentGameState.getBoard();
            for (int i = 0; i < board.columnLength(); i++) {
                for (int j = 0; j < board.rowLength(); j++) {
                    Piece piece = board.getSquare(new Position(i, j));
                    if (piece != null) {
                        Set<Move> legalMoves = piece.legalMoves(board, new Position(i, j));
                        for(Move move : legalMoves) {
                            GameState next = currentGameState.move(move);
                            if(next != null) {
                                MiniMaxNode child = createTree(next, alpha, beta, depth - 1);
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
}