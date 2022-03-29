package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;
import com.dogukan.chessai.chess.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AI {
    private Set<Position> selectedPieces;

    public AI() {
        this.selectedPieces = new HashSet<>();
    }

    public void addToSelected(Position position) {
        selectedPieces.add(position);
    }

    public void removeFromSelected(Position position) {
        selectedPieces.remove(position);
    }

    public GameState bestMove(PieceColour colour, GameState currentGameState, int depth) {
        Set<GameState> allPossibleMoves = currentGameState.allPossibleGameStates(colour);
        GameState maxGameState = new GameState(currentGameState.getPrev(), currentGameState.getBoard(),
                currentGameState.getCreativeMode());

        if(colour == PieceColour.WHITE) {
            int maxEval = Integer.MIN_VALUE;
            for(GameState gameState : allPossibleMoves) {
                MiniMaxTree tree = new MiniMaxTree(colour.opponent(), gameState, depth - 1);
                if(tree.getUtility() > maxEval) {
                    maxEval = tree.getUtility();
                    maxGameState = gameState;
                }
            }
        } else {
            int minEval = Integer.MAX_VALUE;
            for(GameState gameState : allPossibleMoves) {
                MiniMaxTree tree = new MiniMaxTree(colour.opponent(), gameState, depth - 1);
                if(tree.getUtility() < minEval) {
                    minEval = tree.getUtility();
                    maxGameState = gameState;
                }
            }
        }

        return maxGameState;
//        MiniMaxTree miniMaxTree = new MiniMaxTree(colour, currentGameState, 5);
//        MiniMaxNode node = miniMaxTree.getRoot();
//
//        List<MiniMaxNode> children = node.getChildren();
//
//        for(MiniMaxNode n : children) {
//            if(n.getUtility() == node.getUtility()) {
//                return n.getGameState();
//            }
//        }
//        return null;
    }
}