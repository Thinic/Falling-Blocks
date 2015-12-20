package nh.fb;

import nh.core.GameState;

public class Player
{
    private PlayerSettings settings;
    
    private int leftTime, rightTime, downTime;
    
    public Player(PlayerSettings settings) 
    {
        this.settings = settings;
    }
    
    public PlayerSettings getSettings() { return settings; }
    
    public void update(GameState input, FallingBlocks game) 
    {
        int waitTime = 8;
        int dropWaitTime = 4;
        
        /*
         * player input
         */
        if (input.isKeyJustDown(settings.getRotCWKey()) || input.isKeyJustDown(settings.getSecRotCWKey())) 
        {
            game.rotatePieceCW();
        }
        
        if (input.isKeyJustDown(settings.getRotCCWKey())) 
        {
            game.rotatePieceCCW();
        }
        
        if (input.isKeyDown(settings.getLeftKey())) 
        {
            leftTime++;
        }
        else 
        {
            leftTime = 0;
        }
        
        if ((leftTime - 1) % waitTime == 0) 
        {
            game.movePieceLeft();
        }
        
        if (input.isKeyDown(settings.getRightKey())) 
        {
            rightTime++;
        }
        else 
        {
            rightTime = 0;
        }
        
        if ((rightTime - 1) % waitTime == 0) 
        {
            game.movePieceRight();
        }
        
        if (input.isKeyDown(settings.getDownKey())) 
        {
            downTime++;
        }
        else 
        {
            downTime = 0;
        }
        
        if ((downTime - 1) % dropWaitTime == 0) 
        {
            boolean dropped = game.movePieceDown();
            game.resetFallTime();
            
            if (!dropped) 
            {
                game.lockAndGetNextPiece();
            }
        }
        
        if (input.isKeyJustDown(settings.getHardDropKey())) 
        {
            game.hardDropPiece();
        }
    }
}
