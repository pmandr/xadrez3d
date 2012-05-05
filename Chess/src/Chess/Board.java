/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import javax.media.opengl.GLAutoDrawable;
import Jogl.Model;

/**
 *
 * @author Lobosque
 */
public class Board {
    
    private Model model;
    
    public Board(Model model) {
        this.model = model;
    }
    
    public void draw(GLAutoDrawable drawable) {
        model.draw(drawable);
    }
}
