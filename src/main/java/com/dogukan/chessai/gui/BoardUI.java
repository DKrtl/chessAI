package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Optional;

public class BoardUI extends GridPane {
    private final int size = 8;
    private Game game;
    private int width;
    private Position from;
    private Position to;

    BoardUI(Game game, int width) {
        this.width = width;
        this.game = game;

        setId("board");
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                createSquare(col, row);
            }
        }
    }

    private void createSquare(int col, int row) {
        StackPane stackPane = new StackPane();
        double dim = (double) width/(double) size;
        Rectangle square = new Rectangle(dim, dim);
        Optional<ImageView> piece = addImage(col, row);

        stackPane.getChildren().add(square);
        piece.ifPresent(imageView -> stackPane.getChildren().add(imageView));

        dragAndDrop(stackPane, col, row);
        clickListener(stackPane);

        square.getStyleClass().add((row + col) % 2 == 0 ? "lightSquare" : "darkSquare");
        stackPane.getStyleClass().add(stackPane.getChildren().size() > 1 ? "fullPane" : null);

        this.add(stackPane, col, row);
    }

    private Optional<ImageView> addImage(int col, int row) {
        Image img = null;
        Piece piece = game.getBoard().getSquare(new Position(col, row));

        if (piece instanceof Pawn) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhitePawn.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackPawn.png");
            }
        } else if (piece instanceof Rook) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhiteRook.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackRook.png");
            }
        } else if (piece instanceof Bishop) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhiteBishop.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackBishop.png");
            }
        } else if (piece instanceof Knight) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhiteKnight.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackKnight.png");
            }
        } else if (piece instanceof Queen) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhiteQueen.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackQueen.png");
            }
        } else if (piece instanceof King) {
            if (piece.getColour() == PieceColour.WHITE) {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/WhiteKing.png");
            } else {
                img = new Image("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/images/BlackKing.png");
            }
        }

        if(img != null) {
            return Optional.of(new ImageView(img));
        } else {
            return Optional.empty();
        }
    }

    private void dragAndDrop(StackPane stackPane, int col, int row) {
        stackPane.setOnDragDetected(event -> {
            List<Node> stackPaneChildren = stackPane.getChildren();
            if(stackPaneChildren.size() > 1 && !stackPane.getStyleClass().contains("rightClicked")) {
                stackPane.getStyleClass().add("selectedSquare");
                ImageView img = (ImageView) stackPaneChildren.get(stackPaneChildren.size() - 1);
                Dragboard db = img.startDragAndDrop(TransferMode.MOVE);

                ClipboardContent content = new ClipboardContent();
                content.putImage(img.getImage());
                db.setContent(content);

                event.consume();
            }
        });

        stackPane.setOnDragOver(event -> {
            if ((event.getGestureSource() != stackPane) && (stackPane.getChildren().size() == 1)) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        stackPane.setOnDragEntered(event -> {
            if ((event.getGestureSource() != stackPane) && (stackPane.getChildren().size() == 1)) {
                stackPane.getStyleClass().add("selectedSquare");
            }

            event.consume();
        });

        stackPane.setOnDragExited(event -> {
            stackPane.getStyleClass().remove("selectedSquare");

            event.consume();
        });

        stackPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                to = new Position(col, row);
                stackPane.getChildren().add(new ImageView(db.getImage()));
                stackPane.getStyleClass().add("fullPane");
                success = true;
            }

            event.setDropCompleted(success);

            event.consume();
        });

        stackPane.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                from = new Position(col, row);
                List<Node> stackPaneChildren = stackPane.getChildren();
                stackPane.getChildren().remove(stackPaneChildren.size() - 1);
                stackPane.getStyleClass().remove("fullPane");
                game.move(new Move(from, to));
            }

            makeBoard();

            event.consume();
        });
    }

    private void clickListener(StackPane stackPane) {
        stackPane.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                doubleClick(stackPane);
            } else if(click.getButton() == MouseButton.SECONDARY) {
                rightClick(stackPane);
            }
            makeBoard();
        });
    }

    private void rightClick(StackPane stackPane) {
        if(stackPane.getStyleClass().contains("rightClicked")) {
            stackPane.getStyleClass().remove("rightClicked");
        } else if(stackPane.getChildren().size() > 1) {
            stackPane.getStyleClass().add("rightClicked");
        }
    }

    private void doubleClick(StackPane stackPane) {
        List<Node> stackPaneChildren = stackPane.getChildren();
        if (stackPaneChildren.size() > 1 && !stackPane.getStyleClass().contains("rightClicked")) {
            stackPaneChildren.remove(stackPaneChildren.size() - 1);
            stackPane.getStyleClass().remove("fullPane");
            game.remove(new Position(getColumnIndex(stackPane), getRowIndex(stackPane)));
        }
    }

    // Testing purposes
    private void makeBoard() {
        com.dogukan.chessai.chess.Board board = game.getBoard();
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