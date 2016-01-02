package nh.ui;


public class Scene
{
    private UIElement root;
    private int x, y;
    
    public Scene() 
    {
        root = new UIPanel();
    }
    
    public Scene(UIElement e) 
    {
        root = e;
    }
    
    public synchronized UIElement getRoot() 
    {
        return root;
    }
    
    public void setSize(int width, int height) 
    {
        getRoot().setSize(width, height);
    }
    
    public void add(UIElement e) 
    {
        getRoot().add(e);
    }
    
    public void remove(UIElement e) 
    {
        getRoot().remove(e);
    }
    
    /*
     * TODO finish below
     */
    public void mouseMoved(final int x, final int y) 
    {
        this.x = x;
        this.y = y;
        
        getRoot().onEvent(new UIEvent() {
            @Override
            public void perform(UIElement e)
            {
                e.onMouseMove(x, y);
            }
        });
    }
    
    public void mousePressed(final int button) 
    {
        getRoot().onEvent(new UIEvent() {
            @Override
            public void perform(UIElement e)
            {
                e.onMousePress(x, y, button);
            }
        });
    }
    
    public void mouseReleased(final int button) 
    {
        getRoot().onEvent(new UIEvent() {
            @Override
            public void perform(UIElement e)
            {
                e.onMouseRelease(x, y, button);
            }
        });
    }
    
    public void keyPressed(final int key) 
    {
        getRoot().onEvent(new UIEvent() {
            @Override
            public void perform(UIElement e)
            {
                e.onKeyPress(key);
            }
        });
    }
    
    public void keyReleased(final int key) 
    {
        getRoot().onEvent(new UIEvent() {
            @Override
            public void perform(UIElement e)
            {
                e.onKeyRelease(key);
            }
        });
    }
}
