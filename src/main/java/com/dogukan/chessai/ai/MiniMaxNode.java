package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxNode {
    private final GameState currentGameState;
    private final List<MiniMaxNode> children;
    private int utility;

    public MiniMaxNode(PieceColour colour, GameState currentGameState, int utility) {
        this.currentGameState = currentGameState;
        this.children = new ArrayList<>();
        this.utility = utility;
    }

    public void addChild(MiniMaxNode child) {
        this.children.add(child);
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int getUtility() {
        return utility;
    }

    public List<MiniMaxNode> getChildren() {
        return children;
    }
}