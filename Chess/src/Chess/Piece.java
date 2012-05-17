/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Lobosque
 */
public abstract class Piece {
    protected ArrayList<Point> possible_moves;
    protected Point current_position; //a coordenada Y corresponde aa cooredenada Z no ambiente de jogo
    protected Point next_transition_position; //a coordenada Y corresponde aa cooredenada Z no ambiente de jogo
    protected float current_transition_positionX;
    protected float current_transition_positionY;
    protected float current_transition_positionZ;
    protected boolean is_white_colored; 
    protected float scale_factor=1.0f;
    protected float height_factor=1.0f;
    protected float rotate_factor=0.0f;
    protected String name;
    protected boolean inTransition = false;

    public Piece(Point position, boolean color, String name){
        this.current_position = position;
        this.is_white_colored=color;
        this.name = name;
    }
    
    public static Piece factory(String piece_type, Point position){
        if(piece_type.equals("light_rook")) return new Rook(position,true,"light_rook");
        else if(piece_type.equals("light_knight")) return new Knight(position,true,"light_knight");
        else if(piece_type.equals("light_bishop")) return new Bishop(position,true,"light_bishop");
        else if(piece_type.equals("light_queen")) return new Queen(position,true,"light_queen");
        else if(piece_type.equals("light_king")) return new King(position,true,"light_king");
        else if(piece_type.equals("light_pawn")) return new Pawn(position,true,"light_pawn");
        else if(piece_type.equals("dark_rook")) return new Rook(position,false,"dark_rook");
        else if(piece_type.equals("dark_knight")) return new Knight(position,false,"dark_knight");
        else if(piece_type.equals("dark_bishop")) return new Bishop(position,false,"dark_bishop");
        else if(piece_type.equals("dark_queen")) return new Queen(position,false,"dark_queen");
        else if(piece_type.equals("dark_king")) return new King(position,false,"dark_king");
        else// if(piece_type.equals("dark_pawn"))
            return new Pawn(position,false,"dark_pawn");
    }
    public abstract int calculatePossibleMoves();
    
    
     public void setCurrent_position(Point current_position) {
        this.current_position = current_position;
    }

    public Point getCurrent_position() {
        return current_position;
    }

    public float getHeight_factor() {
        return height_factor;
    }

    public boolean is_white_colored() {
        return is_white_colored;
    }

    public ArrayList<Point> getPossible_moves() {
        return possible_moves;
    }

    public float getScale_factor() {
        return scale_factor;
    }
    public String getName(){
        return this.name;
    }

    public float getRotate_factor() {
        return rotate_factor;
    }
    
    public boolean isInTransition(){
        return this.inTransition;
    }
    public float getCurrentTransitionPositionX(){
        return current_transition_positionX;
    }
    public float getCurrentTransitionPositionY(){
        return current_transition_positionY;
    }
    public float getCurrentTransitionPositionZ(){
        return current_transition_positionZ;
    }

    public boolean startTransitionTo(Point next_position){
//        if(possible_moves.contains(next_position)){
         if(true){
            inTransition=true;
            next_transition_position = next_position;
            current_transition_positionX = (float) (2*current_position.getX()+1);
            current_transition_positionY = 0.0f; //significa que esta no chao
            current_transition_positionZ = (float) (2*current_position.getY()+1);
            
            return true;
        }else return false;
        
    }
    public void updateTransition() {
        //testa se ja chegou a futura posicao para acabar com a transicao
//        if(Math.round(current_transition_positionX)==(2*next_transition_position.getX()+1) &&
        if(current_transition_positionX == (float)(2*next_transition_position.getX()+1) &&
            current_transition_positionY==0.0f &&
                current_transition_positionZ == (float)(2*next_transition_position.getY()+1)){
            inTransition=false;
            current_position = next_transition_position;
        }
        else{//caso ainda nao tenha chegado, atualiza os valores para transicao
            current_transition_positionX += 0.2f;//adicao gerando erros para futuras comparacoes? precisa de gambiarra
            //interpolação 3D de 3 pontos: origem, meio-caminho 'voando' e fim
        }
        
    }
    
}
