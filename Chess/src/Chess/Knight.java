/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Game.Game;
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

    Knight(Point position, boolean b, String name) {
        super(position,b,name);
        if(b) this.rotate_factor = -90;
        else this.rotate_factor = 90;
    }

    @Override
    public void calculatePossibleMoves() {
        possible_moves = new ArrayList<Point>();
        
        Piece aux = Game.getAlivePiece((int)current_position.getX()+2,(int)current_position.getY()+1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+2,(int)current_position.getY()+1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()+1,(int)current_position.getY()+2);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+1,(int)current_position.getY()+2));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()-1,(int)current_position.getY()+2);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-1,(int)current_position.getY()+2));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()-2,(int)current_position.getY()+1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-2,(int)current_position.getY()+1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()-2,(int)current_position.getY()-1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-2,(int)current_position.getY()-1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()-1,(int)current_position.getY()-2);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-1,(int)current_position.getY()-2));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()+1,(int)current_position.getY()-2);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+1,(int)current_position.getY()-2));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        aux = Game.getAlivePiece((int)current_position.getX()+2,(int)current_position.getY()-1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+2,(int)current_position.getY()-1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
    }
}
