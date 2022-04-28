package com.dogukan.chessai.gui;

import com.dogukan.chessai.chess.Game;
import com.dogukan.chessai.chess.Information;
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

import java.util.List;

public class NavigationUI extends HBox {

    private Game game;
    private BoardUI boardUI;
    private Button flip;
    private Button explore;
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
        StackPane explorePane = exploreButton(width, height);

        getChildren().addAll(toggleCreativeMode, toggleColour, flipPane, explorePane);
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
                explore.setDisable(true);
                boardUI.setDisable(false);
            } else {
                flip.setDisable(false);
                explore.setDisable(false);
                boardUI.setDisable(true);
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
            boardUI.togglePlayerColour();
            boardUI.draw();
        });
    }

    private StackPane exploreButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        explore = new Button("Explore");
        pane.getChildren().add(explore);

        exploreButtonClicked(explore);

        return pane;
    }

    private void exploreButtonClicked(Button button) {
        button.setOnMouseClicked(click -> {
            informationPopUp(game.createInformation(selectedColour));
        });
    }

    private void informationPopUp(List<Information> information) {
//        final Stage popup = new InformationUI(information);
//        popup.initOwner(getScene().getWindow());
//        popup.show();
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 5; i++) {
            if(i < information.size()) {
                Information info = information.get(i);
                output.append("Move ").append(info.getPiece()).append(" from position ")
                        .append(info.getFrom()).append(" to position ").append(info.getTo()).append("\n")
                        .append("for an initial evaluation: ").append(info.getInitialEval())
                        .append(" to the final evaluation: ").append(info.getFinalEval())
                        .append("\n\n");
            }
        }
        Alert checkmateAlert = new Alert(Alert.AlertType.NONE, output.toString(), ButtonType.OK);

        checkmateAlert.show();
    }
}
