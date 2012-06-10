/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.awt.Point;
import Game.Game;
import java.util.ArrayList;

/**
 *
 * @author Lobosque
 */
public class Bishop extends Piece {

    Bishop(Point position, boolean b, String name) {
        super(position,b,name);
    }
    
    @Override
    public void calculatePossibleMoves() {
        possible_moves = new ArrayList<Point>();
        
        //1a diagonal:
        int i=1;
        Piece aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+i);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()+i));
            i++;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+i);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //2a diagonal:
        i=-1;
        aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+i);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()+i));
            i--;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+i);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //3a diagonal:
        i=-1; int j=1;
        aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+j);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()+j));
            i--;
            j++;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+j);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //4a diagonal:
        i=1; j=-1;
        aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+j);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()+j));
            j--;
            i++;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY()+j);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
    }
    
}
