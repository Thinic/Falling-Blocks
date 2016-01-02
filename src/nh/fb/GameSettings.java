package nh.fb;

public class GameSettings
{
    public GameSettings() 
    {
        
    }
    
    public int getBoardWidth() { return 10; }
    
    public int getBoardHeight() { return 22; }
    
    public int getTicksPerSecond() { return 100; }
    
    public int getLockWaitTicks() { return 80; }

    public int getInitialFallWaitTicks() { return 120; }
}
