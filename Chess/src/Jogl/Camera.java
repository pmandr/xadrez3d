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
    
    private static final float LookAtPointX = 8.0f;
    private static final float LookAtPointY = 0;
    private static final float LookAtPointZ = 8.0f;
    private static final float UpX =0;
    private static final float UpY =1;
    private static final float UpZ =0;
    private static float PosX;
    private static final float PosY = 10.0f;;
    private static float PosZ = 8.0f;

    private static int player =1;
    private static boolean transition = false;
    
    //posicao nro 0 = P1; 1 = RS; 2 = P2; 3=LS
    private static int current_position =0;
    private static int next_position =2;
    
    //A EQUACAO CIRCULAR QUE DESCREVE O MOVIMENTO ROTACIOAL DA CAMERA EH: (x-8)^2+(z-8)^2=18^2
    //POSICAO PADRAO DO PLAYER 1
    private static  final float P1PosX = -10.0f;
    private static final float P1PosY = 10.0f;
    private static final float P1PosZ = 8.0f;
    
    //POSICAO PADRAO DO PLAYER 2
    private static final float P2PosX = 26.0f;
    private static final float P2PosY = 10.0f;
    private static final float P2PosZ = 8.0f;
    
    //POSICAO PADRAO DA VISTA ESQUERDA (LEFT SIDE VIEW) (EM RELACAO AO P1)
    private static final float LSPosX = 8.0f;
    private static final float LSPosY = 10.0f;
    private static final float LSPosZ = -10.0f;
    
    //POSICAO PADRAO DA VISTA DIREITA (RIGHT SIDE VIEW) (EM RELACAO AO P1)
    private static final float RSPosX = 8.0f;
    private static final float RSPosY = 10.0f;
    private static final float RSPosZ = 26.0f;
    private static boolean rotate_clockwise = true;

    public Camera() {
        loadPlayer1View();
    }
    
    
    private static boolean isAtPlayer2Position() {
        if(PosX==P2PosX && PosZ==P2PosZ)
            return true;
        else return false;
    }

    private static boolean isAtPlayer1Position() {
        if(PosX==P1PosX && PosZ==P1PosZ)
            return true;
        else return false;
    }
    private static boolean isAt_RightSideView_Position() {
        if(PosX==RSPosX && PosZ==RSPosZ)
            return true;
        else return false;
    }
    private static boolean isAt_LeftSideView_Position() {
        if(PosX==LSPosX && PosZ==LSPosZ)
            return true;
        else return false;
    }
    
    //a visao do jogador 1 eh a do lado das pecas brancas
    public static void loadPlayer1View(){
        PosX = P1PosX;
        PosZ = P1PosZ;
    }
    
    public static void loadPlayer2View(){        
        PosX = P2PosX;
        PosZ = P2PosZ;
    }
    
    //esta funcao deve ser chamada apos cada jogada feita
    public static void changePlayerView(){
        if(transition==true) return;
        if(player==1){
            if(isAtPlayer1Position()){
                transition=true;
            } else if(isAt_LeftSideView_Position()){
                transition=true;
                rotate_clockwise = false;
            } else if(isAt_RightSideView_Position()){
                transition=true;
                rotate_clockwise = true;
            }
            next_position=2;
            player=2;
        }else{//if current player is number 2
            if(isAtPlayer2Position()){
                transition=true;
            } else if(isAt_LeftSideView_Position()){
                transition=true;
                rotate_clockwise = true;
            } else if(isAt_RightSideView_Position()){
                transition=true;
                rotate_clockwise = false;
            }
            next_position=0;
            player=1;
        }
    }
    
    public static void updateTransition() {
        if(next_position==0 && isAtPlayer1Position()){
            transition=false;
            current_position=0;
            return;
        }
        if(next_position==1 && isAt_RightSideView_Position()){
            transition=false;
            current_position=1;
            return;
        }
        if(next_position==2 && isAtPlayer2Position()){
            transition=false;
            current_position=2;
            return;
        }
        if(next_position==3 && isAt_LeftSideView_Position()){
            transition=false;
            current_position=3;
            return;
        }
        if(rotate_clockwise){
            if(next_position!=3 && next_position!=0){
                PosX ++;
                PosZ =(float) (Math.sqrt(-Math.pow(PosX,2)+16*PosX+260)+8);
            } else {
                PosX--;
                PosZ =(float) (-Math.sqrt(-Math.pow(PosX,2)+16*PosX+260)+8);
            }
            
        }else{
            if(next_position!=1 && next_position!=0){
                PosX ++;
                PosZ =(float) (-Math.sqrt(-Math.pow(PosX,2)+16*PosX+260)+8);
            } else {
                PosX--;
                PosZ =(float) (Math.sqrt(-Math.pow(PosX,2)+16*PosX+260)+8);
            }
        }
    }
    public static void loadRightSideView() {
        PosX = RSPosX;
        PosZ = RSPosZ;
    }

    public static void loadLeftSideView() {
        PosX = LSPosX;
        PosZ = LSPosZ;
    }
    
    public static void rotateClockWise(int next_position){
        if(next_position==current_position)return;
        next_position = next_position%4;//numeros de posicao variam somente de 0 a 3;
        Camera.next_position = next_position;
        rotate_clockwise = true;
        transition = true;        
    }
    public static void rotateCounterClockWise(int next_position){
        if(next_position==current_position)return;
        if(next_position==-1)next_position=3;//este passo eh porque nao sabe calcular o mod de numeros negativos
        next_position = next_position%4;//numeros de posicao variam somente de 0 a 3;
        Camera.next_position = next_position;
        rotate_clockwise = false;
        transition = true;        
    }
    
    public static boolean isInTransition(){
        return transition;
    }

    public static int getCurrentPosition(){
        return current_position;
    }

    public static float getPosX() {
        return PosX;
    }

    public static float getPosY() {
        return PosY;
    }

    public static float getPosZ() {
        return PosZ;
    }

    public static float getLookAtPointX() {
        return LookAtPointX;
    }

    public static float getLookAtPointY() {
        return LookAtPointY;
    }

    public static float getLookAtPointZ() {
        return LookAtPointZ;
    }

    public static float getUpX() {
        return UpX;
    }

    public static float getUpY() {
        return UpY;
    }

    public static float getUpZ() {
        return UpZ;
    }


  
}
