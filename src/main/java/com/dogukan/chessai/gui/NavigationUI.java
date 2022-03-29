package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.Game;
import com.dogukan.chessai.chess.PieceColour;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button complete;
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
        StackPane bestMove = bestMoveButton(width, height);
        StackPane complete = completeButton(width, height);

        getChildren().addAll(toggleCreativeMode, toggleColour, bestMove, complete);
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
                complete.setDisable(true);
                bestMove.setDisable(true);
                boardUI.setDisable(false);
            } else {
                complete.setDisable(false);
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

    private StackPane completeButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        complete = new Button("OK");
        pane.getChildren().add(complete);

        completeButtonClicked(complete);

        return pane;
    }

    private void completeButtonClicked(Button button) {
        button.setOnMouseClicked(click -> {
            button.setDisable(checkmateCheck());
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
            game.setCurrentState(game.bestMove(PieceColour.WHITE));
            boardUI.draw();
            checkmateCheck();
        });
    }

    private void gameOverPopUp() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getScene().getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private boolean checkmateCheck() {
        if(game.isGameOver()) {
            gameOverPopUp();
            return true;
        }
        return false;
    }
}
