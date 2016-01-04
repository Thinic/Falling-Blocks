package nh.ui;

import java.awt.Color;
import java.awt.Graphics;

public class UIPanel extends UIElement
{
    private Color background = Color.GRAY;
    
    public UIPanel() 
    {
        setBackground(0xCFCFCF);
    }
    
    public void setBackground(int col) 
    {
        background = new Color(col);
    }
    
    public int getBackground() 
    {
        return background.getRGB();
    }
    
    @Override
    public void drawElement(Graphics g) 
    {
        g.setColor(background);
        
        g.fillRect(0, 0, getWidth(), getHeight());
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

