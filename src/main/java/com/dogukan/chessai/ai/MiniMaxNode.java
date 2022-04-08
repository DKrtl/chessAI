package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.PieceColour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MiniMaxNode {
    private GameState currentGameState;
    private List<MiniMaxNode> children;
    private int utility;
    private PieceColour playerTurn;
    private int depth;

    public MiniMaxNode(GameState currentGameState, PieceColour playerTurn, int utility, int depth) {
        this.currentGameState = currentGameState;
        this.children = new ArrayList<>();
        this.utility = utility;
        this.playerTurn = playerTurn;
        this.depth = depth;
    }

    public MiniMaxNode(MiniMaxNode miniMaxNode) {
        GameState miniMaxGameState = miniMaxNode.getGameState();
        this.currentGameState = new GameState(miniMaxGameState.getPrev(), miniMaxGameState.getBoard(),
                miniMaxGameState.getCreativeMode());
        this.children = new ArrayList<>(miniMaxNode.getChildren());
        this.utility = miniMaxNode.getUtility();
        this.playerTurn = miniMaxNode.getPlayerTurn();
    }

    public void addChild(MiniMaxNode child) {
        this.children.add(child);
    }

    public PieceColour getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(PieceColour playerTurn) {
        this.playerTurn = playerTurn;
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

    public void updateNode(MiniMaxNode node) {
        this.children = node.getChildren();
        this.currentGameState = node.getGameState();
        this.utility = node.getUtility();
    }

    public void emptyChildren() {
        children.clear();
    }

    public void addChildren(List children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiniMaxNode node = (MiniMaxNode) o;
        return Objects.equals(currentGameState, node.currentGameState) && playerTurn == node.getPlayerTurn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentGameState);
    }
}