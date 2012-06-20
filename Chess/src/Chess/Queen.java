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
public class Queen extends Piece {

    Queen(Point position, boolean b, String name) {
        super(position,b,name);
        this.height_factor = 1.2f;
        this.scale_factor = 1.2f;
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
        
        //linha em frente:
        i=1;
        aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY());
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()));
            i++;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY());
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //linha atras:
        i=-1;
        aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY());
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX()+i,(int)current_position.getY()));
            i--;
            aux=Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY());
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //linha lateral direita:
        i=1;
        aux = Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()+i);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX(),(int)current_position.getY()+i));
            i++;
            aux=Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()+i);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
        //linha lateral esquerda:
        i=-1;
        aux = Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()+i);
        while(aux == null){
            possible_moves.add(new Point((int)current_position.getX(),(int)current_position.getY()+i));
            i--;
            aux=Game.getAlivePiece((int)current_position.getX(),(int)current_position.getY()+i);
        }
        if(Game.isEnemyPiece(aux)) possible_moves.add(aux.getCurrent_position());
        
    }
    
}
