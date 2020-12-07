package TicTac.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class TicTacController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox firstLayout = InitialScreen.createInitialScreen();
        firstLayout.setId("VBoxBackground");

        Scene scene1 = new Scene(firstLayout);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("TicTacGame");
        scene1.getStylesheets().add("initialScreen.css");

        handleInitialScreen(primaryStage, firstLayout);

        try{
            File file = new File("src/Images/logo.jpg");
            Image image = new Image(file.toURI().toString());
            primaryStage.getIcons().add(image);
        }
        catch(Exception e) {
            System.out.println("Crapa Icon-ul");
        }

        primaryStage.setResizable(false);
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage The stage for the initial screen
     * @param firstLayout   The layout given by the InitialScreen class
     */
    public void handleInitialScreen(Stage primaryStage, VBox firstLayout) {
        AtomicBoolean isSinglePlayer = new AtomicBoolean(false);

        for(Node node: firstLayout.getChildren()){
            if(!(node instanceof Label)) {
                Button b = (Button)node;
                b.setOnAction(e -> {
                    isSinglePlayer.set(b.getText().equals("Single player"));

                    VBox layout = TicTacBoard.buildScene(primaryStage, isSinglePlayer);
                    Scene scene2 = new Scene(layout);
                    primaryStage.setScene(scene2);

                    scene2.getStylesheets().add("background.css");
                    primaryStage.setWidth(600);
                    primaryStage.setHeight(650);
                });
            }
        }
    }
}