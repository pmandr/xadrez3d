/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lobosque
 */
public class Knight extends Piece {

    protected static List<Square> possibleMoves(Square pos) {
        int x = pos.getX();
        int y = pos.getY();

        int i, j;
        List<Square> moves = new ArrayList<Square>();

        for (i = -2; i <= 2; i++) {
            if (i == 0) {
                continue;
            }
            for (j = -2; j <= 2; j++) {
                if (Math.abs(i) != Math.abs(j)) {
                    if (j == 0) {
                        continue;
                    }
                    try {
                        moves.add(new Square(x + i, y + j));
                    } catch (InvalidSquareException ex) {
                    }
                }
            }
        }

        return moves;
    }

    Knight(Point position, boolean b, String name) {
        super(position,b,name);
        if(b) this.rotate_factor = -90;
        else this.rotate_factor = 90;
    }

    @Override
    public void calculatePossibleMoves() {
    }
}
