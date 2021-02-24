package gameLogic.structure.GameUtilities;

public abstract class Piece {

    private boolean isKilled = false;
    private boolean isWhite = false;

    /**
     *
     * @param white Initialising the piece with white,
     *              because white always starts first(Constructor)
     */
    public Piece(boolean white) {
        isWhite = white;
    }

    /**
     * @param white If false we get a black piece,
     *              otherwise we get a white piece
     */
    public void setWhite(boolean white) {
        this.isWhite = white;
    }

    /**
     * @return Returns false if the piece is still on the table,
     * false otherwise
     */
    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled() {
        this.isKilled = true;
    }

    /**
     * @return Returns true if the piece is white,
     * false otherwise
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     *
     * @param board The chess table where the game happens
     * @param start The current position of the piece
     * @param end The desired position of the piece
     * @return Returns true if the piece can move at the end Spot given,
     * false otherwise
     */
    abstract public boolean canMove(Board board, Spot start, Spot end);


}
