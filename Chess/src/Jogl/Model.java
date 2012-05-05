/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogl;

import java.io.File;
import java.io.IOException;
import javax.media.opengl.GLAutoDrawable;

/**
 *
 * @author Lobosque
 */
public class Model {
    private String name;
    private Loader obj;
    

    public Loader getObj() {
        return obj;
    }

    public String getName() {
        return name;
    }
    
    public Model(String filename) throws IOException {
        this.name = filename;
        this.obj = new Loader(new File("src/models/" + filename + ".obj"));
        this.setDefaults();

    }

    public Model(String name, String filename) throws IOException {
        this.name = name;
        this.obj = new Loader(new File("src/models/" + filename +  ".obj"));
        this.setDefaults();
    }
    
    private void setDefaults() throws IOException {
        this.obj.unitize();
        this.obj.facetNormals();
        this.obj.vertexNormals(90);
        this.obj.dump(true);
    }

    public void draw(GLAutoDrawable drawable) {
        this.obj.draw(drawable);
    }
}
