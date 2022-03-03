package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxNode {
    private GameState currentGameState;
    private List<MiniMaxNode> children;

    public MiniMaxNode(GameState currentGameState) {
        this.currentGameState = currentGameState;
        this.children = new ArrayList<>();
    }

    public void addChild(MiniMaxNode child) {
        this.children.add(child);
    }
}