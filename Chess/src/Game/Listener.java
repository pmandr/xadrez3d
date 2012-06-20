/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Jogl.Camera;
import Jogl.Jogl;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCanvas;

/**
 *
 * @author Lobosque
 */
public class Listener extends KeyAdapter implements MouseListener {
    private GLCanvas canvas;
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(!Camera.isInTransition())Camera.rotateClockWise(Camera.getCurrentPosition()+1);
                break;
            case KeyEvent.VK_LEFT:
                if(!Camera.isInTransition())Camera.rotateCounterClockWise(Camera.getCurrentPosition()-1);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        switch(e.getButton()){
            case MouseEvent.BUTTON1:
                if(!Game.isInTransition){
                    Jogl.setMouseEvent(e);
                    this.canvas.display();
                } 
                break;
            case MouseEvent.BUTTON2:
                if(!Camera.isInTransition())Camera.rotateCounterClockWise(Camera.getCurrentPosition()-1);
                break;
            case MouseEvent.BUTTON3:
                if(!Camera.isInTransition())Camera.rotateClockWise(Camera.getCurrentPosition()+1);
                break;
            
        }
        
    }

    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUnprojectionCanvas(GLCanvas canvas) {
        this.canvas = canvas;
    }
}
