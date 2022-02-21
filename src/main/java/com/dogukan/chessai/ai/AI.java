package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;

public class AI {
    private GameState gameState;
    private MinMaxTree minMaxTree;

    public AI(GameState gameState) {
        this.gameState = gameState;
        this.minMaxTree = new MinMaxTree(gameState);
    }


}
