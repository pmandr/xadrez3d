/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Game.Game;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Lobosque
 */
public class Pawn extends Piece {

    Pawn(Point position, boolean b, String name) {
        super(position,b,name);
//        calculatePossibleMoves();
    }

    @Override
    public void calculatePossibleMoves() {
//        Point position = Game.getBoardPosition(this);
        
        possible_moves = new ArrayList<Point>();
        
        if(is_white_colored){
            if(Game.getAlivePiece((int)current_position.getX()+1, (int)current_position.getY())==null){
                possible_moves.add(new Point((int)current_position.getX()+1, (int)current_position.getY()));
                if(Game.getAlivePiece((int)current_position.getX()+2, (int)current_position.getY())==null)
                    possible_moves.add(new Point((int)current_position.getX()+2, (int)current_position.getY()));
            }
            if(Game.isEnemyPiece(Game.getAlivePiece((int)current_position.getX()+1, (int)current_position.getY()+1))){
                possible_moves.add(new Point((int)current_position.getX()+1, (int)current_position.getY()+1));
            }
            if(Game.isEnemyPiece(Game.getAlivePiece((int)current_position.getX()+1, (int)current_position.getY()-1))){
                possible_moves.add(new Point((int)current_position.getX()+1, (int)current_position.getY()-1));
            }
        }else{
            if(Game.getAlivePiece((int)current_position.getX()-1, (int)current_position.getY())==null){
                possible_moves.add(new Point((int)current_position.getX()-1, (int)current_position.getY()));
                if(Game.getAlivePiece((int)current_position.getX()-2, (int)current_position.getY())==null)
                    possible_moves.add(new Point((int)current_position.getX()-2, (int)current_position.getY()));
            }
            if(Game.isEnemyPiece(Game.getAlivePiece((int)current_position.getX()-1, (int)current_position.getY()-1))){
                possible_moves.add(new Point((int)current_position.getX()-1, (int)current_position.getY()-1));
            }
            if(Game.isEnemyPiece(Game.getAlivePiece((int)current_position.getX()-1, (int)current_position.getY()+1))){
                possible_moves.add(new Point((int)current_position.getX()-1, (int)current_position.getY()+1));
            }
            
        }
            
        }
    
}
