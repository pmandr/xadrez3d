/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Jogl.Camera;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Lobosque
 */
public class Listener extends KeyAdapter implements MouseMotionListener{
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

    public void mouseDragged(MouseEvent e) {
//        camera.rot += (e.getX() - camera.lastx > 0)? 0.01f : -0.01f;
//        camera.pitch += (e.getY() - camera.lasty > 0)? 0.01f : -0.01f;
//
//	if(camera.rot > 2*Math.PI)
//		camera.rot -= 2*Math.PI;
//	if(camera.rot < 0)
//		camera.rot += 2*Math.PI;
//
//
//	if(camera.pitch > (float)(Math.PI/2))
//		camera.pitch = (float)(Math.PI/2);
//	if(camera.pitch < (float)-(Math.PI/2))
//		camera.pitch = (float)-(Math.PI/2);
//        camera.lastx = e.getX();
//        camera.lasty = e.getY();
//        camera.update();
    }

    public void mouseMoved(MouseEvent e) {
        

    }
}
