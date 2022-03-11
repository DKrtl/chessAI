package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Board extends GridPane {
    private final int size = 8;
    private Game game;
    private int width;

    Board(Game game, int width) {
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

        stackPane.getChildren().addAll(square, addImage(col, row));
        square.getStyleClass().add((row + col) % 2 == 0 ? "lightSquare" : "darkSquare");

        this.add(stackPane, col, row);
    }

    private ImageView addImage(int col, int row) {
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
            return new ImageView(img);
        } else {
            return new ImageView();
        }
    }
}