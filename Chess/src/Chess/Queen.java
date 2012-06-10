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
public class Queen extends Piece {

    Queen(Point position, boolean b, String name) {
        super(position,b,name);
    }

    @Override
    public void calculatePossibleMoves() {
    }
    
}
