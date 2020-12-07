package TicTac.gui;

import TicTac.oop.TicTacGame;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TicTacBoard {

    /**
     * @author Dragos Surugiu
     * @return This function returns the board for the tic-tac-toe game
     *          which is a VBox in our case
     */

    public static VBox buildScene(Stage primaryStage, AtomicBoolean isSinglePlayer) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.setPadding(new Insets(15));

        TicTacGame game = new TicTacGame();

        Button restartButton = new Button("Restart");

        build(isSinglePlayer, gridPane, game);

        restartButton.setOnAction(e -> {

            gridPane.getChildren().clear();
            game.setTable();
            game.setMoveCounter();

            build(isSinglePlayer, gridPane, game);
        });


        HBox hBox = new HBox(10);

        Button exitButton = new Button("Exit");

        exitButton.setOnAction(e -> primaryStage.close());


        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(restartButton, exitButton);

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(gridPane, hBox);

        VBox.setVgrow(gridPane, Priority.NEVER);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(25));


        return vBox;
    }

    private static void build(AtomicBoolean isSinglePlayer, GridPane gridPane, TicTacGame game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                int finalI = i;
                int finalJ = j;

                button.setOnAction(a -> {
                    //Disabling a button once that it had been pushed
                    SimpleBooleanProperty isDisabled = new SimpleBooleanProperty(true);
                   /*
                    If it is player 1 turn we draw a X and otherwise
                    an O , this will be decided by the play() method
                    in TicTacGame.java , therefore by the static counter
                    name "moveCounter"
                    */
                    // System.out.println(finalI + "  " + finalJ + " ");

                    boolean test = game.whoPlays(finalJ, finalI, gridPane).equals("X") && !isSinglePlayer.get();

                    if(test) {
                        ArrayList<Line> arr = UtilityMethodsForBoard.drawLine();

                        gridPane.add(arr.get(0), finalI, finalJ);
                        gridPane.add(arr.get(1), finalI, finalJ);

                        GridPane.setHalignment(arr.get(0), HPos.CENTER);
                        GridPane.setHalignment(arr.get(1), HPos.CENTER);
                    }
                    else {
                        Circle circle = UtilityMethodsForBoard.drawCircle();
                        circle.setBlendMode(BlendMode.OVERLAY);

                        gridPane.add(circle, finalI, finalJ);
                        GridPane.setHalignment(circle, HPos.CENTER);

                        if(isSinglePlayer.get() && !TicTacGame.isOver()){
                            ArrayList<Line> arr = UtilityMethodsForBoard.drawLine();

                            TicTacGame.Move move = game.findBestMove();

                            if(move.col != -1 && move.row != -1) {
                                game.whoPlays(move.row, move.col, gridPane);

                                gridPane.add(arr.get(0), move.col, move.row);
                                gridPane.add(arr.get(1), move.col, move.row);

                                UtilityMethodsForBoard.stopButtonAtCoordinate(gridPane, move.row, move.col);

                                GridPane.setHalignment(arr.get(0), HPos.CENTER);
                                GridPane.setHalignment(arr.get(1), HPos.CENTER);
                            }
                        }
                    }

                    button.disableProperty().bind(isDisabled);

                });

                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                gridPane.add(button, i, j);
                button.setPrefHeight(155);
                button.setPrefWidth(155);
                GridPane.setHgrow(button, Priority.SOMETIMES);
                GridPane.setVgrow(button, Priority.SOMETIMES);
            }
        }
    }
}
