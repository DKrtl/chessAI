package com.dogukan.chessai.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}