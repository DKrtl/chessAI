package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.Set;

public class MiniMaxTree {
    private int utility;

    public MiniMaxTree(PieceColour playerTurn, GameState currentGameState, int depth) {
        this.utility = createTree(playerTurn, currentGameState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
    }

    private int createTree(PieceColour playerTurn, GameState currentGameState, int alpha, int beta, int depth) {
        if (depth == 0 || currentGameState.gameOver()) {
            return currentGameState.getEvaluation();
        }

        Set<GameState> allPossibleGameStates = currentGameState.allPossibleGameStates(playerTurn);

        if (playerTurn == PieceColour.WHITE) {
            int maxEval = Integer.MIN_VALUE;
            for (GameState gameState : allPossibleGameStates) {
                int eval = createTree(playerTurn.opponent(), gameState, alpha, beta, depth - 1);
                maxEval = Integer.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (GameState gameState : allPossibleGameStates) {
                int eval = createTree(playerTurn.opponent(), gameState, alpha, beta, depth - 1);
                minEval = Integer.min(minEval, eval);
            }
            return minEval;
        }
    }

//        if (depth > 0) {
//            Board board = currentGameState.getBoard();
//            for (int i = 0; i < board.columnLength(); i++) {
//                for (int j = 0; j < board.rowLength(); j++) {
//                    Piece piece = board.getSquare(new Position(i, j));
//                    if (piece != null && piece.getColour() == colour) {
//                        Set<Move> legalMoves = piece.legalMoves(board, new Position(i, j));
//                        for(Move move : legalMoves) {
//                            GameState next = currentGameState.move(move);
//                            if(next != null) {
//                                MiniMaxNode child = createTree(colour.opponent(), next, alpha, beta, --depth);
//                                int currentUtility = node.getUtility();
//                                int childUtility = child.getUtility();
//
//                                if(piece.getColour() == PieceColour.WHITE) {
//                                    if(childUtility > currentUtility) {
//                                        node.setUtility(childUtility);
//                                    }
//                                    if(currentUtility > alpha) {
//                                        alpha = currentUtility;
//                                    }
//                                } else {
//                                    if(childUtility < currentUtility) {
//                                        node.setUtility(childUtility);
//                                    }
//                                    if(currentUtility < beta) {
//                                        beta = currentUtility;
//                                    }
//                                }
//                                if(beta <= alpha) {
//                                    break;
//                                }
//                                node.addChild(child);
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            node.setUtility(currentGameState.getEvaluation());
//        }
//
//        return node;

    public int getUtility() {
        return utility;
    }
}