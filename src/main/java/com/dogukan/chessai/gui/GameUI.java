package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.Game;
import com.dogukan.chessai.chess.PieceColour;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameUI extends Application {
    @Override
    public void start(Stage stage) {
        int width = 600;
        int height = 670;

        Game game = new Game(PieceColour.WHITE, true);
        VBox root = new VBox();
        BoardUI boardUI = new BoardUI(game, width);
        NavigationUI navigationUI = new NavigationUI(game, boardUI, width, height - width);
        root.getChildren().addAll(boardUI, navigationUI);

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add("file:///Users/dogukan/Desktop/chessAI/src/main/resources/com/dogukan/chessai/stylesheet.css");
        stage.setTitle("Explore Chess Moves");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}