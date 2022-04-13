package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;
import com.dogukan.chessai.chess.Position;

import java.util.HashSet;
import java.util.Set;

public class AI {
    private Set<Position> selectedPieces;
    private MiniMaxTree tree;

    public AI() {
        this.selectedPieces = new HashSet<>();
        this.tree = new MiniMaxTree();
    }

    public void addToSelected(Position position) {
        selectedPieces.add(position);
    }

    public void removeFromSelected(Position position) {
        selectedPieces.remove(position);
    }

    public GameState bestMove(PieceColour colour, GameState currentGameState, int depth) {
        return tree.getBestMove(colour, currentGameState, selectedPieces, depth);
    }
}