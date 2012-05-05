package Jogl;

import Game.Game;
import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

/**
 * Chess.java <BR> author: Brian Paul (converted to Java by Ron Cemer and Sven
 * Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Jogl extends KeyAdapter implements GLEventListener, MouseMotionListener {

    private static Camera camera;
    public static int width;
    public static int height;
    private GLU glu;


    public static void main(String[] args) throws IOException {
        Game.loadModels();
        Game.createBoard();
        //acelera o rendering   

        camera = new Camera();

        Jogl.width = 1280;
        Jogl.height = 960;
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        GLCanvas canvas = new GLCanvas(caps);
        Jogl j = new Jogl();
        canvas.addGLEventListener(j);
        Frame frame = new Frame("Simple JOGL Application");
        frame.add(canvas);
        frame.setSize(1280, 960);
        final Animator animator = new Animator(canvas);
        frame.addKeyListener(j);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        glu = new GLU();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        drawable.addMouseMotionListener(this);
        // Enable VSync
        gl.setSwapInterval(1);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glShadeModel(GL.GL_SMOOTH);
        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        Game.compileModels(drawable);
        lighting(drawable);

    }

    private void lighting(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        float[] ambient = {0.3f, 0.3f, 0.3f, 1.0f};
        float[] diffuse = new float[]{0.75f, 0.75f, 0.75f, 1.0f};
        float[] specular = new float[]{0.7f, 0.7f, 0.7f, 1.0f};
        float[] position = new float[]{100, 10, 50, 1.0f};

        // Define os parametros da luz de numero 0
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 10.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        //limpa o buffer
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        /*PointerInfo p = MouseInfo.getPointerInfo();
        camera.rotate(p.getLocation()); */

        width = drawable.getWidth();
        height = drawable.getHeight();
        gl.glViewport(0, 0, width, height);					// Reset The Current Viewport

        gl.glMatrixMode(GL.GL_PROJECTION);					// Select The Projection Matrix
        gl.glLoadIdentity();							// Reset The Projection Matrix
        glu.gluPerspective(45.0, (float)width/(float)height, 1, 50);
        glu.gluLookAt(camera.getEyeX(), camera.getEyeY(), camera.getEyeZ(),
                camera.getPosX(), camera.getPosY(), camera.getPosZ(),
                camera.getUpX(), camera.getUpY(), camera.getUpZ());
        
        //define que a matrix é a de modelo
        gl.glMatrixMode(GL.GL_MODELVIEW);					// Select The Modelview Matrix
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, -1.58f, -6.0f);
        gl.glScalef(6.0f, 6.0f, 6.0f);

        Game.board.draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        Game.models.get("dark_rook").draw(drawable);
        //força o desenho das primitivas
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W://faz zoom-in
                camera.moveFront();
                break;
            case KeyEvent.VK_S://faz zoom-out
                camera.moveBack();
                break;
            case KeyEvent.VK_A://faz zoom-out
                camera.strafeRight();
                break;
            case KeyEvent.VK_D://faz zoom-out
                camera.strafeLeft();
                break;
            case KeyEvent.VK_J://faz zoom-out
                camera.moveUp();
                break;
            case KeyEvent.VK_K://faz zoom-out
                camera.moveDown();
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        camera.rot += (e.getX() - camera.lastx > 0)? 0.01f : -0.01f;
        camera.pitch += (e.getY() - camera.lasty > 0)? 0.01f : -0.01f;

	if(camera.rot > 2*Math.PI)
		camera.rot -= 2*Math.PI;
	if(camera.rot < 0)
		camera.rot += 2*Math.PI;


	if(camera.pitch > (float)(Math.PI/2))
		camera.pitch = (float)(Math.PI/2);
	if(camera.pitch < (float)-(Math.PI/2))
		camera.pitch = (float)-(Math.PI/2);
        camera.lastx = e.getX();
        camera.lasty = e.getY();
    camera.update();

    }
}
