package TicTac.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class UtilityMethodsForBoard {

    public static Circle drawCircle() {
        Circle circle = new Circle(100, 100, 38);
        circle.setFill(javafx.scene.paint.Color.WHITE);
        return circle;
    }

    public static ArrayList<Line> drawLine() {

        ArrayList<Line> arr = new ArrayList<>();
        Line line1 = new Line(0, 0, 50, 50);
        Line line2 = new Line(0, 50, 50, 0);

        line1.setStrokeWidth(10);
        line2.setStrokeWidth(10);

        arr.add(line1);
        arr.add(line2);
        return arr;
    }

    public static void drawLine2(GridPane gridPane, String res) {
        for(int i = 0 ; i < 3 ; ++i)
            for(int j = 0 ; j < 3; ++j) {
                if (res.contains("row") &&
                        i == Character.getNumericValue(res.charAt(3)))
                    gridPane.add(createHorizontalLine() , i, j);
                else if(res.contains("col") &&
                        j == Character.getNumericValue(res.charAt(3)) - 1) {
                    Line line = createVerticalLine();
                    gridPane.add(line, i , j);
                    GridPane.setValignment(line, VPos.CENTER);
                    GridPane.setHalignment(line, HPos.CENTER);
                }
                else if(i == j && res.contains("diag1"))
                    gridPane.add(createFirstDiagonalLine(), i , j);
                else if(i + j == 2 && res.contains("diag0") )
                    gridPane.add(createSecondDiagonalLine(), j , i);
            }
    }

    public static Line createHorizontalLine() {
        Line line = new Line(0, 0, 170, 0);
        line.setStrokeWidth(10);
        line.setRotate(90);
        return line;
    }

    public static Line createVerticalLine() {
        Line line = new Line(0, 0, 170, 0);
        line.setStrokeWidth(10);
        return line;
    }

    public static Line createFirstDiagonalLine() {

        Line line = new Line(0, 0, 155, 155);
        line.setStrokeWidth(10);
        return line;
    }

    public static Line createSecondDiagonalLine() {

        Line line = new Line(0, 0, 155, 155);
        line.setRotate(90);
        line.setStrokeWidth(10);
        return line;
    }

    public static void stopButtons(GridPane gridPane) {
        for(int i = 0 ; i < 3 ; ++i)
            for(int j = 0 ; j < 3; ++j)
                for(Node node: gridPane.getChildren()) {
                    node.disableProperty().
                            bind(new SimpleBooleanProperty(true));
                }
    }

    public static void stopButtonAtCoordinate(GridPane gridPane, int i1,int j1) {
                for(Node node: gridPane.getChildren()) {
                    if(GridPane.getRowIndex(node) == i1 && GridPane.getColumnIndex(node) == j1)
                        node.disableProperty().
                                bind(new SimpleBooleanProperty(true));
                }
    }
}
