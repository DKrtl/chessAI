package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AI {
    private Set<Position> selectedPieces;

    public AI(GameState currentGameState) {
        this.selectedPieces = new HashSet<>();
    }

    public void addToSelected(Position position) {
        selectedPieces.add(position);
    }

    public void removeFromSelected(Position position) {
        selectedPieces.remove(position);
    }

    public GameState bestMove(GameState currentGameState) {
        MiniMaxTree miniMaxTree = new MiniMaxTree(currentGameState, 3);
        MiniMaxNode node = miniMaxTree.getRoot();

        List<MiniMaxNode> children = node.getChildren();

        for(MiniMaxNode n : children) {
            if(n.getUtility() == node.getUtility()) {
                return n.getCurrentGameState();
            }
        }
        return null;
    }
}