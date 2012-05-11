/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogl;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Camera {
    
    private float PosX;
    private float PosZ;
    private float PosY;
    private float LookAtPointX;
    private float LookAtPointY;
    private float LookAtPointZ;
    private float UpX;
    private float UpY;
    private float UpZ;

    //Essas variaveis aqui são públicas pq fiz correndo! Usar private com getter/setter!
    private float rot;
    private float pitch;
    private  float lastx;
    private float lasty;
    private int player;

    public Camera() {
        loadPlayer1View();
        player=1;
        lastx = 0;
        lasty = 0;

        pitch = 0;
        rot = (float) (Math.PI / 2);

    }
    
    //a visao do jogador 1 eh a do lado das pecas brancas
    public void loadPlayer1View(){
        LookAtPointX = 8.0f;
        LookAtPointZ = 0.0f;
        LookAtPointY = 8.0f;

        PosX = -16.0f;
        PosY = 16.0f;
        PosZ = 8.0f;

        UpX = 0;
        UpY = 1;
        UpZ = 0;
    }
    public void loadPlayer2View(){
        this.LookAtPointX = 8.0f;
        this.LookAtPointZ = 0.0f;
        this.LookAtPointY = 8.0f;
        
        while(this.PosX<32.0f){
            try {
                Thread.sleep(10);
                this.PosX+=1.0f;
            } catch (Exception ex) {
                Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.PosX = 32.0f;
        this.PosY = 16.0f;
        this.PosZ = 8.0f;

        this.UpX = 0;
        this.UpY = 1;
        this.UpZ = 0;
    }
    public void changePlayerView(){
        if(player==1){
            loadPlayer2View();
            player=2;
        }else{
            loadPlayer1View();
            player=1;
        }
    }

    public void moveFront() {
//        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeZ += Math.sin(this.pitch);
        this.PosY+=1;
        this.update();
    }

    public void moveBack() {
//        this.EyeX -= (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeY -= -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeZ -= Math.sin(this.pitch);
        this.PosY-=1;
        this.update();
    }

    public void strafeLeft() {
        this.LookAtPointX += Math.cos(this.rot + Math.PI / 2) / 2;
        this.LookAtPointY += -Math.sin(this.rot + Math.PI / 2) / 2;
        this.update();
    }

    public void strafeRight() {
        this.LookAtPointX -= Math.cos(this.rot + Math.PI / 2) / 2;
        this.LookAtPointY -= -Math.sin(this.rot + Math.PI / 2) / 2;
        this.update();
    }

    public void moveUp() {
//        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.EyeZ += Math.sin(this.pitch);
        this.PosZ+=1;
        this.update();
    }

    public void moveDown() {
//        this.LookAtPointX -= (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.LookAtPointZ -= -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.LookAtPointY -= Math.sin(this.pitch);
        this.PosZ-=1;
        this.update();
    }

    public void update() {
        PosX = (float) (LookAtPointX + (Math.cos(rot) * Math.cos(pitch)));
        PosZ = (float) (LookAtPointY - (Math.sin(rot) * Math.cos(pitch)));
        PosY = (float) (LookAtPointZ + Math.sin(pitch));

        UpX = (float) (Math.cos(rot) * Math.cos((pitch + Math.PI / 2)));
        UpZ = (float) (-Math.sin(rot) * Math.cos((pitch + Math.PI / 2)));
        UpY = (float) Math.sin((pitch + Math.PI / 2));
    }
    


    public float getPosX() {
        return PosX;
    }

    public float getPosY() {
        return PosY;
    }

    public float getPosZ() {
        return PosZ;
    }

    public float getLookAtPointX() {
        return LookAtPointX;
    }

    public float getLookAtPointY() {
        return LookAtPointZ;
    }

    public float getLookAtPointZ() {
        return LookAtPointY;
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
}
