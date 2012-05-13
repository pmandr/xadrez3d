/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lobosque
 */
public class Rook extends Piece {

    protected static List<Square> possibleMoves(Square pos) {
        int x = pos.getX();
        int y = pos.getY();
        int i, j;
        List<Square> moves = new ArrayList<Square>();

        for (i = 0; i <= 7; i++) {
            if (i == x) {
                continue;
            }
            try {
                moves.add(new Square(i, y));
            } catch (InvalidSquareException ex) {
            }
        }
        for (i = 0; i <= 7; i++) {
            if (i == y) {
                continue;
            }
            try {
                moves.add(new Square(x,i));
            } catch (InvalidSquareException ex) {
            }
        }
        return moves;
    }

    Rook(Point position, boolean b, String name) {
        super(position,b,name);
    }

    @Override
    public int calculatePossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
}
