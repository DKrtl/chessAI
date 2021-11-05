package com.dogukan.chessai.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Board extends GridPane {
    private final int size = 8;

    Board() {
        setId("board");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                createSquare(row, col);
            }
        }
    }

    private void createSquare(int row, int col) {
        Rectangle square = new Rectangle();
        square.getStyleClass().add((row + col) % 2 == 0 ? "lightSquare" : "darkSquare");
        add(square, col, row);
        square.widthProperty().bind(heightProperty().divide(size));
        square.heightProperty().bind(heightProperty().divide(size));
    }
}