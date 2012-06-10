/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;
import Game.Game;
import Jogl.Camera;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Lobosque
 */
public abstract class Piece {
    protected ArrayList<Point> possible_moves = new ArrayList<Point>();
    protected Point current_position; //a coordenada Y corresponde aa cooredenada Z no ambiente de jogo
    protected Point next_transition_position; //a coordenada Y corresponde aa cooredenada Z no ambiente de jogo
    
    //pontos da tragetoria
    protected float beggining_transition_positionX;
    protected float beggining_transition_positionY;
    protected float beggining_transition_positionZ;
    protected float apex_transition_positionX;
    protected float apex_transition_positionY = 2.0f; //altura que a peca alcanca na transicao de posicoes
    protected float apex_transition_positionZ;
    protected float next_transition_positionX;
    protected float next_transition_positionY;
    protected float next_transition_positionZ;
    protected float current_transition_positionX;
    protected float current_transition_positionY;
    protected float current_transition_positionZ;
    protected float animation_timer =0;
    
    protected boolean is_white_colored; 
    protected float scale_factor=1.0f;
    protected float height_factor=1.0f;
    protected float rotate_factor=0.0f;
    protected String name;
    protected boolean inTransition = false;
    private boolean isDying = false;

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
        else if(piece_type.equals("dark_pawn")) return new Pawn(position,false,"dark_pawn");
        else return new Pawn(position,false,"out of borders"); //for calculating the possible moves
    }
    public abstract void calculatePossibleMoves();
    
    public boolean startTransitionTo(Point next_position) throws Exception{
//        if(possible_moves.contains(next_position)){
            inTransition=true;
//            Game.setTransition(this.getCurrent_position(), next_position);
            //gera o ponto de origem da animacao
            beggining_transition_positionX = (float) (2*current_position.getX()+1);
            beggining_transition_positionY = 0.0f; //significa que esta no chao
            beggining_transition_positionZ = (float) (2*current_position.getY()+1);
            
            //gera o ponto de destino da animacao
            next_transition_positionX = (float) (2*next_position.getX()+1);
            next_transition_positionY = 0.0f; //significa que esta no chao
            next_transition_positionZ = (float) (2*next_position.getY()+1);
            
            //gera o ponto medio (apice) da parabola descrita na animacao
            apex_transition_positionX = (beggining_transition_positionX+next_transition_positionX)/2; 
            apex_transition_positionZ = (beggining_transition_positionZ+next_transition_positionZ)/2; 
            
            current_transition_positionX = beggining_transition_positionX;
            current_transition_positionY = beggining_transition_positionY;
            current_transition_positionZ = beggining_transition_positionZ;
            next_transition_position = next_position;
            animation_timer =0.0f;
            return true;
//        }else{
//            Exception InvalidSquareException = new InvalidSquareException();
//             throw InvalidSquareException;
//             return false;
//         } 
        
    }
    public void updateTransition() {
        //testa se ja chegou a futura posicao para acabar com a transicao
//        if(current_transition_positionX == next_transition_positionX &&
//            current_transition_positionY==0.0f &&
//                current_transition_positionZ == next_transition_positionZ){
        if(animation_timer==1.0f){  
            inTransition=false;
            isDying=false;
            current_position = next_transition_position;
            Game.endTransition();
            
            }
        else{//caso ainda nao tenha chegado, atualiza os valores para transicao
            if(isDying) height_factor -= animation_timer/50;
            //esta adicao gera erros para futuras comparacoes. os 2 passos abaixo eh uma gambiarra para garantir uma precisao de uma casa decimal
            animation_timer+=0.01f;
            animation_timer = Math.round(100*animation_timer);
            animation_timer = animation_timer/100;
            
            current_transition_positionX = calculateXinParabola(animation_timer);
            current_transition_positionY = calculateYinParabola(animation_timer);
            current_transition_positionZ = calculateZinParabola(animation_timer);
//            current_transition_positionX += 0.2f;
//            current_transition_positionX = Math.round(10*current_transition_positionX);
//            current_transition_positionX= current_transition_positionX/10;
            //interpolação 3D de 3 pontos: origem, meio-caminho 'voando' e fim
        }
        
    }
    
    
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

    //x(t) = x1 - t*(3*x1-4*x2+x3) + 2*t^2*(x1-2*x2+x3), t=0..1
    private float calculateXinParabola(float animation_timer) {
        return (beggining_transition_positionX
                -animation_timer*(3*beggining_transition_positionX -4*apex_transition_positionX +next_transition_positionX)
                +2*(float)Math.pow(animation_timer, 2)*
                    (beggining_transition_positionX-2*apex_transition_positionX+next_transition_positionX));
    }
    //y(t) = y1 - t*(3*y1-4*y2+y3) + 2*t^2*(y1-2*y2+y3)     //t=0..1
    private float calculateYinParabola(float animation_timer) {
        return (beggining_transition_positionY
                -animation_timer*(3*beggining_transition_positionY -4*apex_transition_positionY +next_transition_positionY)
                +2*(float)Math.pow(animation_timer, 2)*
                    (beggining_transition_positionY-2*apex_transition_positionY+next_transition_positionY));
    }
    //z(t) = z1 - t*(3*z1-4*z2+z3) + 2*t^2*(z1-2*z2+z3)     //t=0..1
    private float calculateZinParabola(float animation_timer) {
        return (beggining_transition_positionZ
                -animation_timer*(3*beggining_transition_positionZ -4*apex_transition_positionZ +next_transition_positionZ)
                +2*(float)Math.pow(animation_timer, 2)*
                    (beggining_transition_positionZ-2*apex_transition_positionZ+next_transition_positionZ));
    }

    public void startDying() {
        isDying = true;
        inTransition=true;
        next_transition_position=current_position;
    }
}
