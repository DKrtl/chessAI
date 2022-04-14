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

    private MiniMaxTree drawTree(PieceColour playerTurn, GameState currentGameState, int depth) {
        return new MiniMaxTree(playerTurn, currentGameState, depth);
    }

    public void addToSelected(Position position) {
        selectedPieces.add(position);
    }

    public void removeFromSelected(Position position) {
        selectedPieces.remove(position);
    }

    public GameState getBestMove(PieceColour playerTurn, GameState currentGameState, int depth) {
        MiniMaxNode root = drawTree(playerTurn, currentGameState, depth).getRoot();
        if(selectedPieces.isEmpty()) {
            return bestMove(root).getGameState();
        } else {
            return null;
        }
    }

    private MiniMaxNode bestMove(MiniMaxNode root) {
        List<MiniMaxNode> children = root.getChildren();

        for(MiniMaxNode child : children) {
            if(child.getUtility() == root.getUtility()) {
                return child;
            }
        }
        return null;
    }
}