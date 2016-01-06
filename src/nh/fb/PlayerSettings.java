package nh.fb;

import java.awt.event.KeyEvent;

/*
 * Player dependent settings
 */
public class PlayerSettings
{
    private int leftKey;
    private int rightKey;
    private int downKey;
    private int rotCWKey;
    private int rotCCWKey;
    private int rotCW2Key;
    private int hardDropKey;
    
    private int lineClearWait;
    
    private int keyHoldWait;
    private int keyHoldRepeat;
    
    private int holdKey;
    
    public PlayerSettings() 
    {
        
    }
    
    /*
     * get key controls
     */
    public int getLeftKey() { return leftKey; }
    public int getRightKey() { return rightKey; }
    public int getDownKey() { return downKey; }
    public int getRotCWKey() { return rotCWKey; }
    public int getSecRotCWKey() { return rotCW2Key; }
    public int getRotCCWKey() { return rotCCWKey; }
    public int getHardDropKey() { return hardDropKey; }
    public int getHoldKey() { return holdKey; }
    
    /*
     * set key controls
     */
    public void setLeftKey(int k) { leftKey = k; }
    public void setRightKey(int k) { rightKey = k; }
    public void setDownKey(int k) { downKey = k; }
    public void setRotCWKey(int k) { rotCWKey = k; }
    public void setSecRotCWKey(int k) { rotCW2Key = k; }
    public void setRotCCWKey(int k) { rotCCWKey = k; }
    public void setHardDropKey(int k) { hardDropKey = k; }
    public void setHoldKey(int k) { holdKey = k; }
    
    /*
     * how long before key repeats, and how often to repeat after that
     */
    public void setKeyWait(int wait) { keyHoldWait = wait; }
    public void setKeyRepeat(int wait) { keyHoldRepeat = wait; }
    
    public int getKeyWait() { return keyHoldWait; }
    public int getKeyRepeat() { return keyHoldRepeat; }
    
    /*
     * player dependent game settings
     */
    public int getLineClearWait() { return lineClearWait; }
    
    public int getPauseKey() { return KeyEvent.VK_P; }
    
    /*
     * set game settings
     */
    public void setLineClearWait(int wait) { lineClearWait = wait; }
}
