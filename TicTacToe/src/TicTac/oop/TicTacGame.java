package TicTac.oop;

import TicTac.gui.UtilityMethodsForBoard;
import javafx.scene.layout.GridPane;
import java.util.Arrays;

import static TicTac.gui.UtilityMethodsForBoard.stopButtons;

public class TicTacGame {

    private int[][] table;
    protected static int moveCounter = 0;

    public TicTacGame() {
        table = new int[3][3];
        setTable();
    }

    public int[][] getTable() {
        return table;
    }
    
    public void setTable() {
        Arrays.stream(table).forEach(e -> Arrays.fill(e, -1));
    }

    /**
     * @param value The value you would like to insert
     * @param i     The row where you would like to insert the value
     * @param j     The column where you would like to insert the value
     */

    public void add(int value, int i, int j) {
        table[i][j] = value;
    }

    /**
     * @return Returns true if table is full or false if the table
     * isn't fully occupied yet
     */

    public String whoPlays(int i, int j, GridPane gridPane) {

        if(table[i][j] == -1)
            add(moveCounter % 2, i, j);

        multiPlayer(gridPane);

        if (moveCounter % 2 == 0 ) {
            ++moveCounter;
            return "O";
        } else {
            ++moveCounter;
            return "X";
        }
    }
    public void multiPlayer(GridPane gridPane) {

        if(evaluate() == 10)
            System.out.println("Player playing X won!");
        else if(evaluate() == -10)
            System.out.println("Player playing O won!");
        else if(isOver() && evaluate() == 0 )
            System.out.println("It's a draw");
        else return;

        UtilityMethodsForBoard.drawLine2(gridPane, winner()[1]);
        stopButtons(gridPane);
    }



    public boolean isOver() {

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                if (table[i][j] == -1)
                    return false;
            }
        return true;
    }

    public String[] winner() {
        int[] score = new int[2 * 3 + 2];
        Arrays.fill(score, 0);

        for(int i = 0; i < 3 ; ++i)
            for(int j = 0 ; j < 3; ++j){
                if(table[j][i] == 0) {
                    ++score[j + 3];
                    ++score[i];
                }
                else if(table[j][i] == 1) {
                    --score[j + 3];
                    --score[i];
                }


                if(i == 1 && j == 1 ){
                    if(table[i][j] == 0) {
                        ++score[6];
                        ++score[7];
                    } else if(table[i][j] == 1){
                        --score[6];
                        --score[7];
                    }
                }

                if(i == j && i != 1)
                    if(table[i][j] == 0)
                        ++score[6];
                    else if(table[i][j] == 1)
                        --score[6];

                if( (i == 0 && j == 2) || (i == 2 && j == 0))
                    if(table[i][j] == 0)
                        ++score[7];
                    else if(table[i][j] == 1)
                        --score[7];
            }

        for(int it = 0 ; it < 8 ; it++) {
            if(score[it] == 3 || score[it] == -3)
                if(score[it] == 3) {
                    if(it < 3)
                        return new String[]{"O", "row" + it};
                    else if(it < 6) {
                        int copy = it - 2;
                        return new String[]{"O", "col" + copy };
                    }
                    else {
                        int copy = 7 - it;
                        return new String[]{"O" , "diag" + copy };
                    }
                }else {
                    if(it < 3)
                        return new String[]{"X", "row" + it };
                    else if(it < 6) {
                        int copy = it - 2;
                        return new String[]{"X", "col" + copy};
                    }
                    else {
                        int copy = 7 - it;
                        return new String[]{"O" , "diag" + copy };
                    }
                }
        }
        return new String[]{"Draw", "Aia e"};
    }

    static public class Move {
        public int row,col;
    };

    static int cnt = 0;

    private int evaluate()
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (table[row][0] == table[row][1] &&
                    table[row][1] == table[row][2])
            {
                if (table[row][0] == 1)
                    return +10;
                else if (table[row][0] == 0)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (table[0][col] == table[1][col] &&
                    table[1][col] == table[2][col])
            {
                if (table[0][col] == 1)
                    return +10;

                else if (table[0][col] == 0)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (table[0][0] == table[1][1] && table[1][1] == table[2][2])
        {
            if (table[0][0] == 1)
                return +10;
            else if (table[0][0] == 0)
                return -10;
        }

        if (table[0][2] == table[1][1] && table[1][1] == table[2][0])
        {
            if (table[0][2] == 1)
                return +10;
            else if (table[0][2] == 0)
                return -10;
        }

        return 0;
    }

    private int miniMax(int depth, boolean isMax){

        int sth = evaluate();

        if(sth == 10)
            return 10;

        if(sth == -10)
            return -10;

        if(isOver())
            return 0;

        int best;
        if(isMax) {
            best = -100000;

            for(int i = 0 ; i < 3; ++i) {
                for(int j = 0 ; j < 3 ; ++j) {
                    if(table[i][j] == -1){
                        table[i][j] = 1;
                        best = Math.max(best, miniMax(depth + 1, false));
                        table[i][j] = -1;
                    }
                }
            }
            return best;
        }
        else {
            best = 100000;

            for(int i = 0 ; i < 3; ++i) {
                for(int j = 0 ; j < 3 ; ++j) {
                    if(table[i][j] == -1){
                        table[i][j] = 0;

                        best = Math.min(best, miniMax(depth + 1, true));
                        table[i][j] = -1;
                    }
                }
            }

            return best;
        }
    }

    public Move findBestMove() {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for(int i = 0 ; i < 3; ++i)
            for(int j = 0 ; j < 3; ++j) {
                if(table[i][j] == -1) {
                    table[i][j] = 1;

                    int moveVal = miniMax(0, false);
                    System.out.println("mOVEval: " + moveVal);
                    System.out.println("The x coordinate " + i + " and the y: " + j);
                    System.out.println("CNT: " + cnt);

                    table[i][j] = -1;

                    if(moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }

        System.out.println(bestMove.row + "  " + bestMove.col);
        return bestMove;
    }

    public void setMoveCounter() {
        moveCounter = 0;
    }
}

