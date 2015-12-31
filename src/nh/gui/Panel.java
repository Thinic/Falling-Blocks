package nh.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Panel extends Element
{
    private Color background = Color.GRAY;
    
    public Panel() 
    {
        setBackground(0xCFCFCF);
    }
    
    public void setBackground(int col) 
    {
        background = new Color(col);
    }
    
    @Override
    public void drawElement(Graphics g) 
    {
        g.setColor(background);
        
        g.fillRect(getAbsX(), getAbsY(), getWidth(), getHeight());
    }

    @Override
    public void drawElementOverlay(Graphics g)
    {
        
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        
    }

    @Override
    public void onMousePress(int x, int y, int button)
    {
        
    }

    @Override
    public void onMouseRelease(int x, int y, int button)
    {
        
    }

    @Override
    public void onKeyPress(int key)
    {
        
    }

    @Override
    public void onKeyRelease(int key)
    {
        
    }

    @Override
    public void onResize()
    {
        // TODO Auto-generated method stub
        
    }
}

