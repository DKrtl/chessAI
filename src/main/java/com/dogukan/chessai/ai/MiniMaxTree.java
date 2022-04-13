package com.dogukan.chessai.ai;

import com.dogukan.chessai.chess.*;

import java.util.*;

public class MiniMaxTree {
    private Map<MiniMaxNode, MiniMaxNode> cache;

    public MiniMaxTree() {
        this.cache = new HashMap<>();
    }

    private MiniMaxNode createTree(MiniMaxNode miniMaxNode, int alpha, int beta, int depth) {
        boolean checkmate = miniMaxNode.getGameState().isCheckmate(miniMaxNode.getPlayerTurn());
        if(checkmate) {
            if(miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
                miniMaxNode.setUtility(Integer.MIN_VALUE);
            } else {
                miniMaxNode.setUtility(Integer.MAX_VALUE);
            }
            return miniMaxNode;
        } else if(depth == 0) {
            return miniMaxNode;
        }

        if (miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
            miniMaxNode.setUtility(Integer.MIN_VALUE);

            if (calculateChildren(miniMaxNode, depth - 1)) {
                return miniMaxNode;
            }

            for(MiniMaxNode child : miniMaxNode.getChildren()) {
                child.setPlayerTurn(miniMaxNode.getPlayerTurn().opponent());
                child.updateNode(createTree(child, alpha, beta, depth - 1));
                int eval = child.getUtility();
                miniMaxNode.setUtility(Integer.max(miniMaxNode.getUtility(), eval));
                alpha = Integer.max(alpha, eval);
                if(beta <= alpha) {
                    break;
                }
            }
        } else {
            miniMaxNode.setUtility(Integer.MAX_VALUE);

            if (calculateChildren(miniMaxNode, depth - 1)) {
                return miniMaxNode;
            }

            for (MiniMaxNode child : miniMaxNode.getChildren()) {
                child.setPlayerTurn(miniMaxNode.getPlayerTurn().opponent());
                child.updateNode(createTree(child, alpha, beta, depth - 1));
                int eval = child.getUtility();
                miniMaxNode.setUtility(Integer.min(miniMaxNode.getUtility(), eval));
                beta = Integer.min(beta, eval);
                if(beta <= alpha) {
                    break;
                }
            }
        }

        MiniMaxNode cacheNode = cache.get(miniMaxNode);
        if(cacheNode != null) {
            if(depth > cacheNode.getDepth()) {
                cache.put(miniMaxNode, miniMaxNode);
            }
        } else {
            cache.put(miniMaxNode, miniMaxNode);
        }

        return miniMaxNode;
    }

    private boolean calculateChildren(MiniMaxNode miniMaxNode, int depth) {
        MiniMaxNode cacheNode = cache.get(miniMaxNode);
        if(cacheNode != null) {
            if(depth <= cacheNode.getDepth()) {
                MiniMaxNode child = cacheNode.getChildren().get(0);
                int eval = child.getUtility();

                if(miniMaxNode.getPlayerTurn() == PieceColour.WHITE) {
                    miniMaxNode.setUtility(Integer.max(miniMaxNode.getUtility(), eval));
                } else {
                    miniMaxNode.setUtility(Integer.min(miniMaxNode.getUtility(), eval));
                }

                miniMaxNode.addChild(child);
                return true;
            } else {
                sortNodes(cacheNode.getChildren(), cacheNode.getPlayerTurn());
                miniMaxNode.addChildren(cacheNode.getChildren());
            }
        } else {
            miniMaxNode.addChildren(allPossibleGameStates(miniMaxNode, depth));
        }
        return false;
    }

    public List<MiniMaxNode> allPossibleGameStates(MiniMaxNode node, int depth) {
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
                            allPossibleGameStates.add(new MiniMaxNode(next, playerTurn, next.getEvaluation(), depth));
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

        for(int d = 1; d <= maxDepth; d++) {
            MiniMaxNode node = createRoot(playerTurn, currentGameState, d);
            bestMove = bestMove(createTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE, d));
            if(bestMove.getUtility() == Integer.MAX_VALUE ||
                bestMove.getUtility() == Integer.MIN_VALUE) {
                break;
            }
        }

        cache = new HashMap<>();

        return bestMove;
    }

    private MiniMaxNode createRoot(PieceColour playerTurn, GameState currentGameState, int depth) {
        MiniMaxNode node;

        if(playerTurn == PieceColour.WHITE) {
            node = new MiniMaxNode(currentGameState, playerTurn, Integer.MIN_VALUE, depth);
        } else {
            node = new MiniMaxNode(currentGameState, playerTurn, Integer.MAX_VALUE, depth);
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

    private MiniMaxNode bestMove(MiniMaxNode root) {
        List<MiniMaxNode> children = root.getChildren();

        for(MiniMaxNode child : children) {
            if(child.getUtility() == root.getUtility()) {
                return child;
            }
        }
        return null;
    }

    public GameState getBestMove(PieceColour playerTurn, GameState currentGameState, Set<Position> selectedPieces, int maxDepth) {
        MiniMaxNode root = iterativeDeepening(playerTurn, currentGameState, maxDepth);
        if(selectedPieces.isEmpty()) {
            return root.getGameState();
        } else {
            return null;
        }
    }

    private void showBoards(Board board) {
        makeBoard(board);
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