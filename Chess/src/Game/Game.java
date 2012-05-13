/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Chess.Board;
import Chess.Piece;
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
import javax.media.opengl.GL;

/**
 *
 * @author Lobosque
 */
public class Game {

    public static Map<String, Model> models;
    public static Board board;
    private static Piece[][] alive_pieces= new Piece[8][8];
    private static boolean isInTransition = false;

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
        alive_pieces[6][0] = Piece.factory("light_pawn",new Point(6,0));
        alive_pieces[6][1] = Piece.factory("light_pawn",new Point(6,1));
        alive_pieces[6][2] = Piece.factory("light_pawn",new Point(6,2));
        alive_pieces[6][3] = Piece.factory("light_pawn",new Point(6,3));
        alive_pieces[6][4] = Piece.factory("light_pawn",new Point(6,4));
        alive_pieces[6][5] = Piece.factory("light_pawn",new Point(6,5));
        alive_pieces[6][6] = Piece.factory("light_pawn",new Point(6,6));
        alive_pieces[6][7] = Piece.factory("light_pawn",new Point(6,7));
        alive_pieces[7][0] = Piece.factory("light_rook",new Point(7,0));
        alive_pieces[7][1] = Piece.factory("light_knight",new Point(7,1));
        alive_pieces[7][2] = Piece.factory("light_bishop",new Point(7,2));
        alive_pieces[7][3] = Piece.factory("light_queen",new Point(7,3));
        alive_pieces[7][4] = Piece.factory("light_king",new Point(7,4));
        alive_pieces[7][5] = Piece.factory("light_bishop",new Point(7,5));
        alive_pieces[7][6] = Piece.factory("light_knight",new Point(7,6));
        alive_pieces[7][7] = Piece.factory("light_rook",new Point(7,7));
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
                    gl.glTranslatef((float)(2*alive_pieces[i][j].getCurrent_position().getX()+1), (float)(alive_pieces[i][j].getHeight_factor()), (float)(2*alive_pieces[i][j].getCurrent_position().getY()+1));
                    float scale = alive_pieces[i][j].getScale_factor();
                    gl.glScalef(scale, scale, scale);
                    Game.models.get(alive_pieces[i][j].getName()).draw(drawable);
                }
            }
    }
    
    public static boolean isInTransition(){
        return isInTransition;
    }

    public static void updateTransition(GL gl, GLAutoDrawable drawable) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
