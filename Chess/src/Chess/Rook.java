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
public class Rook extends Piece {

    Rook(Point position, boolean b, String name) {
        super(position,b,name);
    }

    @Override
    public void calculatePossibleMoves() {
        possible_moves = new ArrayList<Point>();
        
        //linha em frente:
        int i=1;
        Piece aux = Game.getAlivePiece((int)current_position.getX()+i,(int)current_position.getY());
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
