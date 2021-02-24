package gameLogic.structure.GameUtilities;

public class Spot {

    Piece piece;
    int x,y;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
