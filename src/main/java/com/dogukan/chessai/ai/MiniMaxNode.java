package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.GameState;
import com.dogukan.chessai.chess.Move;
import com.dogukan.chessai.chess.Piece;
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
    private Piece movedPiece;
    private Move move;

    public MiniMaxNode(GameState currentGameState, PieceColour playerTurn, Piece movedPiece, Move move, int utility) {
        this.currentGameState = currentGameState;
        this.children = new ArrayList<>();
        this.utility = utility;
        this.playerTurn = playerTurn;
        this.movedPiece = movedPiece;
        this.move = move;
    }

    public PieceColour getPlayerTurn() {
        return playerTurn;
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

    public Move getMove() {
        return move;
    }

    public Piece getMovedPiece() {
        return movedPiece;
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