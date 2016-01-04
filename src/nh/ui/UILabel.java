package nh.ui;

import java.awt.Graphics;

public class UILabel extends UIElement
{
    public UILabel() 
    {
        super();
    }
    
    @Override
    public void drawElement(Graphics g)
    {
        TextRenderer tr = new TextRenderer();
        tr.setContext(g);
        tr.textSize = getFontSize();
        
        tr.draw(getText(), getWidth()/2, getHeight()/2);
    }
    
    @Override
    public void drawElementOverlay(Graphics g)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onResize()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onMouseMove(int x, int y)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onMousePress(int x, int y, int button)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onMouseRelease(int x, int y, int button)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onKeyPress(int key)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onKeyRelease(int key)
    {
        // TODO Auto-generated method stub
        
    }
    
}
