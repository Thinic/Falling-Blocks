package nh.core;

public class Keyboard
{
    public static final int SIZE = 1024;
    
    public static final int RELEASED = 0;
    public static final int JUST_PRESSED = 1;
    
    private int[] keys;
    
    public Keyboard() 
    {
        keys = new int[SIZE];
    }
    
    public void update() 
    {
        for (int i = 0; i < keys.length; i++) 
        {
            if (keys[i] > RELEASED) keys[i]++;
        }
    }
    
    public void keyPressed(int key) 
    {
        if (keys[key] == RELEASED) keys[key] = JUST_PRESSED;
    }
    
    public void keyReleased(int key) 
    {
        keys[key] = RELEASED;
    }
    
    public boolean isKeyDown(int key) 
    {
        return keys[key] != RELEASED;
    }
    
    public boolean isKeyJustDown(int key) 
    {
        return keys[key] == JUST_PRESSED;
    }
    
    public int getKeyDownTime(int key) 
    {
        return keys[key];
    }
}
