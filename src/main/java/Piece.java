public abstract class Piece {

    private static Board board = new Board();
    private PieceColour colour;

    Piece(PieceColour colour) {
        this.colour = colour;
    }

    public abstract void move(Square currentSquare, Square nextSquare);

    public Board getBoard() {
        return board;
    }

    public PieceColour getColour() {
        return colour;
    }
}
