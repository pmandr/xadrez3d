/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Game.Game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lobosque
 */
public class King extends Piece {

    public King(Point position, boolean b, String name) {
        super(position,b,name);
        this.rotate_factor=90;
    }

    @Override
    public void calculatePossibleMoves() {
        possible_moves = new ArrayList<Point>();
        
        //1a diagonal:
        Piece aux = Game.getAlivePiece((int)current_position.getX()+1,(int)current_position.getY()+1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+1,(int)current_position.getY()+1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //2a diagonal:
        aux = Game.getAlivePiece((int)current_position.getX()-1,(int)current_position.getY()-1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-1,(int)current_position.getY()-1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //3a diagonal:
        aux = Game.getAlivePiece((int)current_position.getX()+1,(int)current_position.getY()-1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+1,(int)current_position.getY()-1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //4a diagonal:
        aux = Game.getAlivePiece((int)current_position.getX()-1,(int)current_position.getY()+1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-1,(int)current_position.getY()+1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //frente:
        aux = Game.getAlivePiece((int)current_position.getX()+1,(int)current_position.getY());
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+1,(int)current_position.getY()));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //tras:
        aux = Game.getAlivePiece((int)current_position.getX()-1,(int)current_position.getY());
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX()-1,(int)current_position.getY()));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //lateral 1:
        aux = Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()-1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX(),(int)current_position.getY()-1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //lateral 2:
        aux = Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()+1);
        if(aux == null){
            possible_moves.add(new Point((int)current_position.getX(),(int)current_position.getY()+1));
        }else if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        
    }
}
