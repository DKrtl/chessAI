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
    private int upperBound;
    private int lowerBound;

    public MiniMaxNode(GameState currentGameState, PieceColour playerTurn, int utility) {
        this.currentGameState = currentGameState;
        this.children = new ArrayList<>();
        this.utility = utility;
        this.playerTurn = playerTurn;
    }

    public MiniMaxNode(MiniMaxNode miniMaxNode) {
        GameState miniMaxGameState = miniMaxNode.getGameState();
        this.currentGameState = new GameState(miniMaxGameState.getPrev(), miniMaxGameState.getBoard(),
                miniMaxGameState.getCreativeMode());
        this.children = new ArrayList<>(miniMaxNode.getChildren());
        this.utility = miniMaxNode.getUtility();
        this.playerTurn = miniMaxNode.getPlayerTurn();
        this.upperBound = miniMaxNode.upperBound;
        this.lowerBound = miniMaxNode.lowerBound;
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

    public void emptyChildren() {
        children.clear();
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void addChildren(List<MiniMaxNode> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiniMaxNode node = (MiniMaxNode) o;
        return currentGameState.equals(node.getGameState()) && playerTurn == node.getPlayerTurn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentGameState);
    }
}