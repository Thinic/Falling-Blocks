package nh.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Counter extends ActionElement
{
    private Action increment, decrement;
    
    private Button incBtn, decBtn;
    
    private volatile int value, min, max;
    
    public Counter() 
    {
        super();
        
        final Counter counter = this;
        
        increment = new Action() {
            public void performAction() {
                value++;
                
                if (value > max) value = max;
                
                counter.performAction();
            }
        };
        
        decrement = new Action() {
            public void performAction() {
                value--;
                
                if (value < min) value = min;
                
                counter.performAction();
            }
        };
        
        incBtn = new Button();
        incBtn.setAction(increment);
        incBtn.setText("+");
        incBtn.setOffset(0, 0);
        incBtn.setOffsetType(CENTER_Y | RIGHT);
        add(incBtn);
        
        decBtn = new Button();
        decBtn.setAction(decrement);
        decBtn.setText("-");
        decBtn.setOffset(0, 0);
        decBtn.setOffsetType(CENTER_Y | LEFT);
        add(decBtn);
        
        setBounds(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public int getValue() 
    {
        return value;
    }
    
    public void setValue(int v) 
    {
        value = v;
    }
    
    public void setBounds(int min, int max) 
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public void drawElement(Graphics g)
    {
        Color back = new Color(0xbbbbbb);
        g.setColor(back);
        g.fillRect(getAbsX(), getAbsY(), getWidth(), getHeight());
        
        TextRenderer tr = new TextRenderer();
        tr.setContext(g);
        tr.textSize = 20;
        
        tr.draw("" + value, getAbsX() + getWidth()/2, getAbsY() + getHeight()/2);
    }

    @Override
    public void drawElementOverlay(Graphics g)
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

    @Override
    public void onResize()
    {
        incBtn.setSize(getHeight(), getHeight());
        decBtn.setSize(getHeight(), getHeight());
    }
}
