package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.Set;

public class MiniMaxTree {
    private MiniMaxNode root;

    public MiniMaxTree(PieceColour playerTurn, GameState currentGameState, int depth) {
        this.root = createTree(playerTurn, currentGameState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
    }

    private MiniMaxNode createTree(PieceColour playerTurn, GameState currentGameState, int alpha, int beta, int depth) {
        boolean checkmate = currentGameState.isCheckmate(playerTurn);
        if(checkmate) {
            if(playerTurn == PieceColour.WHITE) {
                return new MiniMaxNode(playerTurn, currentGameState, Integer.MIN_VALUE);
            } else {
                return new MiniMaxNode(playerTurn, currentGameState, Integer.MAX_VALUE);
            }
        } else if (depth == 0) {
            return new MiniMaxNode(playerTurn, currentGameState, currentGameState.getEvaluation());
        }

        Set<GameState> allPossibleGameStates = currentGameState.allPossibleGameStates(playerTurn);

        if (playerTurn == PieceColour.WHITE) {
            MiniMaxNode maxNode = new MiniMaxNode(playerTurn, currentGameState, Integer.MIN_VALUE);
            for (GameState gameState : allPossibleGameStates) {
                MiniMaxNode node = createTree(playerTurn.opponent(), gameState, alpha, beta, depth - 1);
                maxNode.addChild(node);
                int eval = node.getUtility();
                maxNode.setUtility(Integer.max(maxNode.getUtility(), eval));
                alpha = Integer.max(alpha, eval);
                if(beta <= alpha) {
                    break;
                }
            }
            return maxNode;
        } else {
            MiniMaxNode minNode = new MiniMaxNode(playerTurn, currentGameState, Integer.MAX_VALUE);
            for (GameState gameState : allPossibleGameStates) {
                MiniMaxNode node = createTree(playerTurn.opponent(), gameState, alpha, beta, depth - 1);
                minNode.addChild(node);
                int eval = node.getUtility();
                minNode.setUtility(Integer.min(minNode.getUtility(), eval));
                beta = Integer.min(beta, eval);
                if(beta <= alpha) {
                    break;
                }
            }
            return minNode;
        }
    }

    public MiniMaxNode getRoot() {
        return root;
    }
}