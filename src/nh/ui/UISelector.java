package nh.ui;

import java.awt.Color;
import java.awt.Graphics;

public class UISelector extends UIElement
{
    private UIButton incBtn, decBtn;
    
    private String[] values;
    
    private int value;
    
    public UISelector() 
    {
        super();
        
        final UISelector counter = this;
        
        incBtn = new UIButton();
        decBtn = new UIButton();
        
        UIActionListener al = new UIActionListener() {
            public void actionPerformed(UIActionEvent e) {
                UIElement s = e.getSource();
                
                if (s == incBtn) 
                {
                    value++;
                    
                    if (value >= values.length) value = values.length - 1;
                    
                    counter.performAction();
                }
                else if (s == decBtn) 
                {
                    value--;
                    
                    if (value < 0) value = 0;
                    
                    counter.performAction();
                }
            }
        };
        
        incBtn.addUIActionListener(al);
        incBtn.setText("+");
        incBtn.setOffset(0, 0);
        incBtn.setOffsetType(CENTER_Y | RIGHT);
        add(incBtn);
        
        decBtn.addUIActionListener(al);
        decBtn.setText("-");
        decBtn.setOffset(0, 0);
        decBtn.setOffsetType(CENTER_Y | LEFT);
        add(decBtn);
    }
    
    public int getValue() 
    {
        return value;
    }
    
    public String getValueText() 
    {
        if (values == null || value < 0 && value >= values.length) return "N/A";
        else return values[value];
    }
    
    public void setValue(int v) 
    {
        value = v;
    }
    
    public void setSelections(String[] values) 
    {
        this.values = values;
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
        
        String text = getValueText();
        
        tr.draw(text, getAbsX() + getWidth()/2, getAbsY() + getHeight()/2);
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
