package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AI {
    private Set<Piece> selectedPieces;

    public AI() {
        this.selectedPieces = new HashSet<>();
    }

    private MiniMaxTree drawTree(PieceColour playerTurn, GameState currentGameState) {
        return new MiniMaxTree(playerTurn, currentGameState);
    }

    public void addToSelected(Piece piece) {
        selectedPieces.add(piece);
    }

    public void removeFromSelected(Piece piece) {
        selectedPieces.remove(piece);
    }

    public List<Information> createInformation(PieceColour playerTurn, GameState currentGameState) {
        MiniMaxNode root = drawTree(playerTurn, currentGameState).getRoot();
        List<Information> informationList = new ArrayList<>();
        List<MiniMaxNode> children = root.getChildren();

        if(selectedPieces.isEmpty()) {
            for(MiniMaxNode child : children) {
                informationList.add(new Information(child.getMovedPiece(), child.getMove(),
                        root.getGameState().getEvaluation(), child.getUtility()));
            }
        } else {
            for(MiniMaxNode child : children) {
                if(selectedPieces.contains(child.getMovedPiece())) {
                    informationList.add(new Information(child.getMovedPiece(), child.getMove(),
                            root.getGameState().getEvaluation(), child.getUtility()));
                }
            }
        }
        return informationList;
    }
}