package com.dogukan.chessai;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

public class Game extends Application {
    int stageWidth = 700;
    int stageHeight = 700;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");

//        VBox vBox = new VBox();
//        Color currentColour = Color.BLACK;
//        for (int i = 0; i < 8; i++) {
//            HBox hBox = new HBox();
//            currentColour = toggleColour(currentColour);
//            for (int j = 0; j < 8; j++) {
//                Square square = new Square(currentColour, 50 ,50);
//                currentColour = toggleColour(currentColour);
//                hBox.getChildren().add(square);
//            }
//            vBox.getChildren().add(hBox);
//        }

        Board board = new Board();

        Scene scene = new Scene(board, stageWidth, stageHeight);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
