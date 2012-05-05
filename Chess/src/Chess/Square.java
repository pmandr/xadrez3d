/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;

/**
 *
 * @author Lobosque
 */
public class Square {

    private int x;
    private int y;
    
    public Square(String pos) throws InvalidSquareException {
        int x = (int)pos.charAt(0) - 97; 
        int y = (int)pos.charAt(1) - 49;
        if(x < 0 || x > 7) throw new InvalidSquareException();
        if(y < 0 || y > 7) throw new InvalidSquareException();
        this.x = x; 
        this.y = y;
    }
    
    public Square(int x, int y) throws InvalidSquareException {
        if(x < 0 || x > 7) throw new InvalidSquareException();
        if(y < 0 || y > 7) throw new InvalidSquareException();
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) throws InvalidSquareException {
        if(x < 0 || x > 7) throw new InvalidSquareException();
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws InvalidSquareException {
        if(y < 0 || y > 7) throw new InvalidSquareException();
        this.y = y;
    }
    
    public String getPos() {
        char x = (char)(97 + this.x);
        char y = (char)(49 + this.y);
        return String.valueOf(x) + String.valueOf(y);
    }
}
