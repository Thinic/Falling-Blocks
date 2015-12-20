package nh.core.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/*
 * Draws screen for a GameWindow
 */
public class GameCanvas
{
    private int width, height;
    
    private GameInput input;
    
    private List<GameComponent> components;
    
    private Color background;
    
    public GameCanvas() 
    {
        input = new GameInput();
        
        setColor(0xAADDFF);
        
        components = new ArrayList<GameComponent>();
    }
    
    public void setSize(int w, int h) 
    {
        width = w;
        height = h;
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void setColor(int c) 
    {
        background = new Color(c);
    }
    
    /*
     * get input from canvas
     */
    public GameInput getGameInput() 
    {
        return input;
    }
    
    public void update() 
    {
        input.update();
        
        for (GameComponent gc : components) 
        {
            gc.update();
        }
    }
    
    public void draw(Graphics2D g) 
    {
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        drawBackground(g);
        
        for (GameComponent gc : components) 
        {
            gc.draw(g);
        }
    }
    
    /*
     * used for rendering game
     */
    public void drawBackground(Graphics2D g) 
    {
        
    }
    
    public void add(GameComponent gc) 
    {
        components.add(gc);
    }
    
    public void remove(GameComponent gc) 
    {
        components.remove(gc);
    }

    public void mouseMoved(int x, int y)
    {
        input.moveMouse(x, y);
        
        GameInputEvent e = new GameInputEvent(input);
        
        for (GameComponent gc : components) 
        {
            gc.mouseMoved(e);
        }
    }
    
    public void mousePressed(int code)
    {
        input.useMouseButton(code, true);
        
        GameInputEvent e = new GameInputEvent(input);
        
        for (GameComponent gc : components) 
        {
            gc.mousePressed(e);
        }
    }
    
    public void mouseReleased(int code)
    {
        input.useMouseButton(code, false);
        
        GameInputEvent e = new GameInputEvent(input);
        
        for (GameComponent gc : components) 
        {
            gc.mouseReleased(e);
        }
    }
    
    public void keyPressed(int code)
    {
        input.useKey(code, true);
        
        GameInputEvent e = new GameInputEvent(input);
        
        for (GameComponent gc : components) 
        {
            gc.keyPressed(e);
        }
    }
    
    public void keyReleased(int code)
    {
        input.useKey(code, false);
        
        GameInputEvent e = new GameInputEvent(input);
        
        for (GameComponent gc : components) 
        {
            gc.keyReleased(e);
        }
    }
}
