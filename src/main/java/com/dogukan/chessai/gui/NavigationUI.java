package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.Game;
import com.dogukan.chessai.chess.PieceColour;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

public class NavigationUI extends HBox {

    private Game game;
    private BoardUI boardUI;
    private Button flip;
    private Button bestMove;
    private PieceColour selectedColour;

    public NavigationUI(Game game, BoardUI boardUI, int width, int height) {
        this.game = game;
        this.boardUI = boardUI;
        this.selectedColour = PieceColour.WHITE;
        setMinWidth(width);
        setMinHeight(height);
        setAlignment(Pos.CENTER);
        setSpacing(5);

        StackPane toggleCreativeMode = creativeModeToggle(width, height);
        StackPane toggleColour = colourToggle(width, height);
        StackPane flipPane = flipButton(width, height);
        StackPane bestMove = bestMoveButton(width, height);

        getChildren().addAll(toggleCreativeMode, toggleColour, flipPane, bestMove);
    }

    private StackPane creativeModeToggle(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.3);
        pane.setMinHeight(height);

        ToggleSwitch creativeModeToggle = new ToggleSwitch("Creative Mode");
        pane.getChildren().add(creativeModeToggle);

        boolean creativeMode = game.getCreativeMode();

        creativeModeToggle.setSelected(creativeMode);

        if(creativeMode) {
            boardUI.setDisable(false);
        } else {
            boardUI.setDisable(true);
        }

        creativeModeToggleButton(creativeModeToggle);

        return pane;
    }

    private void creativeModeToggleButton(ToggleSwitch toggleSwitch) {
        toggleSwitch.setOnMouseClicked(pressed -> {
            game.setCreativeMode(toggleSwitch.isSelected());
            if (game.getCreativeMode()) {
                flip.setDisable(true);
                bestMove.setDisable(true);
                boardUI.setDisable(false);
            } else {
                flip.setDisable(false);
                bestMove.setDisable(false);
                boardUI.setDisable(true);
                checkmateCheck();
            }
        });
    }

    private StackPane colourToggle(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.3);
        pane.setMinHeight(height);

        ToggleSwitch colour = new ToggleSwitch("Player turn");
        pane.getChildren().add(colour);

        colour.setSelected(selectedColour == PieceColour.BLACK);
        if(colour.isSelected()) {
            colour.getStyleClass().add("colourToggle");
        } else {
            colour.getStyleClass().remove("colourToggle");
        }

        colourToggleButton(colour);

        return pane;
    }

    private void colourToggleButton(ToggleSwitch toggleSwitch) {
        toggleSwitch.setOnMouseClicked(pressed -> {
            if(toggleSwitch.isSelected()) {
                selectedColour = PieceColour.BLACK;
                toggleSwitch.getStyleClass().add("colourToggle");
            } else {
                selectedColour = PieceColour.WHITE;
                toggleSwitch.getStyleClass().remove("colourToggle");
            }
        });
    }

    private StackPane flipButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        flip = new Button("Flip");
        pane.getChildren().add(flip);

        flipButtonClicked(flip);

        return pane;
    }

    private void flipButtonClicked(Button button) {
        button.setOnMouseClicked(click -> {
            button.setDisable(checkmateCheck());

            boardUI.togglePlayerColour();
            boardUI.draw();
        });
    }

    private StackPane bestMoveButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        bestMove = new Button("Best Move");
        pane.getChildren().add(bestMove);

        bestMoveButtonClicked(bestMove);

        return pane;
    }

    private void bestMoveButtonClicked(Button button) {
        button.setOnMouseClicked(click -> {
            game.setCurrentState(game.bestMove(selectedColour));
            boardUI.draw();
            checkmateCheck();
        });
    }

    private void gameOverPopUp() {
        Alert checkmateAlert = new Alert(Alert.AlertType.NONE, "Checkmate", ButtonType.OK);

        checkmateAlert.show();
    }

    private boolean checkmateCheck() {
        if(game.isGameOver()) {
            gameOverPopUp();
            return true;
        }
        return false;
    }
}
