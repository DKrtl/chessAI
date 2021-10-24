package com.dogukan.chessai;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
