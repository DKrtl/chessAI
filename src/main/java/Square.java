public class Square {

    private int x;
    private int y;
    private Piece currentPiece;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addPiece(Piece piece) {
        if (currentPiece == null) {
            currentPiece = piece;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return currentPiece == null;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }
}
