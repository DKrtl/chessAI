import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    private boolean firstMove;

    Pawn(PieceColour colour) {
        super(colour);
        firstMove = true;
    }

    public void move(Square currentSquare, Square nextSquare) {
        Set<Square> legalMoves = legalMoves(currentSquare);

        if (legalMoves.contains(nextSquare)) {
            nextSquare.addPiece(this);
        }
    }

    public Set<Square> legalMoves(Square currentSquare) {
        int currentX = currentSquare.getX();
        int currentY = currentSquare.getY();
        Set<Square> legalMoves = new HashSet<>();

        if (firstMove) {
            Square nextMove1;
            Square nextMove2;
            if (super.getColour() == PieceColour.WHITE) {
                nextMove1 = getBoard().getSquare(currentX, currentY + 2);
                nextMove2 = getBoard().getSquare(currentX, currentY + 1);
            } else {
                nextMove1 = getBoard().getSquare(currentX, currentY - 2);
                nextMove2 = getBoard().getSquare(currentX, currentY - 1);
            }
            if (nextMove1.isEmpty() && nextMove2.isEmpty()) {
                legalMoves.add(nextMove1);
                legalMoves.add(nextMove1);
            }
        } else {
            Square nextMove;
            if (super.getColour() == PieceColour.WHITE) {
                nextMove = getBoard().getSquare(currentX, currentY + 1);
            } else {
                nextMove = getBoard().getSquare(currentX, currentY - 1);
            }
            if (nextMove.isEmpty()) {
                legalMoves.add(nextMove);
            }
        }

        Square takeLeft;
        Square takeRight;
        boolean pawnOnLeft;
        boolean pawnOnRight;

        if (super.getColour() == PieceColour.WHITE) {
            takeLeft = getBoard().getSquare(currentX - 1, currentY + 1);
            takeRight = getBoard().getSquare(currentX + 1, currentY + 1);
            pawnOnLeft = getBoard().getSquare(currentX - 1, currentY).getCurrentPiece() instanceof Pawn;
            pawnOnRight = getBoard().getSquare(currentX + 1, currentY).getCurrentPiece() instanceof Pawn;
        } else {
            takeLeft = getBoard().getSquare(currentX + 1, currentY - 1);
            takeRight = getBoard().getSquare(currentX - 1, currentY - 1);
            pawnOnLeft = getBoard().getSquare(currentX + 1, currentY).getCurrentPiece() instanceof Pawn;
            pawnOnRight = getBoard().getSquare(currentX - 1, currentY).getCurrentPiece() instanceof Pawn;
        }
        if (!takeLeft.isEmpty() || pawnOnLeft && !takeLeft.isEmpty()) {
            legalMoves.add(takeLeft);
        }
        if (!takeRight.isEmpty() || pawnOnRight && takeRight.isEmpty()) {
            legalMoves.add(takeRight);
        }
        return legalMoves;
    }
}