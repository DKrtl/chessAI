package com.dogukan.chessai;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {

    private Piece currentPiece;

    Square(Color color, int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setFill(color);
    }

    public void addPiece(Piece piece) {
        if (currentPiece == null) {
            currentPiece = piece;
        }
    }

    public boolean isEmpty() {
        return currentPiece == null;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }
}
