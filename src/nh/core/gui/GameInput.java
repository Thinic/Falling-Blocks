package nh.core.gui;

/*
 * Buffer for keyboard and mouse
 */
public class GameInput
{
    private static final int PRESS = 0;
    private static final int RELEASE = -1;
    
    private int keys[] = new int[1024];
    private int buttons[] = new int[16];
    
    private int mouseX, mouseY;
    
    public GameInput() 
    {
        reset();
    }
    
    public void reset() 
    {
        mouseX = mouseY = 0;
        
        for (int i = 0; i < keys.length; i++) 
        {
            keys[i] = RELEASE;
        }
        
        for (int i = 0; i < buttons.length; i++) 
        {
            buttons[i] = RELEASE;
        }
    }
    
    /*
     * TODO finish
     */
    public void update() 
    {
        for (int i = 0; i < keys.length; i++) 
        {
            if (keys[i] != RELEASE) keys[i]++;
        }
        
        for (int i = 0; i < buttons.length; i++) 
        {
            if (buttons[i] != RELEASE) buttons[i]++;
        }
    }
    
    /*
     * checks if key is first down current frame
     */
    public boolean isKeyJustDown(int code) 
    {
        return getKeyTime(code) == 1;
    }
    
    /*
     * checks how many frames key has been down, first frame is 1
     */
    public int getKeyTime(int code) 
    {
        return keys[code] == RELEASE ? 0 : keys[code];
    }
    
    /*
     * checks if key is down
     */
    public boolean isKeyDown(int code) 
    {
        return getKeyTime(code) > 0;
    }
    
    /*
     * checks if mouse button is down current frame
     */
    public boolean isMouseButtonJustDown(int code) 
    {
        return getMouseButtonTime(code) == 1;
    }
    
    /*
     * checks how many frames mouse button has been down, first frame is 1
     */
    public int getMouseButtonTime(int code) 
    {
        return buttons[code] == RELEASE ? 0 : buttons[code];
    }
    
    /*
     * checks if mouse button is down
     */
    public boolean isMouseButtonDown(int code) 
    {
        return getMouseButtonTime(code) > 0;
    }
    
    public int getMouseX() 
    {
        return mouseX;
    }
    
    public int getMouseY() 
    {
        return mouseY;
    }
    
    /*
     * methods for GameCanvas to use
     */
    public void useKey(int code, boolean press) 
    {
//        System.out.println("use key: " + press);
        
        if (press) 
        {
            if (keys[code] == RELEASE) keys[code] = PRESS;
        }
        else 
        {
            keys[code] = RELEASE;
        }
    }
    
    public void useMouseButton(int code, boolean press) 
    {
        if (press) 
        {
            if (buttons[code] == RELEASE) buttons[code] = PRESS;
        }
        else 
        {
            buttons[code] = RELEASE;
        }
    }
    
    public void moveMouse(int x, int y) 
    {
        mouseX = x;
        mouseY = y;
    }
}
