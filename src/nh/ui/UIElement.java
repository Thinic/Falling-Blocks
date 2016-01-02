package nh.ui;

import java.awt.Graphics;
import java.util.ArrayList;

/*
 * basic element for gui
 */
public abstract class UIElement
{
    /*
     * positioning
     */
    public static final int       TOP           = 0x00;
    public static final int       BOTTOM        = 0x01;
    public static final int       CENTER_Y      = 0x02;
    public static final int       Y_BITS        = 0x0F;
    
    public static final int       LEFT          = 0x00;
    public static final int       RIGHT         = 0x10;
    public static final int       CENTER_X      = 0x20;
    public static final int       X_BITS        = 0xF0;
    
    public static final int       TOP_LEFT      = TOP | LEFT;
    public static final int       TOP_RIGHT     = TOP | RIGHT;
    public static final int       TOP_CENTER    = TOP | CENTER_X;
    public static final int       BOTTOM_LEFT   = BOTTOM | LEFT;
    public static final int       BOTTOM_RIGHT  = BOTTOM | RIGHT;
    public static final int       BOTTOM_CENTER = BOTTOM | CENTER_X;
    public static final int       CENTER        = CENTER_Y | CENTER_X;
    
    public static final int       DEFAULT       = TOP_LEFT;
    
    /*
     * actual position (top-left)
     */
    private int x, y, w, h;
    
    /*
     * positioning that was assigned
     */
    private int ox, oy, oType;
    
    private UIElement parent;
    private ArrayList<UIElement> children;
    
    private String text;
    private int fontSize = 20;
    
    private boolean visible = true, maxWidth, maxHeight;
    
    private ArrayList<UIActionListener> listeners;
    
    public UIElement() 
    {
        children = new ArrayList<>();
        
        listeners = new ArrayList<>();
        
        setParent(null);
    }
    
    protected void performAction() 
    {
        UIActionEvent e = new UIActionEvent(this);
        
        for (UIActionListener al : listeners) 
        {
            al.actionPerformed(e);
        }
    }
    
    public void addUIActionListener(UIActionListener e) 
    {
        listeners.add(e);
    }
    
    public void setMaximized(boolean m) 
    {
        maxWidth = m;
        maxHeight = m;
    }
    
    public boolean isMaximized() 
    {
        return maxWidth && maxHeight;
    }
    
    public void setMaximizedVertical(boolean m) 
    {
        maxWidth = m;
    }
    
    public void setMaximizedHorizontal(boolean m) 
    {
        maxHeight = m;
    }
    
    public boolean isMaximizedVertical() 
    {
        return maxWidth;
    }
    
    public boolean isMaximizedHorizontal() 
    {
        return maxHeight;
    }
    
    /*
     * updating and rendering
     */
    public void draw(Graphics g) 
    {
        if (!isVisible()) return;
        
        drawElement(g);
        
        for (UIElement c : children) c.draw(g);
    }
    
    public void drawOverlay(Graphics g) 
    {
        if (!isVisible()) return;
        
        drawElementOverlay(g);
        
        for (UIElement c : children) c.drawOverlay(g);
    }
    
    /*
     * override these for drawing
     */
    public abstract void drawElement(Graphics g);
    
    public abstract void drawElementOverlay(Graphics g);
    
    public boolean isVisible() 
    {
        return visible;
    }
    
    public void setVisible(boolean v) 
    {
        visible = v;
    }
    
    public void show() 
    {
        setVisible(true);
    }
    
    public void hide() 
    {
        setVisible(false);
    }
    
    public String getText() 
    {
        return text;
    }
    
    public void setText(String t) 
    {
        text = t;
    }
    
    public int getFontSize() 
    {
        return fontSize;
    }
    
    public void setFontSize(int sz) 
    {
        fontSize = sz;
    }
    
    public void updateLocation() 
    {
        if (!hasParent()) 
        {
            x = 0;
            y = 0;
        }
        else 
        {
            int offX = getOffsetX();
            int offY = getOffsetY();
            
            int pWidth = getParent().getWidth();
            int pHeight = getParent().getHeight();
            
            switch (getOffsetType() & X_BITS) 
            {
                case RIGHT: 
                    x = pWidth - getWidth() - offX;
                    break;
                case CENTER_X:
                    x = pWidth / 2 - getWidth() / 2 + offX;
                    break;
                case LEFT: default:
                    x = offX;
                    break;
            }
            
            if (isMaximizedHorizontal()) 
            {
                x = 0;
                w = pWidth;
            }
            
            switch (getOffsetType() & Y_BITS) 
            {
                case BOTTOM:
                    y = pHeight - getHeight() - offY;
                    break;
                case CENTER_Y:
                    y = pHeight / 2 - getHeight() / 2 + offY;
                    break;
                case TOP: default:
                    y = offY;
                    break;
            }     
            
            if (isMaximizedVertical()) 
            {
                y = 0;
                h = pHeight;
            }
        }
        
        for (UIElement c : children) c.updateLocation();
    }
    
    /*
     * parent util
     */
    public void setParent(UIElement e) 
    {
        parent = e;
        
        updateLocation();
    }
    
    public UIElement getParent() 
    {
        return parent;
    }
    
    public boolean hasParent() 
    {
        return parent != null;
    }
    
    /*
     * child util
     */
    public void add(UIElement e) 
    {
        e.setParent(this);
        
        children.add(e);
    }
    
    public void remove(UIElement e) 
    {
        e.setParent(null);
        
        children.remove(e);
    }
    
    public boolean contains(UIElement e) 
    {
        return children.contains(e);
    }
    
    /*
     * dimension util
     */
    public int getWidth() 
    {
        return w;
    }
    
    public int getHeight() 
    {
        return h;
    }
    
    public void setWidth(int w) 
    {
        this.w = w;
        
        updateLocation();
        
        onResize();
    }
    
    public void setHeight(int h) 
    {
        this.h = h;
        
        updateLocation();
        
        onResize();
    }
    
    public void setSize(int w, int h) 
    {
        this.w = w;
        this.h = h;
        
        updateLocation();
        
        onResize();
    }
    
    public abstract void onResize();
    
    /*
     * positioning util
     */
    public void setOffset(int x, int y) 
    {
        ox = x;
        oy = y;
        
        updateLocation();
    }
    
    public void setOffsetType(int type) 
    {
        oType = type;
        
        updateLocation();
    }
    
    public int getOffsetX() 
    {
        return ox;
    }
    
    public int getOffsetY() 
    {
        return oy;
    }
    
    public int getOffsetType() 
    {
        return oType;
    }
    
    public int getAbsX() 
    {
        if (!hasParent()) return x;
        
        return x + getParent().getAbsX();
    }
    
    public int getAbsCenterX() 
    {
        return getAbsX() + getWidth()/2;
    }
    
    public int getAbsY() 
    {
        if (!hasParent()) return y;
        
        return y + getParent().getAbsY();
    }
    
    public int getAbsCenterY() 
    {
        return getAbsY() + getHeight()/2;
    }
    
    /*
     * collision
     */
    public boolean inBounds(int px, int py) 
    {
        int x = getAbsX();
        int y = getAbsY();
        
        return px >= x &&
               px <= x + w &&
               py >= y &&
               py <= y + h;
    }
    
    /*
     * event
     */
    public void onEvent(UIEvent e) 
    {
        if (!isVisible()) return;
        
        e.perform(this);
        
        for (UIElement c : children) c.onEvent(e);
    }
    
    public abstract void onMouseMove(int x, int y);
    
    public abstract void onMousePress(int x, int y, int button);
    
    public abstract void onMouseRelease(int x, int y, int button);
    
    public abstract void onKeyPress(int key);
    
    public abstract void onKeyRelease(int key);
}
