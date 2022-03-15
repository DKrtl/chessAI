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

        HBox direction = createDirections();

        getChildren().add(direction);
    }

    private HBox createDirections() {
        HBox directions = new HBox();

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

}
