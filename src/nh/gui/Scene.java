package nh.gui;


public class Scene
{
    private Element root;
    private int x, y;
    
    public Scene() 
    {
        root = new Panel();
    }
    
    public synchronized Element getRoot() 
    {
        return root;
    }
    
    public void setSize(int width, int height) 
    {
        getRoot().setSize(width, height);
    }
    
    public void add(Element e) 
    {
        getRoot().add(e);
    }
    
    public void remove(Element e) 
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
        
        getRoot().onEvent(new Event() {
            @Override
            public void perform(Element e)
            {
                e.onMouseMove(x, y);
            }
        });
    }
    
    public void mousePressed(final int button) 
    {
        getRoot().onEvent(new Event() {
            @Override
            public void perform(Element e)
            {
                e.onMousePress(x, y, button);
            }
        });
    }
    
    public void mouseReleased(final int button) 
    {
        getRoot().onEvent(new Event() {
            @Override
            public void perform(Element e)
            {
                e.onMouseRelease(x, y, button);
            }
        });
    }
    
    public void keyPressed(final int key) 
    {
        getRoot().onEvent(new Event() {
            @Override
            public void perform(Element e)
            {
                e.onKeyPress(key);
            }
        });
    }
    
    public void keyReleased(final int key) 
    {
        getRoot().onEvent(new Event() {
            @Override
            public void perform(Element e)
            {
                e.onKeyRelease(key);
            }
        });
    }
}
