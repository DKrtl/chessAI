package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.Arrays;
import java.util.Set;

public class MiniMaxTree {
    private final MiniMaxNode root;
    private final int depth;

    public MiniMaxTree(GameState currentGameState, int depth) {
        this.root = createTree(currentGameState, depth);
        this.depth = depth;
    }

    private MiniMaxNode createTree(GameState currentGameState, int depth) {
        MiniMaxNode node = new MiniMaxNode(currentGameState);
        if (depth > 0) {
            Board board = currentGameState.getBoard();
            for (int i = 0; i < board.columnLength(); i++) {
                for (int j = 0; j < board.rowLength(); j++) {
                    Piece piece = board.getSquare(new Position(i, j));
                    if (piece != null) {
                        Set<Move> legalMoves = piece.legalMoves(board, new Position(i, j));
                        for(Move move : legalMoves) {
                            GameState next = piece.move(currentGameState, move);
                            node.addChild(createTree(next, depth - 1));
                        }
                    }
                }
            }
        }
        return node;
    }
}