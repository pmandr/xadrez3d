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
    
    private final float LookAtPointX = 8.0f;
    private final float LookAtPointY = 0;
    private final float LookAtPointZ = 8.0f;
    private  final float UpX =0;
    private  final float UpY =1;
    private  final float UpZ =0;
    private float PosX;
    private final float PosY = 10.0f;;
    private float PosZ = 8.0f;

    private int player =1;
    private boolean transition = false;
    
    //posicao nro 0 = P1; 1 = RS; 2 = P2; 3=LS
    private int current_position =0;
    private int next_position =2;
    
    //A EQUACAO CIRCULAR QUE DESCREVE O MOVIMENTO ROTACIOAL DA CAMERA EH: (x-8)^2+(z-8)^2=18^2
    //POSICAO PADRAO DO PLAYER 1
    private final float P1PosX = -10.0f;
    private final float P1PosY = 10.0f;
    private final float P1PosZ = 8.0f;
    
    //POSICAO PADRAO DO PLAYER 2
    private final float P2PosX = 26.0f;
    private final float P2PosY = 10.0f;
    private final float P2PosZ = 8.0f;
    
    //POSICAO PADRAO DA VISTA ESQUERDA (LEFT SIDE VIEW) (EM RELACAO AO P1)
    private final float LSPosX = 8.0f;
    private final float LSPosY = 10.0f;
    private final float LSPosZ = -10.0f;
    
    //POSICAO PADRAO DA VISTA DIREITA (RIGHT SIDE VIEW) (EM RELACAO AO P1)
    private final float RSPosX = 8.0f;
    private final float RSPosY = 10.0f;
    private final float RSPosZ = 26.0f;
    private boolean rotate_clockwise = true;

    public Camera() {
        loadPlayer1View();
    }
    
    
    private boolean isAtPlayer2Position() {
        if(this.PosX==this.P2PosX && this.PosZ==this.P2PosZ)
            return true;
        else return false;
    }

    private boolean isAtPlayer1Position() {
        if(this.PosX==this.P1PosX && this.PosZ==this.P1PosZ)
            return true;
        else return false;
    }
    private boolean isAt_RightSideView_Position() {
        if(this.PosX==this.RSPosX && this.PosZ==this.RSPosZ)
            return true;
        else return false;
    }
    private boolean isAt_LeftSideView_Position() {
        if(this.PosX==this.LSPosX && this.PosZ==this.LSPosZ)
            return true;
        else return false;
    }
    
    //a visao do jogador 1 eh a do lado das pecas brancas
    public void loadPlayer1View(){
        this.PosX = this.P1PosX;
        this.PosZ = this.P1PosZ;
    }
    
    public void loadPlayer2View(){        
        this.PosX = this.P2PosX;
        this.PosZ = this.P2PosZ;
    }
    
    //esta funcao deve ser chamada apos cada jogada feita
    public void changePlayerView(){
        if(this.transition==true) return;
        if(this.player==1){
            if(this.isAtPlayer1Position()){
                this.transition=true;
            } else if(this.isAt_LeftSideView_Position()){
                this.transition=true;
                this.rotate_clockwise = false;
            } else if(this.isAt_RightSideView_Position()){
                this.transition=true;
                this.rotate_clockwise = true;
            }
            this.next_position=2;
            this.player=2;
        }else{//if current player is number 2
            if(this.isAtPlayer2Position()){
                this.transition=true;
            } else if(this.isAt_LeftSideView_Position()){
                this.transition=true;
                this.rotate_clockwise = true;
            } else if(this.isAt_RightSideView_Position()){
                this.transition=true;
                this.rotate_clockwise = false;
            }
            this.next_position=0;
            this.player=1;
        }
    }
    
    public void updateTransition() {
        if(this.next_position==0 && this.isAtPlayer1Position()){
            this.transition=false;
            this.current_position=0;
            return;
        }
        if(this.next_position==1 && this.isAt_RightSideView_Position()){
            this.transition=false;
            this.current_position=1;
            return;
        }
        if(this.next_position==2 && this.isAtPlayer2Position()){
            this.transition=false;
            this.current_position=2;
            return;
        }
        if(this.next_position==3 && this.isAt_LeftSideView_Position()){
            this.transition=false;
            this.current_position=3;
            return;
        }
        if(this.rotate_clockwise){
            if(this.next_position!=3 && this.next_position!=0){
                this.PosX ++;
                this.PosZ =(float) (Math.sqrt(-Math.pow(this.PosX,2)+16*this.PosX+260)+8);
            } else {
                this.PosX--;
                this.PosZ =(float) (-Math.sqrt(-Math.pow(this.PosX,2)+16*this.PosX+260)+8);
            }
            
        }else{
            if(this.next_position!=1 && this.next_position!=0){
                this.PosX ++;
                this.PosZ =(float) (-Math.sqrt(-Math.pow(this.PosX,2)+16*this.PosX+260)+8);
            } else {
                this.PosX--;
                this.PosZ =(float) (Math.sqrt(-Math.pow(this.PosX,2)+16*this.PosX+260)+8);
            }
        }
    }
    public void loadRightSideView() {
        this.PosX = this.RSPosX;
        this.PosZ = this.RSPosZ;
    }

    public void loadLeftSideView() {
        this.PosX = this.LSPosX;
        this.PosZ = this.LSPosZ;
    }
    
    public void rotateClockWise(int next_position){
        if(next_position==this.current_position)return;
        next_position = next_position%4;//numeros de posicao variam somente de 0 a 3;
        this.next_position = next_position;
        this.rotate_clockwise = true;
        this.transition = true;        
    }
    public void rotateCounterClockWise(int next_position){
        if(next_position==this.current_position)return;
        if(next_position==-1)next_position=3;//este passo eh porque nao sabe calcular o mod de numeros negativos
        next_position = next_position%4;//numeros de posicao variam somente de 0 a 3;
        this.next_position = next_position;
        this.rotate_clockwise = false;
        this.transition = true;        
    }
    
    public boolean isInTransition(){
        return this.transition;
    }

    public int getCurrentPosition(){
        return this.current_position;
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
