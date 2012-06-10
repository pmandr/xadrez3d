/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Chess.Board;
import Chess.Piece;
import Jogl.Camera;
import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import Jogl.Loader;
import Jogl.Jogl;
import Jogl.Model;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.*;
import javax.media.opengl.GL;

/**
 *
 * @author Lobosque
 */
public class Game {

    public static Map<String, Model> models;
    public static Board board;
    public static Piece[][] alive_pieces= new Piece[8][8];
    public static boolean isInTransition = false;
    private static int player = 1;
    public static Point current_selected_position_1OF2=null;
    public static Point current_selected_position_2OF2=null;
    private static boolean piece_touched_piece_moved=false;
    private static boolean paint_possible_moves = true;
    public static void createBoard() {
        board = new Board(models.get("board"));
    }
    public static void initialize_pieces(){
        alive_pieces[0][0] = Piece.factory("light_rook", new Point(0,0));
        alive_pieces[0][1] = Piece.factory("light_knight",new Point(0,1));
        alive_pieces[0][2] = Piece.factory("light_bishop",new Point(0,2));
        alive_pieces[0][3] = Piece.factory("light_queen", new Point(0,3));
        alive_pieces[0][4] = Piece.factory("light_king",new Point(0,4));
        alive_pieces[0][5] = Piece.factory("light_bishop",new Point(0,5));
        alive_pieces[0][6] = Piece.factory("light_knight",new Point(0,6));
        alive_pieces[0][7] = Piece.factory("light_rook",new Point(0,7));
        alive_pieces[1][0] = Piece.factory("light_pawn",new Point(1,0));
        alive_pieces[1][1] = Piece.factory("light_pawn",new Point(1,1));
        alive_pieces[1][2] = Piece.factory("light_pawn",new Point(1,2));
        alive_pieces[1][3] = Piece.factory("light_pawn",new Point(1,3));
        alive_pieces[1][4] = Piece.factory("light_pawn",new Point(1,4));
        alive_pieces[1][5] = Piece.factory("light_pawn",new Point(1,5));
        alive_pieces[1][6] = Piece.factory("light_pawn",new Point(1,6));
        alive_pieces[1][7] = Piece.factory("light_pawn",new Point(1,7));
        for(int i=2;i<6;i++)
            for(int j=0;j<8;j++)
                alive_pieces[i][j] = null;
        alive_pieces[6][0] = Piece.factory("dark_pawn",new Point(6,0));
        alive_pieces[6][1] = Piece.factory("dark_pawn",new Point(6,1));
        alive_pieces[6][2] = Piece.factory("dark_pawn",new Point(6,2));
        alive_pieces[6][3] = Piece.factory("dark_pawn",new Point(6,3));
        alive_pieces[6][4] = Piece.factory("dark_pawn",new Point(6,4));
        alive_pieces[6][5] = Piece.factory("dark_pawn",new Point(6,5));
        alive_pieces[6][6] = Piece.factory("dark_pawn",new Point(6,6));
        alive_pieces[6][7] = Piece.factory("dark_pawn",new Point(6,7));
        alive_pieces[7][0] = Piece.factory("dark_rook",new Point(7,0));
        alive_pieces[7][1] = Piece.factory("dark_knight",new Point(7,1));
        alive_pieces[7][2] = Piece.factory("dark_bishop",new Point(7,2));
        alive_pieces[7][3] = Piece.factory("dark_queen",new Point(7,3));
        alive_pieces[7][4] = Piece.factory("dark_king",new Point(7,4));
        alive_pieces[7][5] = Piece.factory("dark_bishop",new Point(7,5));
        alive_pieces[7][6] = Piece.factory("dark_knight",new Point(7,6));
        alive_pieces[7][7] = Piece.factory("dark_rook",new Point(7,7));
//        for(int i=0;i<8;i++)
//            for(int j=0; j<8;j++)
//                if(alive_pieces[i][j]!=null) 
//                    alive_pieces[i][j].calculatePossibleMoves();
    }
    public static void updateTransition(){
        
    } 
    public static void loadModels() throws IOException {
        models = new HashMap<String, Model>();

        Model m;
        m = new Model("light_bishop");
        models.put(m.getName(), m);
        m = new Model("light_king");
        models.put(m.getName(), m);
        m = new Model("light_knight");
        models.put(m.getName(), m);
        m = new Model("light_pawn");
        models.put(m.getName(), m);
        m = new Model("light_queen");
        models.put(m.getName(), m);
        m = new Model("light_rook");
        models.put(m.getName(), m);
        m = new Model("dark_bishop");
        models.put(m.getName(), m);
        m = new Model("dark_king");
        models.put(m.getName(), m);
        m = new Model("dark_knight");
        models.put(m.getName(), m);
        m = new Model("dark_pawn");
        models.put(m.getName(), m);
        m = new Model("dark_queen");
        models.put(m.getName(), m);
        m = new Model("dark_rook");
        models.put(m.getName(), m);
        m = new Model("board");
        models.put(m.getName(), m);
    }

    public static void compileModels(GLAutoDrawable drawable) {
        Collection c = models.values();
        Model m = null;
        //obtain an Iterator for Collection
        Iterator itr = c.iterator();

        //iterate through HashMap values iterator
        while (itr.hasNext()) {
            m = (Model) itr.next();
            m.getObj().compile(drawable, Loader.WF_MATERIAL  | Loader.WF_SMOOTH);
        }
    }

    public static void loadAlivePieces(GL gl,GLAutoDrawable drawable) {
        //desenha cada peca
        for(int i=0;i<8;i++)
            for(int j=0; j<8;j++){
                if(alive_pieces[i][j]!=null){
                    gl.glLoadIdentity();
                    //a localizacao no tabuleiro, em coordenadas de mundo, eh dado por 2*Posicao +1
                    if(!alive_pieces[i][j].isInTransition()){ 
                        gl.glTranslatef((float)(2*alive_pieces[i][j].getCurrent_position().getX()+1), (float)(alive_pieces[i][j].getHeight_factor()), (float)(2*alive_pieces[i][j].getCurrent_position().getY()+1));
                    }else{
                        alive_pieces[i][j].updateTransition();
                        try{
                            gl.glTranslatef(alive_pieces[i][j].getCurrentTransitionPositionX(), 
                                (float)(alive_pieces[i][j].getHeight_factor())+alive_pieces[i][j].getCurrentTransitionPositionY(),
                                alive_pieces[i][j].getCurrentTransitionPositionZ());
                        }catch(NullPointerException ex){
                            System.out.println("Erro de leitura: Null Pointer exception");
                        }
                    }
                    
                    float scale = alive_pieces[i][j].getScale_factor();
                    gl.glScalef(scale, scale, scale);
                    
                    float rotate = alive_pieces[i][j].getRotate_factor();
                    if(rotate!=0.0f) gl.glRotatef(rotate, 0.0f, 1.0f, 0.0f);
                    
                    Game.models.get(alive_pieces[i][j].getName()).draw(drawable);
                }
            }
    }

    /*
     * @method Recupera uma peca do tabuleiro
     * @param posX e posY devem ser as coordenadas da peça no tabuleiro,
     *      sendo aceitos apenas valores entre 0 e 7
     */
    public static Piece getAlivePiece(int posX, int posY) {
        if(posX>7 || posX<0 || posY>7 || posY<0 ) return Piece.factory("out of borders", new Point(-1,-1));
        else return alive_pieces[posX][posY];
    }
    
    public static int changePlayer(){
        if(Game.player==1) Game.player=2;
        else Game.player=1;
        return Game.player;
    }

    public static void drawCurrentPositionAndChildrenPositions(GL gl) {
        if(Game.player == 1){
            gl.glColor3f(1.0f, 0.0f, 0.0f);
        }else gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3f((float)current_selected_position_1OF2.getX()*2, 0.01f, (float)current_selected_position_1OF2.getY()*2);
            gl.glVertex3f((float)current_selected_position_1OF2.getX()*2, 0.01f, (float)current_selected_position_1OF2.getY()*2+2);
            gl.glVertex3f((float)current_selected_position_1OF2.getX()*2+2, 0.01f, (float)current_selected_position_1OF2.getY()*2+2);
            gl.glVertex3f((float)current_selected_position_1OF2.getX()*2+2, 0.01f, (float)current_selected_position_1OF2.getY()*2);
        gl.glEnd();
        
        if(paint_possible_moves){
            ArrayList<Point> possible_moves = getAlivePiece((int)current_selected_position_1OF2.getX(),(int)current_selected_position_1OF2.getY()).getPossible_moves();
            for(int i=0;i<possible_moves.size();i++){
                Point p = possible_moves.get(i);
                gl.glBegin(GL.GL_POLYGON);
                    gl.glVertex3f((float)p.getX()*2, 0.01f, (float)p.getY()*2);
                    gl.glVertex3f((float)p.getX()*2, 0.01f, (float)p.getY()*2+2);
                    gl.glVertex3f((float)p.getX()*2+2, 0.01f, (float)p.getY()*2+2);
                    gl.glVertex3f((float)p.getX()*2+2, 0.01f, (float)p.getY()*2);
                gl.glEnd();
            }
        }
    }

    public static void loadBoad(GL gl, GLAutoDrawable drawable) {
        float proportionality = (float) (21.2/19.5);//proporcao entre tamanho de todo tabuleiro e soh a a area das pecas dentro dele
        gl.glTranslatef(-(16*proportionality-16.0f)/2, 0.0f, -(16*proportionality-16.0f)/2);//coloca as bordas para fora do plano cartesiano positivo
        gl.glScalef((8*proportionality),0.10f,(8*proportionality));//o tamanho do tabuleiro(parte dos quadrados) fica 16x16
        gl.glTranslatef(1.0f, 0.0f, 1.0f); //1=a metade tamanho do tabuleiro normalizado antes da escala
        board.draw(drawable);
    }

    //controle de selecao de movimento pelo jogador
    public static void setSelectedMove(int row, int col) {
        if(current_selected_position_1OF2 == null){
            //tenta selecionar o primeiro movimento
            if(Game.getAlivePiece(row, col)!= null)
                if(Game.getAlivePiece(row, col).is_white_colored() && player==1){
                    current_selected_position_1OF2 = new Point(row,col);
                    Piece first_piece_selected = Game.getAlivePiece((int)current_selected_position_1OF2.getX(), (int)current_selected_position_1OF2.getY());
                    first_piece_selected.calculatePossibleMoves();
                }
                else if(!Game.getAlivePiece(row, col).is_white_colored() && player==2){
                    current_selected_position_1OF2 = new Point(row,col);
                    Piece first_piece_selected = Game.getAlivePiece((int)current_selected_position_1OF2.getX(), (int)current_selected_position_1OF2.getY());
                    first_piece_selected.calculatePossibleMoves();
                }
        }else{
            //tenta selecionar o 2o movimento:
            Piece first_piece_selected = Game.getAlivePiece((int)current_selected_position_1OF2.getX(), (int)current_selected_position_1OF2.getY());
            first_piece_selected.calculatePossibleMoves();
        
            //tenta selecionar outra de suas proprias pecas
            if(Game.getAlivePiece(row, col)!= null){
                if(!Game.piece_touched_piece_moved){
                    if(Game.getAlivePiece(row, col).is_white_colored() && player==1)
                        current_selected_position_1OF2 = new Point(row,col);
                        else if(!Game.getAlivePiece(row, col).is_white_colored() && player==2)
                            current_selected_position_1OF2 = new Point(row,col);
                    }
                 //tenta comer uma peca inimiga
                if((Game.isEnemyPiece(Game.getAlivePiece(row, col)) &&
                        first_piece_selected.getPossible_moves().contains(new Point(row,col)))){
                    current_selected_position_2OF2 = new Point(row,col);
                    makeMove("eat_enemy");
                    }
            }else{
                //tenta movimentar para espaco vazio
                if(first_piece_selected.getPossible_moves().contains(new Point(row,col))){
                    current_selected_position_2OF2 = new Point(row,col);
                    makeMove("simple_move");
                    }
                }
             }
    }
    public static Point getBoardPosition(Piece piece){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(alive_pieces[i][j]==piece)
                    return new Point(i,j);
        return null;
    }
    
    //essa funcao deve ser chamada somente quando uma jogada valida é completa
    private static void makeMove(String move) {
        try {

            Game.alive_pieces[(int)current_selected_position_1OF2.getX()][(int)current_selected_position_1OF2.getY()]
                    .startTransitionTo(current_selected_position_2OF2);
            if(move.equalsIgnoreCase("eat_enemy")){
                Game.alive_pieces[(int)current_selected_position_2OF2.getX()][(int)current_selected_position_2OF2.getY()]
                        .startDying();
            }

        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        Game.isInTransition = true;
        Game.alive_pieces[(int)Game.current_selected_position_2OF2.getX()][(int)Game.current_selected_position_2OF2.getY()]
                = Game.alive_pieces[(int)Game.current_selected_position_1OF2.getX()][(int)Game.current_selected_position_1OF2.getY()];
        Game.alive_pieces[(int)Game.current_selected_position_1OF2.getX()][(int)Game.current_selected_position_1OF2.getY()]
                = null;
        Game.alive_pieces[(int)Game.current_selected_position_2OF2.getX()][(int)Game.current_selected_position_2OF2.getY()]
                .setCurrent_position(Game.current_selected_position_2OF2);
        Game.alive_pieces[(int)Game.current_selected_position_2OF2.getX()][(int)Game.current_selected_position_2OF2.getY()]
                .calculatePossibleMoves();
        Game.current_selected_position_1OF2=null;
        Game.current_selected_position_2OF2=null;
    }

    public static void endTransition() {
        Game.isInTransition=false;
        Game.changePlayer();
        Camera.changePlayerView();
//        Runtime.getRuntime().gc();
    }

    public static boolean isEnemyPiece(Piece piece) {
        if(piece==null) return false;
        if(piece.getName().equalsIgnoreCase("out of borders")) return false;
        if(player==1 && !piece.is_white_colored())return true;
        if(player==2 && piece.is_white_colored())return true;
        return false;    
    }
}
