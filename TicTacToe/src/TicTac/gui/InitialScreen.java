package TicTac.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InitialScreen {

    /**
     * @author Dragos Surugiu
     * @return It returns the VBox containing the two buttons on the
     * initial screen
     */
    public static VBox createInitialScreen() {


        //Title
        Label label = new Label("Tic Tac Toe");
        label.getStyleClass().add("title");

        //First Button(Single player button)
        Button singlePlayerButton = new Button("Single player");
        singlePlayerButton.getStyleClass().add("single");

        //Second Button(Player vs player button)
        Button multiPlayerButton = new Button("Player VS Player");
        multiPlayerButton.getStyleClass().add("multi");

        VBox vBox = new VBox(50);
        vBox.getChildren().addAll(label, singlePlayerButton, multiPlayerButton);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }
}
