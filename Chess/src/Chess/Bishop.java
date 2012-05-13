/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.awt.Point;

/**
 *
 * @author Lobosque
 */
public class Bishop extends Piece {

    Bishop(Point position, boolean b, String name) {
        super(position,b,name);
    }

    @Override
    public int calculatePossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
