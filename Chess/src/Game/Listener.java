/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Jogl.Camera;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Lobosque
 */
public class Listener extends KeyAdapter implements MouseListener {
    private Camera camera;
    
    public Listener(Camera camera){
        this.camera = camera;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W://faz zoom-in
                break;
            case KeyEvent.VK_S://faz zoom-out
                break;
            case KeyEvent.VK_A://faz zoom-out
                break;
            case KeyEvent.VK_D://faz zoom-out
                break;
            case KeyEvent.VK_Q://faz zoom-out
                break;
            case KeyEvent.VK_E://faz zoom-out
                Game.getAlivePiece(1, 2).startTransitionTo(new Point(2,2));
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
