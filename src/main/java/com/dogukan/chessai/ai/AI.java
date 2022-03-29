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

    public GameState bestMove(PieceColour colour, GameState currentGameState) {
        MiniMaxTree miniMaxTree = new MiniMaxTree(colour, currentGameState, 4);
        MiniMaxNode node = miniMaxTree.getRoot();

        List<MiniMaxNode> children = node.getChildren();

        for(MiniMaxNode n : children) {
            if(n.getUtility() == node.getUtility()) {
                return n.getGameState();
            }
        }
        return null;
    }
}