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
    private Camera camera;
    private GLCanvas canvas;
    
    public Listener(Camera camera) {
        this.camera = camera;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W://faz zoom-in
                try {
                    Game.getAlivePiece(6, 7).startTransitionTo(new Point(3,7));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_S://faz zoom-out
                try {
                    Game.getAlivePiece(6, 1).startTransitionTo(new Point(3,6));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_A://faz zoom-out
                try {
                    Game.getAlivePiece(2, 1).startTransitionTo(new Point(7,0));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_D://faz zoom-out
                try {
                    Game.getAlivePiece(6, 6).startTransitionTo(new Point(2,2));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_Q://faz zoom-out
                try {
                    Game.getAlivePiece(6, 5).startTransitionTo(new Point(5,2));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_E:
                try {
                    Game.getAlivePiece(1, 2).startTransitionTo(new Point(2,7));
                            } catch (Exception ex) {
                                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                break;
            case KeyEvent.VK_X:
                if(!camera.isInTransition())camera.changePlayerView();
                break;
            case KeyEvent.VK_RIGHT:
                if(!camera.isInTransition())camera.rotateClockWise(camera.getCurrentPosition()+1);
                break;
            case KeyEvent.VK_LEFT:
                if(!camera.isInTransition())camera.rotateCounterClockWise(camera.getCurrentPosition()-1);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        Jogl.setMouseEvent(e); 
        this.canvas.display();
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
