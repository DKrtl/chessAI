package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;
import javafx.util.Pair;

import java.util.*;

public class MiniMaxTree {
    private Map<MiniMaxNode, Pair<MiniMaxNode, Integer>> cache;
    private MiniMaxNode root;
    int count = 0;

    public MiniMaxTree(PieceColour playerTurn, GameState currentGameState, int depth) {
        this.cache = new HashMap<>();
        this.root = iterativeDeepening(playerTurn, currentGameState, depth);
    }

//    private MiniMaxNode createTree(MiniMaxNode miniMaxNode, int alpha, int beta, int depth) {
//        count++;
//        boolean checkmate = miniMaxNode.getGameState().isCheckmate(miniMaxNode.getPlayerTurn());
//        if(checkmate) {
//            if(miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
//                miniMaxNode.setUtility(Integer.MIN_VALUE);
//            } else {
//                miniMaxNode.setUtility(Integer.MAX_VALUE);
//            }
//            return miniMaxNode;
//        } else if(depth == 0) {
//            return miniMaxNode;
//        }
//
//        if (miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
//            miniMaxNode.setUtility(Integer.MIN_VALUE);
//
//            if (calculateChildren(miniMaxNode, depth)) {
//                for(MiniMaxNode child : miniMaxNode.getChildren()) {
//                    createTree(child, alpha, beta, depth - 1);
//                    int eval = child.getUtility();
//                    miniMaxNode.setUtility(Integer.max(miniMaxNode.getUtility(), eval));
//                    alpha = Integer.max(alpha, eval);
//                    if(beta <= alpha) {
//                        break;
//                    }
//                }
//            }
//
//        } else {
//            miniMaxNode.setUtility(Integer.MAX_VALUE);
//
//            if (calculateChildren(miniMaxNode, depth)) {
//                for (MiniMaxNode child : miniMaxNode.getChildren()) {
//                    createTree(child, alpha, beta, depth - 1);
//                    int eval = child.getUtility();
//                    miniMaxNode.setUtility(Integer.min(miniMaxNode.getUtility(), eval));
//                    beta = Integer.min(beta, eval);
//                    if(beta <= alpha) {
//                        break;
//                    }
//                }
//            }
//        }
//
////        cache.put(miniMaxNode, new Pair<>(miniMaxNode, depth));
//
//        return miniMaxNode;
//    }

//    private MiniMaxNode createTree(MiniMaxNode miniMaxNode, int depth) {
//        count++;
//        boolean checkmate = miniMaxNode.getGameState().isCheckmate(miniMaxNode.getPlayerTurn());
//        if(checkmate) {
//            if(miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
//                miniMaxNode.setUtility(Integer.MIN_VALUE);
//            } else {
//                miniMaxNode.setUtility(Integer.MAX_VALUE);
//            }
//            return miniMaxNode;
//        } else if(depth == 0) {
//            return miniMaxNode;
//        }
//
//        if (miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
//            miniMaxNode.setUtility(Integer.MIN_VALUE);
//
//            if (calculateChildren(miniMaxNode, depth)) {
//                for(MiniMaxNode child : miniMaxNode.getChildren()) {
//                    child.setAlpha(miniMaxNode.getAlpha());
//                    child.setBeta(miniMaxNode.getBeta());
//
//                    createTree(child,depth - 1);
//                    int eval = child.getUtility();
//                    miniMaxNode.setUtility(Integer.max(miniMaxNode.getUtility(), eval));
//                    miniMaxNode.setAlpha(Integer.max(miniMaxNode.getAlpha(), eval));
//                    if(miniMaxNode.getBeta() <= miniMaxNode.getAlpha()) {
//                        break;
//                    }
//                }
//            }
//
//        } else {
//            miniMaxNode.setUtility(Integer.MAX_VALUE);
//
//            if (calculateChildren(miniMaxNode, depth)) {
//                for (MiniMaxNode child : miniMaxNode.getChildren()) {
//                    child.setAlpha(miniMaxNode.getAlpha());
//                    child.setBeta(miniMaxNode.getBeta());
//
//                    createTree(child,depth - 1);
//                    int eval = child.getUtility();
//                    miniMaxNode.setUtility(Integer.min(miniMaxNode.getUtility(), eval));
//                    miniMaxNode.setBeta(Integer.min(miniMaxNode.getBeta(), eval));
//                    if(miniMaxNode.getBeta() <= miniMaxNode.getAlpha()) {
//                        break;
//                    }
//                }
//            }
//        }
//
//        cache.put(miniMaxNode, new Pair<>(miniMaxNode, depth));
//
//        return miniMaxNode;
//    }

//    public List<MiniMaxNode> allPossibleGameStates(MiniMaxNode node) {
//        List<MiniMaxNode> allPossibleGameStates = new ArrayList<>();
//
//        GameState currentGameState = node.getGameState();
//        PieceColour playerTurn = node.getPlayerTurn();
//        Board board = currentGameState.getBoard();
//        for(int i = 0; i < board.columnLength(); i++) {
//            for(int j = 0; j < board.rowLength(); j++) {
//                Position position = new Position(i, j);
//                Piece piece = board.getSquare(position);
//                if(piece != null && piece.getColour() == playerTurn) {
//                    Set<Move> moves = piece.legalMoves(board, position);
//                    for(Move move : moves) {
//                        GameState next = currentGameState.move(move);
//                        if(next != null) {
//                            allPossibleGameStates.add(new MiniMaxNode(next, playerTurn.opponent(), next.getEvaluation()));
//                        }
//                    }
//                }
//            }
//        }
//        sortNodes(allPossibleGameStates, playerTurn);
//        return allPossibleGameStates;
//    }

//    private boolean calculateChildren(MiniMaxNode miniMaxNode, int depth) {
//        Pair<MiniMaxNode, Integer> cachePair = cache.get(miniMaxNode);
//        if(cachePair != null) {
//            if(depth <= cachePair.getValue()) {
//                MiniMaxNode cacheNode = cachePair.getKey();
//                List<MiniMaxNode> children = cacheNode.getChildren();
//                sortNodes(children, miniMaxNode.getPlayerTurn());
//                miniMaxNode.addChildren(children);
//                MiniMaxNode child = children.get(0);
//                int eval = child.getUtility();
//
//                if(miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
//                    miniMaxNode.setUtility(Integer.max(miniMaxNode.getUtility(), eval));
//                    miniMaxNode.setAlpha(Integer.max(miniMaxNode.getAlpha(), eval));
//                } else {
//                    miniMaxNode.setUtility(Integer.min(miniMaxNode.getUtility(), eval));
//                    miniMaxNode.setBeta(Integer.min(miniMaxNode.getBeta(), eval));
//                }
//
//                return false;
//            } else {
//                MiniMaxNode cacheNode = cachePair.getKey();
//                List<MiniMaxNode> children = cacheNode.getChildren();
//                sortNodes(children, miniMaxNode.getPlayerTurn());
//                miniMaxNode.addChildren(children);
////                miniMaxNode.addChildren(allPossibleGameStates(miniMaxNode));
//            }
//        } else {
//            miniMaxNode.addChildren(allPossibleGameStates(miniMaxNode));
//        }
//
//        return true;
//    }

//    private int createTree(MiniMaxNode node, int alpha, int beta, int depth) {
//        count++;
//        Pair<MiniMaxNode, Integer> cachePair = cache.get(node);
//        if(cachePair != null) {
//            if(node.getLowerBound() >= beta) {
//                return node.getLowerBound();
//            }
//            if(node.getUpperBound() <= alpha) {
//                return node.getUpperBound();
//            }
//            alpha = Integer.max(alpha, node.getLowerBound());
//            beta = Integer.min(beta, node.getUpperBound());
//        }
//
//        int eval;
//
//        if(depth == 0) {
//            eval = node.getGameState().getEvaluation();
//        } else if(node.getPlayerTurn() == PieceColour.WHITE) {
//            eval = Integer.MIN_VALUE;
//            int a = alpha;
//            node.addChildren(allPossibleGameStates(node));
//
//            for(MiniMaxNode child : node.getChildren()) {
//                if(eval >= beta) {
//                    break;
//                } else {
//                    eval = Integer.max(eval, createTree(child, a, beta, depth - 1));
//                    a = Integer.max(a, eval);
//                }
//            }
//        } else {
//            eval = Integer.MAX_VALUE;
//            int b = beta;
//            node.addChildren(allPossibleGameStates(node));
//
//            for(MiniMaxNode child : node.getChildren()) {
//                if(eval <= alpha) {
//                    break;
//                } else {
//                    eval = Integer.min(eval, createTree(child, alpha, b, depth - 1));
//                    b = Integer.min(b, eval);
//                }
//            }
//        }
//
//        if(eval <= alpha) {
//            node.setUpperBound(eval);
//        }
//        if(eval > alpha && eval < beta) {
//            node.setLowerBound(eval);
//            node.setUpperBound(eval);
//        }
//        if(eval >= beta) {
//            node.setLowerBound(eval);
//        }
//
//        cache.put(node, new Pair<>(node, depth));
//
//        return eval;
//    }

    private MiniMaxNode createTree(MiniMaxNode node, int alpha, int beta, int depth) {
        count++;
        Pair<MiniMaxNode, Integer> cachePair = cache.get(node);
        if(cachePair != null && cachePair.getValue() >= depth) {
            int lowerBound = node.getLowerBound();
            int upperBound = node.getUpperBound();
            if((lowerBound >= beta) || (upperBound <= alpha) ||
                    (node.getUpperBound() == node.getLowerBound())) {
                return node;
            }
            alpha = Integer.max(alpha, node.getLowerBound());
            beta = Integer.min(beta, node.getUpperBound());
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
                            allPossibleGameStates.add(new MiniMaxNode(next, playerTurn.opponent(), next.getEvaluation()));
                        }
                    }
                }
            }
        }
        sortNodes(allPossibleGameStates, playerTurn);
        return allPossibleGameStates;
    }


    private MiniMaxNode iterativeDeepening(PieceColour playerTurn, GameState currentGameState, int maxDepth) {
        MiniMaxNode bestMove = null;

        for(int d = 2; d <= 5; d += 3) {
            MiniMaxNode node = createRoot(playerTurn, currentGameState);
            bestMove = createTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE, d);
            if(bestMove.getUtility() == Integer.MAX_VALUE ||
                    bestMove.getUtility() == Integer.MIN_VALUE) {
                break;
            }
        }

//        MiniMaxNode node = createRoot(playerTurn, currentGameState);
//        bestMove = createTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE, 5);

        System.out.println(count);
        count = 0;

        cache = new HashMap<>();

        return bestMove;
    }

    private MiniMaxNode createRoot(PieceColour playerTurn, GameState currentGameState) {
        MiniMaxNode node;

        if(playerTurn == PieceColour.WHITE) {
            node = new MiniMaxNode(currentGameState, playerTurn, Integer.MIN_VALUE);
        } else {
            node = new MiniMaxNode(currentGameState, playerTurn, Integer.MAX_VALUE);
        }

        return node;
    }

    private void sortNodes(List<MiniMaxNode> nodes, PieceColour playerTurn) {
        nodes.sort((o1, o2) -> {
            if(playerTurn == PieceColour.WHITE) {
                return o2.getUtility() - o1.getUtility();
            } else {
                return o1.getUtility() - o2.getUtility();
            }
        });
    }

    public MiniMaxNode getRoot() {
        return root;
    }

    private Board newGame() {
        Board board = new Board(new Piece[8][8], false);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (j == 1) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.BLACK));
                } else if (j == 6) {
                    board.addPiece(new Position(i, j), new Pawn(PieceColour.WHITE));
                } else if (j == 0) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.BLACK));
                    } else if (i == 1 || i == 6) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.BLACK));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.BLACK));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.BLACK));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.BLACK));
                    }
                } else if (j == 7) {
                    if (i == 0 || i == 7) {
                        board.addPiece(new Position(i , j), new Rook(PieceColour.WHITE));
                    } else if (i == 1 || i == 6) {
                        board.addPiece(new Position(i , j), new Knight(PieceColour.WHITE));
                    } else if (i == 2 || i == 5) {
                        board.addPiece(new Position(i , j), new Bishop(PieceColour.WHITE));
                    } else if (i == 3) {
                        board.addPiece(new Position(i, j), new Queen(PieceColour.WHITE));
                    } else {
                        board.addPiece(new Position(i, j), new King(PieceColour.WHITE));
                    }
                }
            }
        }
        return board;
    }

    private static void makeBoard(Board board) {
        for (int y = 0; y < board.columnLength(); y++) {
            for (int x = 0; x < board.rowLength(); x++) {
                Piece piece = board.getSquare(new Position(x, y));
                if (piece instanceof Pawn) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WP ");
                    } else {
                        System.out.print("BP ");
                    }
                } else if (piece instanceof Rook) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WR ");
                    } else {
                        System.out.print("BR ");
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WB ");
                    } else {
                        System.out.print("BB ");
                    }
                } else if (piece instanceof Knight) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WK ");
                    } else {
                        System.out.print("BK ");
                    }
                } else if (piece instanceof Queen) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WQ ");
                    } else {
                        System.out.print("BQ ");
                    }
                } else if (piece instanceof King) {
                    if (piece.getColour() == PieceColour.WHITE) {
                        System.out.print("WKi ");
                    } else {
                        System.out.print("BKi ");
                    }
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
}