package nh.fb;

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
    
    /*
     * player dependent game settings
     */
    public int getLineClearWait() { return lineClearWait; }
    
    /*
     * set game settings
     */
    public void setLineClearWait(int wait) { lineClearWait = wait; }
}
