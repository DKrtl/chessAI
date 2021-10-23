import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    private boolean firstMove;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
    }

    public void move(Square currentSquare, Square nextSquare) {
//        int currentX = currentSquare.getX();
//        int currentY = currentSquare.getY();
//        int nextX = currentSquare.getX();
//        int nextY = currentSquare.getY();
//
//        if (getBoard().getSquare(nextX, nextY).isEmpty()) {
//            if ((Math.abs(currentY - nextY) == 1) || (firstMove && Math.abs(currentY - nextY) == 2)) {
//                nextSquare.addPiece(this);
//            }
//        } else {
//
//        }
    }

    private Set<Square> legalMoves(Square currentSquare) {
        int currentX = currentSquare.getX();
        int currentY = currentSquare.getY();
        Set<Square> legalMoves = new HashSet<>();

        if (firstMove) {
            Square nextMove;
            if(super.getColour() == PieceColour.WHITE) {
                nextMove = getBoard().getSquare(currentX, currentY + 2);
            } else {
                nextMove = getBoard().getSquare(currentX, currentY - 2);
            }
            if (nextMove.isEmpty()) {
                legalMoves.add(nextMove);
            }
        }
    }
}