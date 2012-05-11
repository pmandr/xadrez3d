package Jogl;

import Game.*;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
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
public class Jogl implements GLEventListener {

    private static Camera camera = new Camera();
    private static int width =800;
    private static int height=600;
    private GLU glu;
    private static Jogl renderer = new Jogl();
    private static Listener listener = new Listener(camera);


    public static void main(String[] args) throws IOException {
        Game.loadModels();
        Game.createBoard();
        //acelera o rendering   

        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        GLCanvas canvas = new GLCanvas(caps);
        
        
        canvas.addGLEventListener(renderer);
        Frame frame = new Frame("Simple JOGL Application");
        frame.add(canvas);
        frame.setSize(Jogl.width, Jogl.height);
        final FPSAnimator animator = new FPSAnimator(canvas,60);
        frame.addKeyListener(listener);
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
        drawable.addMouseMotionListener(listener);
        // Enable VSync
        gl.setSwapInterval(1);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glShadeModel(GL.GL_SMOOTH);
        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        Game.compileModels(drawable);
        lighting(gl);

    }

    private void lighting(GL gl) {
        float[] ambient = {0.5f, 0.5f, 0.5f, 1.0f};
        float[] diffuse = new float[]{0.6f, 0.6f, 0.6f, 1.0f};
        float[] specular = new float[]{0.7f, 0.7f, 0.7f, 1.0f};
        float[] position = new float[]{8, 8, 50, 0.10f};
//        float[] position = new float[]{100, 10, 50, 1.0f};

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
        
        if(camera.isInTransition()) camera.updateTransition();
        glu.gluLookAt(camera.getPosX(), camera.getPosY(), camera.getPosZ(),
                camera.getLookAtPointX(), camera.getLookAtPointY(), camera.getLookAtPointZ(),
                camera.getUpX(), camera.getUpY(), camera.getUpZ());
//        glu.gluLookAt(-2, 1,-2, 8,0,8,0,1,0);
//        glu.gluLookAt(-10, 10,8, 8,0,8,0,1,0);
        
        //define que a matrix é a de modelo
        gl.glMatrixMode(GL.GL_MODELVIEW);					// Select The Modelview Matrix
        gl.glLoadIdentity();
        float proportionality = (float) (21.2/19.5);//proporcao entre tamanho de todo tabuleiro e soh a a area das pecas dentro dele
        gl.glTranslatef(-(16*proportionality-16.0f)/2, 0.0f, -(16*proportionality-16.0f)/2);//coloca as bordas para fora do plano cartesiano positivo
        gl.glScalef((8*proportionality),0.10f,(8*proportionality));//o tamanho do tabuleiro(parte dos quadrados) fica 16x16
        gl.glTranslatef(1.0f, 0.0f, 1.0f); //1=a metade tamanho do tabuleiro normalizado antes da escala
        Game.board.draw(drawable);
        
        //INICIALIZA PEÇAS... TEM Q MUDAR PARA UMA FUNCAO EM BOARD OU GAME??
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 1.0f);
        Game.models.get("light_rook").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 3.0f);
        Game.models.get("light_knight").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 5.0f);
        Game.models.get("light_bishop").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 7.0f);
        Game.models.get("light_queen").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 9.0f);
        Game.models.get("light_king").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 11.0f);
        Game.models.get("light_bishop").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 13.0f);
        Game.models.get("light_knight").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(1.0f, 1.0f, 15.0f);
        Game.models.get("light_rook").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 1.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 3.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 5.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 7.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 9.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 11.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 13.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 1.0f, 15.0f);
        Game.models.get("light_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 1.0f);
        Game.models.get("dark_rook").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 3.0f);
        Game.models.get("dark_knight").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 5.0f);
        Game.models.get("dark_bishop").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 7.0f);
        Game.models.get("dark_queen").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 9.0f);
        Game.models.get("dark_king").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 11.0f);
        Game.models.get("dark_bishop").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 13.0f);
        Game.models.get("dark_knight").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(15.0f, 1.0f, 15.0f);
        Game.models.get("dark_rook").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 1.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 3.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 5.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 7.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 9.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 11.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 13.0f);
        Game.models.get("dark_pawn").draw(drawable);
        gl.glLoadIdentity();
        gl.glTranslatef(13.0f, 1.0f, 15.0f);
        Game.models.get("dark_pawn").draw(drawable);
        
        gl.glLoadIdentity();
        DrawXYZAxis(gl);
        //força o desenho das primitivas
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public void DrawXYZAxis(GL gl){
           GLUT glut = new GLUT();
                //X Axis
            gl.glLineWidth(3.0f);
            gl.glRasterPos3f(0.0f, 0.0f, 0.0f);   
            glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "x");
                gl.glBegin(GL.GL_LINES);
                        gl.glColor3f(0.0f,1.0f,0.0f);
                        gl.glVertex3f(0,0,0);
                        gl.glVertex3f(20,0,0);
                gl.glEnd();
            
            //Y Axis
            gl.glRasterPos3f(0.0f, 1.2f, 0.0f);    
            glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "y");
            gl.glBegin(GL.GL_LINES);
                        gl.glColor3f(0,1,0);
                        gl.glVertex3f(0,0,0);
                        gl.glVertex3f(0,20,0);
                gl.glEnd();

                //Z Axis
            gl.glRasterPos3f(0.0f, 0.0f, 1.2f);    
            glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "z");
            gl.glBegin(GL.GL_LINES);
                        gl.glColor3f(0,0,1);
                        gl.glVertex3f(0,0,0);
                        gl.glVertex3f(0,0,20);
            gl.glEnd();
 
                
        }
    
}
