package com.dogukan.chessai.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareGUI extends Rectangle {

    SquareGUI(Color color, int x, int y) {
        setWidth(x);
        setHeight(y);
        setFill(color);
    }
}
