package Jogl;

import Game.*;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 * Chess.java <BR> author: Brian Paul (converted to Java by Ron Cemer and Sven
 * Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Jogl implements GLEventListener {

    private static Camera camera = new Camera();
    private static int width = 800;
    private static int height = 600;
    private GLU glu;
    private static Jogl renderer = new Jogl();
    private static GLCanvas canvas;
    private static Listener listener = new Listener();
    private static MouseEvent mouse_event = null;
    private static Texture bgTexture = null;

    public static void main(String[] args) throws IOException {
        Game.loadModels();
        Game.createBoard();
        //acelera o rendering   

        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        canvas = new GLCanvas(caps);


        canvas.addGLEventListener(renderer);
        JFrame frame = new JFrame("Xadrez 3D");
        frame.getContentPane().add(canvas);
        frame.setSize(Jogl.width, Jogl.height);
        frame.setLocationRelativeTo(null);
        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        //listener.setUnprojectionCanvas(canvas);

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
        //canvas.requestFocusInWindow();
    }

    public void init(GLAutoDrawable drawable) {
        load_texture();
        GL gl = drawable.getGL();
        glu = new GLU();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        drawable.addMouseListener(listener);
        // Enable VSync
        gl.setSwapInterval(1);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glShadeModel(GL.GL_SMOOTH);
        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        Game.initialize_pieces();
        Game.compileModels(drawable);
//        initbg();
        lighting(gl);

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
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glDisable(GL.GL_LIGHTING);
        width = drawable.getWidth();
        height = drawable.getHeight();
        gl.glLoadIdentity();
        //gl.glOrtho(1, 1, 0, 0, 0, 1);
        gl.glTranslatef(-1,-1, 0);
        gl.glScalef(2, 2, 0);
        gl.glDisable(gl.GL_DEPTH_TEST);
        bgTexture.enable();
        bgTexture.bind();
        gl.glBegin(GL.GL_QUADS);
        //gl.glTranslatef(width, width, width);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex2f(0, 0);
        gl.glTexCoord2f(width, 0.0f); gl.glVertex2f(width, 0);
        gl.glTexCoord2f(width, height); gl.glVertex2f(width, height);
        gl.glTexCoord2f(0, height);  gl.glVertex2f(0, height);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex2f(0, 0);
        gl.glTexCoord2f(width, 0.0f); gl.glVertex2f(width, 0);
        gl.glTexCoord2f(width, height); gl.glVertex2f(width, height);
        gl.glTexCoord2f(0, height);  gl.glVertex2f(0, height);
        gl.glEnd();
        gl.glViewport(0, 0, width, height);					// Reset The Current Viewport
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glMatrixMode(GL.GL_PROJECTION);					// Select The Projection Matrix
        gl.glEnable(GL.GL_LIGHTING);
        gl.glLoadIdentity();							// Reset The Projection Matrix
        glu.gluPerspective(45.0, (float) width / (float) height, 1, 50);
//        drawbg(gl, glu);
        if (camera.isInTransition()) {
            camera.updateTransition();
        }
        glu.gluLookAt(camera.getPosX(), camera.getPosY(), camera.getPosZ(),
                camera.getLookAtPointX(), camera.getLookAtPointY(), camera.getLookAtPointZ(),
                camera.getUpX(), camera.getUpY(), camera.getUpZ());

        //define que a matrix é a de modelo
        gl.glMatrixMode(GL.GL_MODELVIEW);					// Select The Modelview Matrix
        gl.glLoadIdentity();

        Game.loadBoad(gl, drawable);
        //carrega PEÇAS...
        Game.loadAlivePieces(gl, drawable);

        gl.glLoadIdentity();
        if (mouse_event != null) {
            processClick(gl, glu);
        }
        if (Game.current_selected_position_1OF2 != null) {
            Game.drawCurrentPositionAndChildrenPositions(gl);
        }
//        DrawXYZAxis(gl);

        //força o desenho das primitivas
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    private void lighting(GL gl) {
        float[] white = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
        float[] ambient = {0.1f, 0.1f, 0.1f, 1.0f};
        float[] diffuse = new float[]{0.6f, 0.5f, 0.0f, 1.0f};
        float[] specular = new float[]{0.6f, 0.1f, 0.1f, 1.0f};
//        float[] position = new float[]{8, 30, 8, 1.0f};
        float[] position = new float[]{-100.0f, 10.0f, 50.0f, 1.0f};

        // Define os parametros da luz de numero 0
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
        //gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_COLOR_MATERIAL);
        gl.glEnable(GL.GL_LIGHT0);
//        gl.glLightf(GL.GL_LIGHT0, GL.GL_CONSTANT_ATTENUATION, 0.25f);
//        gl.glLightf(GL.GL_LIGHT0, GL.GL_LINEAR_ATTENUATION, 0.25f);
//        gl.glLightf(GL.GL_LIGHT0, GL.GL_QUADRATIC_ATTENUATION, 0.01f);
    }

    public void DrawXYZAxis(GL gl) {

        GLUT glut = new GLUT();
        //X Axis
        gl.glLineWidth(3.0f);
        float[] rgba = {0.0f, 1.0f, 0f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgba, 0);
        gl.glRasterPos3f(0.0f, 0.0f, 0.0f);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "x");
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(20, 0, 0);
        gl.glEnd();

        //Y Axis
        rgba = new float[]{1.0f, 0.0f, 0f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgba, 0);
        gl.glRasterPos3f(0.0f, 1.2f, 0.0f);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "y");
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 20, 0);
        gl.glEnd();

        //Z Axis
        rgba = new float[]{0.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgba, 0);
        gl.glRasterPos3f(0.0f, 0.0f, 1.2f);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "z");
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 0, 20);
        gl.glEnd();
    }

    public static void setMouseEvent(MouseEvent e) {
        mouse_event = e;
    }

    private void processClick(GL gl, GLU glu) {
        //GLU UNPROJECT VARIABLES
        int viewport[] = new int[4];
        double mvmatrix[] = new double[16];
        double projmatrix[] = new double[16];
        int realy = 0;// GL y coord pos
        double wcoord[] = new double[4];// wx, wy, wz;// returned xyz coords

        int x = mouse_event.getX(), y = mouse_event.getY();
        switch (mouse_event.getButton()) {
            case MouseEvent.BUTTON1:
                gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
                gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix, 0);
                gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projmatrix, 0);
                /*
                 * note viewport[3] is height of window in pixels
                 */
                realy = viewport[3] - (int) y - 1;
//                System.out.println("Coordinates at cursor are (" + x + ", " + realy);
                glu.gluUnProject((double) x, (double) realy, 0.0, //
                        mvmatrix, 0,
                        projmatrix, 0,
                        viewport, 0,
                        wcoord, 0);
//            System.out.println("World coords at z=0.0 are ( " //
//                                + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
//                                + ")");
                double[] p1 = {wcoord[0], wcoord[1], wcoord[2]};
                glu.gluUnProject((double) x, (double) realy, 1.0, //
                        mvmatrix, 0,
                        projmatrix, 0,
                        viewport, 0,
                        wcoord, 0);
                double[] p2 = {wcoord[0], wcoord[1], wcoord[2]};
//            System.out.println("World coords at z=1.0 are (" //
//                                + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
//                                + ")");

                double[] point = new double[3];
                double[] VET_DIRETOR = {(p1[0] - p2[0]), (p1[1] - p2[1]), (p1[2] - p2[2])};
                double t = (-p1[1]) / VET_DIRETOR[1];
                //feita com equacoes parametricas da reta:http://www.mat.puc-rio.br/cursos/MAT1200/roteiros//aula2091.pdf
                point[0] = p1[0] + (t * VET_DIRETOR[0]);
                point[1] = 0;
                point[2] = p1[2] + (t * VET_DIRETOR[2]);
//            System.out.println("board X = "+ point[0]+", board z = "+point[2]);

                //converte para as coordenadas de tabuleiro
                point[0] = Math.floor(point[0]);
                point[0] -= point[0] % 2;
                point[0] = point[0] / 2;
                point[2] = Math.floor(point[2]);
                point[2] -= point[2] % 2;
                point[2] = point[2] / 2;

//            System.out.println("Posicao selecionada: ("+ point[0]+","+ point[2]+")");

                if (point[0] >= 0 && point[0] <= 7 && point[2] >= 0 && point[2] <= 7) {
                    Game.setSelectedMove((int) point[0], (int) point[2]);
                }
                mouse_event = null;
                break;
            case MouseEvent.BUTTON2:
                break;
            default:
                break;
        }
    }

    public void load_texture() {
        try {
            InputStream stream = getClass().getResourceAsStream("/models/texture.jpg");
            TextureData data = TextureIO.newTextureData(stream, false, "jpg");
            bgTexture = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }
}