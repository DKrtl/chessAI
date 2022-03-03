package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;

import java.util.HashSet;
import java.util.Set;

public class MiniMaxNode {
    private GameState currentGameState;
    private Set<MiniMaxNode> children;
    private int currentUtility;

    public MiniMaxNode(GameState currentGameState) {
        this.currentGameState = currentGameState;
        this.children = new HashSet<>();
        this.currentUtility = currentGameState.getNetStrength();
    }

    public void addChild(MiniMaxNode child) {
        if(currentGameState.getPlayerTurn() == PieceColour.WHITE) {
            int childNetStrength = child.getCurrentGameState().getNetStrength();
            if(childNetStrength > currentUtility) {
                currentUtility = childNetStrength;
            }
        } else if(currentGameState.getPlayerTurn() == PieceColour.BLACK) {
            int childNetStrength = child.getCurrentGameState().getNetStrength();
            if(childNetStrength < currentUtility) {
                currentUtility = childNetStrength;
            }
        }
        this.children.add(child);
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public int getCurrentUtility() {
        return currentUtility;
    }
}