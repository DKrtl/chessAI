package com.dogukan.chessai.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
    private final int size = 8;
    private NumberBinding minSide;
    private final Pane root;

    Board(Pane root) {
        this.root = root;
        minSide = Bindings
                .min(root.heightProperty(), root.widthProperty())
                .divide(8);

        setId("board");
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                createSquare(col, row);
            }
        }
    }

    private void createSquare(int col, int row) {
        Rectangle square = new Rectangle();
        square.getStyleClass().add((row + col) % 2 == 0 ? "lightSquare" : "darkSquare");

        square.xProperty().bind(minSide.multiply(col));
        square.yProperty().bind(minSide.multiply(row));
        square.heightProperty().bind(minSide.subtract(2));
        square.widthProperty().bind(square.heightProperty());
        root.getChildren().add(square);
    }
}