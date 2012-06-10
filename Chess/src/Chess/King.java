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
public class King extends Piece {

    protected static List<Square> possibleMoves(Square pos) {
        int x = pos.getX();
        int y = pos.getY();

        int i, j;
        List<Square> moves = new ArrayList<Square>();

        for (i = -1; i <= 1; i++) {
            for (j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                try {
                    moves.add(new Square(x + i, y + j));
                } catch (InvalidSquareException ex) {
                }
            }
        }

        return moves;
    }

    public King(Point position, boolean b, String name) {
        super(position,b,name);
        this.rotate_factor=90;
    }

    @Override
    public void calculatePossibleMoves() {
    }
}
