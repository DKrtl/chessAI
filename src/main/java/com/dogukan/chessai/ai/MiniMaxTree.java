package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;

public class MiniMaxTree {
    private MiniMaxNode root;
    private int depth;

    public MiniMaxTree(GameState currentGameState, int depth) {
        this.root = createTree(currentGameState);
        this.depth = depth;
    }

    // to be complete
    private MiniMaxNode createTree(GameState currentGameState) {
        return new MiniMaxNode(currentGameState);
    }

    // add child for each possible move in current state then recurse on
    // the children.
}
