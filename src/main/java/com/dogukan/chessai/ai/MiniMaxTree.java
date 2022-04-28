package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;
import javafx.util.Pair;

import java.util.*;

public class MiniMaxTree {
    private Map<MiniMaxNode, Pair<MiniMaxNode, Integer>> cache;
    private MiniMaxNode root;
    int count = 0;

    public MiniMaxTree(PieceColour playerTurn, GameState currentGameState) {
        this.cache = new HashMap<>();
        this.root = iterativeDeepening(playerTurn, currentGameState);
        sortNodes(root.getChildren(), playerTurn);

        for(MiniMaxNode child : root.getChildren()) {
            System.out.println(child.getUtility());
        }
    }

    private MiniMaxNode createTree(MiniMaxNode node, int alpha, int beta, int depth) {
        count++;
        Pair<MiniMaxNode, Integer> cachePair = cache.get(node);
        if(cachePair != null && cachePair.getValue() >= depth) {
            MiniMaxNode cacheNode = cachePair.getKey();
            int lowerBound = cacheNode.getLowerBound();
            int upperBound = cacheNode.getUpperBound();
            if((lowerBound >= beta) || (upperBound <= alpha)) {
                return node;
            }
            alpha = Integer.max(alpha, lowerBound);
            beta = Integer.min(beta, upperBound);
        }

        int eval;

        if(depth == 0) {
            eval = node.getGameState().getEvaluation();
        } else if(node.getPlayerTurn() == PieceColour.WHITE) {
            eval = Integer.MIN_VALUE;
            int a = alpha;
            node.addChildren(allPossibleGameStates(node));

            for(MiniMaxNode child : node.getChildren()) {
                if(eval >= beta) {
                    break;
                } else {
                    eval = Integer.max(eval, createTree(child, a, beta, depth - 1).getUtility());
                    a = Integer.max(a, eval);
                }
            }
        } else {
            eval = Integer.MAX_VALUE;
            int b = beta;
            node.addChildren(allPossibleGameStates(node));

            for(MiniMaxNode child : node.getChildren()) {
                if(eval <= alpha) {
                    break;
                } else {
                    eval = Integer.min(eval, createTree(child, alpha, b, depth - 1).getUtility());
                    b = Integer.min(b, eval);
                }
            }
        }

        if(eval <= alpha) {
            node.setUpperBound(eval);
        }
        if(eval > alpha && eval < beta) {
            node.setLowerBound(eval);
            node.setUpperBound(eval);
        }
        if(eval >= beta) {
            node.setLowerBound(eval);
        }

        node.setUtility(eval);
        cache.put(node, new Pair<>(node, depth));

        return node;
    }

    public List<MiniMaxNode> allPossibleGameStates(MiniMaxNode node) {
        List<MiniMaxNode> allPossibleGameStates = new ArrayList<>();

        GameState currentGameState = node.getGameState();
        PieceColour playerTurn = node.getPlayerTurn();
        Board board = currentGameState.getBoard();
        for(int i = 0; i < board.columnLength(); i++) {
            for(int j = 0; j < board.rowLength(); j++) {
                Position position = new Position(i, j);
                Piece piece = board.getSquare(position);
                if(piece != null && piece.getColour() == playerTurn) {
                    Set<Move> moves = piece.legalMoves(board, position);
                    for(Move move : moves) {
                        GameState next = currentGameState.move(move);
                        if(next != null) {
                            allPossibleGameStates.add(new MiniMaxNode(next, playerTurn.opponent(), piece, move, next.getEvaluation()));
                        }
                    }
                }
            }
        }
        sortNodes(allPossibleGameStates, playerTurn);
        return allPossibleGameStates;
    }

    private MiniMaxNode iterativeDeepening(PieceColour playerTurn, GameState currentGameState) {
        MiniMaxNode bestMove = null;

        for(int d = 2; d <= 5; d += 3) {
            MiniMaxNode node = createRoot(playerTurn, currentGameState);
            bestMove = createTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE, d);
            if(bestMove.getUtility() == Integer.MAX_VALUE ||
                    bestMove.getUtility() == Integer.MIN_VALUE) {
                break;
            }
        }

        cache = new HashMap<>();

        return bestMove;
    }

    private MiniMaxNode createRoot(PieceColour playerTurn, GameState currentGameState) {
        MiniMaxNode node;

        if(playerTurn == PieceColour.WHITE) {
            node = new MiniMaxNode(currentGameState, playerTurn, null, null, Integer.MIN_VALUE);
        } else {
            node = new MiniMaxNode(currentGameState, playerTurn, null, null, Integer.MAX_VALUE);
        }

        return node;
    }

    private void sortNodes(List<MiniMaxNode> nodes, PieceColour playerTurn) {
        nodes.sort((o1, o2) -> {
            if(playerTurn == PieceColour.WHITE) {
                if(o1.getUtility() == Integer.MAX_VALUE && o2.getUtility() < 0) {
                    return -1;
                } else {
                    return o2.getUtility() - o1.getUtility();
                }
            } else {
                if(o1.getUtility() == Integer.MIN_VALUE && o2.getUtility() > 0) {
                    return -1;
                } else {
                    return o1.getUtility() - o2.getUtility();
                }
            }
        });
    }

    public MiniMaxNode getRoot() {
        return root;
    }
}