package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;

public class AI {
    private GameState gameState;
    private MiniMaxTree minMaxTree;

    public AI(GameState gameState) {
        this.gameState = gameState;
        this.minMaxTree = new MiniMaxTree(gameState, 1);
    }
}
