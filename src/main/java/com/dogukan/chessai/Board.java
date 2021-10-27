package com.dogukan.chessai;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Board extends GridPane {

    private Square[][] board;

    Board() {
        board = new Square[8][8];

        drawBoard();

        setProperties();
    }

    private void drawBoard() {
        Color currentColour = Color.BLACK;
        for(int i = 0; i < 8; i++) {
            currentColour = toggleColour(currentColour);
            for(int j = 0; j < 8; j++) {
                board[i][j] = new Square(currentColour, i, j, 50,50);
                add(board[i][j], i, j);
                currentColour = toggleColour(currentColour);
                if (j == 1) {
                    board[i][j].setCurrentPiece(new Pawn(PieceColour.WHITE));
                } else if (j == 6) {
                    board[i][j].setCurrentPiece(new Pawn(PieceColour.BLACK));
                }
            }
        }
    }

    private void setProperties() {
        setAlignment(Pos.CENTER);

        setStyle("-fx-border-width:2px;-fx-border-color:red");

//        setBorder(new Border(new BorderStroke(Color.BLACK,
//                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    private Color toggleColour(Color color) {
        if (color == Color.GREEN) {
            color = Color.WHITE;
        } else {
            color = Color.GREEN;
        }
        return color;
    }

    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    public Square[][] getBoard() {
        return board;
    }
}