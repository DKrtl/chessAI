package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;
import com.dogukan.chessai.chess.Position;

import java.util.HashSet;
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
        MiniMaxTree tree = new MiniMaxTree(colour, currentGameState, 5);

        for(MiniMaxNode child : tree.getRoot().getChildren()) {
            if(tree.getRoot().getUtility() == child.getUtility()) {
                return child.getGameState();
            }
        }
        return null;
    }
}