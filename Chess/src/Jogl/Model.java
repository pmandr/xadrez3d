/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogl;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

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
