/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Chess.Board;
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

/**
 *
 * @author Lobosque
 */
public class Game {

    public static Map<String, Model> models;
    public static Board board;

    public static void createBoard() {
        board = new Board(models.get("board"));
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
}
