package com.dogukan.chessai.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class NavigationUI extends HBox {

    public NavigationUI(int width, int height) {
        setMinWidth(width);
        setMinHeight(height);
        setAlignment(Pos.CENTER);
        setSpacing(5);

        HBox direction = createDirections(width, height);
        StackPane bestMove = bestMoveButton(width, height);
        StackPane complete = completeButton(width, height);

        getChildren().addAll(direction, bestMove, complete);
    }

    private HBox createDirections(int width, int height) {
        HBox directions = new HBox();
        directions.setAlignment(Pos.CENTER);
        directions.setSpacing(5);
        directions.setMinWidth(width * 0.6);
        directions.setMinHeight(height);

        StackPane leftPane = new StackPane();
        leftPane.setAlignment(Pos.CENTER);
        Button leftButton = new Button();
        FontIcon iconL = new FontIcon();
        iconL.setId("leftIcon");
        leftButton.setGraphic(iconL);
        leftPane.getChildren().add(leftButton);

        StackPane rightPane = new StackPane();
        rightPane.setAlignment(Pos.CENTER);
        Button rightButton = new Button();
        FontIcon iconR = new FontIcon();
        iconR.setId("rightIcon");
        rightButton.setGraphic(iconR);
        rightPane.getChildren().add(rightButton);

        directions.getChildren().addAll(leftPane, rightPane);

        return directions;
    }

    private StackPane completeButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        Button complete = new Button("OK");
        pane.getChildren().add(complete);

        return pane;
    }

    private StackPane bestMoveButton(int width, int height) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setMinWidth(width * 0.2);
        pane.setMinHeight(height);

        Button bestMove = new Button("Best Move");
        pane.getChildren().add(bestMove);

        return pane;
    }
}
