/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogl;

import java.awt.Point;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Camera {

    private float PosX;
    private float PosZ;
    private float PosY;
    private float EyeX;
    private float EyeZ;
    private float EyeY;
    private float UpX;
    private float UpY;
    private float UpZ;

    //Essas variaveis aqui são públicas pq fiz correndo! Usar private com getter/setter!

    public float rot;
    public float pitch;
    public float lastx;
    public float lasty;



    public float getPosX() {
        return PosX;
    }

    public float getPosY() {
        return PosY;
    }

    public float getPosZ() {
        return PosZ;
    }

    public float getEyeX() {
        return EyeX;
    }

    public float getEyeY() {
        return EyeY;
    }

    public float getEyeZ() {
        return EyeZ;
    }

    public float getUpX() {
        return UpX;
    }

    public float getUpY() {
        return UpY;
    }

    public float getUpZ() {
        return UpZ;
    }

    public Camera() {
        EyeX = -1.5f;
        EyeY = -1.5f;
        EyeZ = -6.0f;

        PosX = 40.0f;
        PosY = 40.0f;
        PosZ = 0.0f;

        UpX = 0;
        UpY = 1;
        UpZ = 0;

        lastx = 0;
        lasty = 0;

        pitch = 0;
        rot = (float) (Math.PI / 2);

    }

    public void moveFront() {
        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
        this.EyeZ += Math.sin(this.pitch);
        this.update();
    }

    public void moveBack() {
        this.EyeX -= (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
        this.EyeY -= -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
        this.EyeZ -= Math.sin(this.pitch);
        this.update();
    }

    public void strafeLeft() {
        this.EyeX += Math.cos(this.rot + Math.PI / 2) / 2;
        this.EyeZ += -Math.sin(this.rot + Math.PI / 2) / 2;
        this.update();
    }

    public void strafeRight() {
        this.EyeX -= Math.cos(this.rot + Math.PI / 2) / 2;
        this.EyeZ -= -Math.sin(this.rot + Math.PI / 2) / 2;
        this.update();
    }

    public void moveUp() {
        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
        this.EyeZ += Math.sin(this.pitch);
        this.update();
    }

    public void moveDown() {
        this.EyeX -= (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
        this.EyeY -= -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
        this.EyeZ -= Math.sin(this.pitch);
        this.update();
    }

    public void update() {
        PosX = (float) (EyeX + (Math.cos(rot) * Math.cos(pitch)));
        PosZ = (float) (EyeZ - (Math.sin(rot) * Math.cos(pitch)));
        PosY = (float) (EyeY + Math.sin(pitch));

        UpX = (float) (Math.cos(rot) * Math.cos((pitch + Math.PI / 2)));
        UpZ = (float) (-Math.sin(rot) * Math.cos((pitch + Math.PI / 2)));
        UpY = (float) Math.sin((pitch + Math.PI / 2));
    }
}
