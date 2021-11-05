package com.dogukan.chessai.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Application {

    public void start(Stage stage) {
        GridPane root = new Board();
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/stylesheet.css");
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}