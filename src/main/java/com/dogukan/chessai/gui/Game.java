package com.dogukan.chessai.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
    GridPane root = new GridPane();
    final int size = 8;

    public void start(Stage primaryStage) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle square = new Rectangle();
                Color color;
                if ((row + col) % 2 == 0) {
                    color = Color.GREEN;
                } else {
                    color = Color.WHITE;
                }
                square.setFill(color);
                root.add(square, col, row);
                square.widthProperty().bind(root.heightProperty().divide(size));
                square.heightProperty().bind(root.heightProperty().divide(size));
                root.setAlignment(Pos.CENTER);
            }
        }
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
