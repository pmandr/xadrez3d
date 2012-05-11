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
    
    private final float LookAtPointX = 7.9f;
    private final float LookAtPointY = 0;
    private final float LookAtPointZ = 7.9f;
    private final float UpX =0;
    private final float UpY =1;
    private final float UpZ =0;

    private float PosX;
    private final float PosY = 10.0f;;
    private final float PosZ = 8.0f;
    //Essas variaveis aqui são públicas pq fiz correndo! Usar private com getter/setter!
    private float rot;
    private float pitch;
    private  float lastx;
    private float lasty;
    private int player =1;
    private boolean transition = false;
    
    //POSICAO PADRAO DO PLAYER 1
    private final float P1PosX = -10.0f;
    private final float P1PosY = 10.0f;
    private final float P1PosZ = 8.0f;
    
    //POSICAO PADRAO DO PLAYER 2
    private final float P2PosX = 26.0f;
    private final float P2PosY = 10.0f;
    private final float P2PosZ = 8.0f;

    public Camera() {
        loadPlayer1View();
        lastx = 0;
        lasty = 0;

        pitch = 0;
        rot = (float) (Math.PI / 2);
    }
    
    
    private boolean isAtPlayer2Position() {
        if(this.PosX==this.P2PosX)
            return true;
        else return false;
    }

    private boolean isAtPlayer1Position() {
        if(this.PosX==this.P1PosX)
            return true;
        else return false;
    }
    
    //a visao do jogador 1 eh a do lado das pecas brancas
    public void loadPlayer1View(){
        this.PosX = this.P1PosX;
//        this.PosY = this.P1PosY;
//        this.PosZ = this.P1PosZ;
    }
    
    public void loadPlayer2View(){        
        this.PosX = this.P2PosX;
//        this.PosY = this.P2PosY;
//        this.PosZ = this.P2PosZ;
    }
    public void changePlayerView(){
        transition = true;
//        if(player==1 && this.isAtPlayer2Position()){
//            transition=false;
//            player=2;
//        }else if(player==2 && this.isAtPlayer1Position()){
//            transition=false;
//            player=1;
//        }
    }
    
    public void updateTransition(){
        if(this.transition){
            if(this.player==1 ) {
                if(!isAtPlayer2Position()) this.PosX+=1.0f;
                else{
                    this.transition = false;
                    this.player =2;
                }
            }
            //if the current player is the p2
            else if(!isAtPlayer1Position()) this.PosX-=1.0f;
                   else {
                    this.transition = false;
                    this.player = 1;
                    }
        }
    }
    
    public boolean isInTransition(){
        return this.transition;
    }

    public void moveFront() {
//        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeZ += Math.sin(this.pitch);
//        this.PosY+=1;
//        this.update();
    }

    public void moveBack() {
//        this.EyeX -= (Math.cos(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeY -= -(Math.sin(this.rot) * Math.cos(this.pitch)) / 2;
//        this.EyeZ -= Math.sin(this.pitch);
//        this.PosY-=1;
//        this.update();
    }

    public void strafeLeft() {
//        this.LookAtPointX += Math.cos(this.rot + Math.PI / 2) / 2;
//        this.LookAtPointY += -Math.sin(this.rot + Math.PI / 2) / 2;
//        this.update();
    }

    public void strafeRight() {
//        this.LookAtPointX -= Math.cos(this.rot + Math.PI / 2) / 2;
//        this.LookAtPointY -= -Math.sin(this.rot + Math.PI / 2) / 2;
//        this.update();
    }

    public void moveUp() {
//        this.EyeX += (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.EyeY += -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.EyeZ += Math.sin(this.pitch);
//        this.PosZ+=1;
//        this.update();
    }

    public void moveDown() {
//        this.LookAtPointX -= (Math.cos(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.LookAtPointZ -= -(Math.sin(this.rot) * Math.cos(this.pitch + Math.PI / 2)) / 2;
//        this.LookAtPointY -= Math.sin(this.pitch);
//        this.PosZ-=1;
//        this.update();
    }

    public void update() {
//        PosX = (float) (LookAtPointX + (Math.cos(rot) * Math.cos(pitch)));
//        PosZ = (float) (LookAtPointY - (Math.sin(rot) * Math.cos(pitch)));
//        PosY = (float) (LookAtPointZ + Math.sin(pitch));
//
//        UpX = (float) (Math.cos(rot) * Math.cos((pitch + Math.PI / 2)));
//        UpZ = (float) (-Math.sin(rot) * Math.cos((pitch + Math.PI / 2)));
//        UpY = (float) Math.sin((pitch + Math.PI / 2));
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
        return LookAtPointY;
    }

    public float getLookAtPointZ() {
        return LookAtPointZ;
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
