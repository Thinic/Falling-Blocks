package nh.core.gui;

public class GuiBounds
{
    private int x, y, w, h;
    
    public GuiBounds(int x, int y, int w, int h) 
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    
    public int getWidth() { return w; }
    public int getHeight() { return h; }
    
    public void setWidth(int w) { this.w = w; }
    public void setHeight(int h) { this.h = h; }
    
    public boolean inBounds(int px, int py) 
    {
        return px >= x && px <= x+w && py >= y && py <= y+h;
    }
}
