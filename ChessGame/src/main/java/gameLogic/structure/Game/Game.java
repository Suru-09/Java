package gameLogic.structure.Game;

import gameLogic.structure.GameUtilities.Board;
import gameLogic.structure.GameUtilities.Move;

import java.util.List;

public class Game {

    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;

    private void initialize(Player p1, Player p2) {

        players[0] = p1;
        players[1] = p2;


    }

}
