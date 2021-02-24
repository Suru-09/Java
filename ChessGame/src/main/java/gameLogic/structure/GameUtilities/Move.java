package gameLogic.structure.GameUtilities;
import gameLogic.structure.Game.Player;

public class Move {

    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    /**
     * Constructor made for initializing a move
     * @param player The current player(player1 or player2)
     * @param start The starting spot of the move
     * @param end The ending spot of the move
     */
    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
    }


}
