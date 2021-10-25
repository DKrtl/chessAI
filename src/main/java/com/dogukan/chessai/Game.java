package com.dogukan.chessai;

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
            for (int j = 0; j < 8; j++) {
                Rectangle square = new Rectangle(10, 10);
                if (currentColour == Color.BLACK) {
                    currentColour = Color.GREEN;
                } else {
                    currentColour = Color.BLACK;
                }
                square.setFill(currentColour);
                hBox.getChildren().add(square);
            }
            vBox.getChildren().add(hBox);
        }

        Scene scene = new Scene(vBox, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
