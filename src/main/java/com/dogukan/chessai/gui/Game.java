package com.dogukan.chessai.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");

        VBox vBox = new VBox();
        Color currentColour = Color.BLACK;
        for (int i = 0; i < 8; i++) {
            HBox hBox = new HBox();
            currentColour = toggleColour(currentColour);
            for (int j = 0; j < 8; j++) {
                SquareGUI square = new SquareGUI(currentColour, 50 ,50);
                currentColour = toggleColour(currentColour);
                hBox.getChildren().add(square);
            }
            vBox.getChildren().add(hBox);
        }

        Scene scene = new Scene(vBox, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    private Color toggleColour(Color color) {
        if (color == Color.GREEN) {
            color = Color.WHITE;
        } else {
            color = Color.GREEN;
        }
        return color;
    }

    public static void main(String[] args) {
        launch();
    }
}
