/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author Lobosque
 */
public class Square {

    private int x;
    private int y;
    
    public Square(String pos) {
        this.x = (int)pos.charAt(0) - 97; 
        if(this.x < 0) x = 0;
        else if(this.x > 7) x = 7;
        this.y = (int)pos.charAt(1) - 49;
        if(this.y < 0) y = 0;
        else if(this.y > 7) y = 7;
    }
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public String getPos() {
        char x = (char)(97 + this.x);
        char y = (char)(49 + this.y);
        return String.valueOf(x) + String.valueOf(y);
    }
}
