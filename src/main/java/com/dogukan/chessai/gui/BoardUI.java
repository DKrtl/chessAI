package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.*;
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
    private PieceColour playerColour;

    BoardUI(Game game, int width) {
        this.width = width;
        this.game = game;
        this.playerColour = PieceColour.WHITE;

        draw();
    }

    public void draw() {
        setId("board");
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                createSquare(col, row);
            }
        }
    }

    public void togglePlayerColour() {
        getChildren().removeAll();
        playerColour = playerColour.opponent();
    }

    private void createSquare(int col, int row) {
        StackPane stackPane = new StackPane();
        double dim = (double) (width - 8)/(double) size;
        Rectangle square = new Rectangle(dim, dim);
        Optional<ImageView> piece = addImage(col, row);

        stackPane.getChildren().add(square);
        piece.ifPresent(imageView -> stackPane.getChildren().add(imageView));

        dragAndDrop(stackPane, col, row);
        clickListener(stackPane);

        square.getStyleClass().add((row + col) % 2 == 0 ? "lightSquare" : "darkSquare");
        stackPane.getStyleClass().add(stackPane.getChildren().size() > 1 ? "fullPane" : null);

        if(playerColour == PieceColour.WHITE) {
            this.add(stackPane, col, row);
        } else {
            this.add(stackPane, 7 - col, 7 - row);
        }
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
        });
    }

    private void rightClick(StackPane stackPane) {
        Position position = new Position(getColumnIndex(stackPane), getRowIndex(stackPane));
        if(stackPane.getStyleClass().contains("rightClicked")) {
            stackPane.getStyleClass().remove("rightClicked");
            game.removeFromAI(game.getBoard().getSquare(position));
        } else if(stackPane.getChildren().size() > 1) {
            stackPane.getStyleClass().add("rightClicked");
            game.addToAI(game.getBoard().getSquare(position));
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
}