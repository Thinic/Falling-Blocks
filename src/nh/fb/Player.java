package nh.fb;

import nh.core.Keyboard;

public class Player
{
    private PlayerSettings settings;
    
    public Player(PlayerSettings settings) 
    {
        this.settings = settings;
    }
    
    public PlayerSettings getSettings() { return settings; }
    
    private boolean isKeyPressValid(Keyboard input, int key) 
    {
        int down = input.getKeyDownTime(key);
        int wait = settings.getKeyWait();
        
        return input.isKeyJustDown(key) || down == wait ||
               (down > wait && (down - wait) % settings.getKeyRepeat() == 0);
    }
    
    public void update(Keyboard input, FallingBlocksGame game) 
    {
        if (isKeyPressValid(input, settings.getLeftKey())) 
        {
            game.moveLeft();
        }
        
        if (isKeyPressValid(input, settings.getRightKey())) 
        {
            game.moveRight();
        }
        
        if (input.isKeyJustDown(settings.getRotCWKey()) || input.isKeyJustDown(settings.getSecRotCWKey())) 
        {
            game.rotateCW();
        }
        
        if (input.isKeyJustDown(settings.getRotCCWKey())) 
        {
            game.rotateCCW();
        }
        
        if (input.isKeyJustDown(settings.getHardDropKey())) 
        {
            game.hardDrop();
        }
        
        if (isKeyPressValid(input, settings.getDownKey())) 
        {
            game.moveDown();
        }
        
        /*
         * player input
         */
//        if (input.isKeyJustDown(settings.getRotCWKey())) 
//        {
//            game.rotatePieceCW();
//        }
//        
//        if (input.isKeyJustDown(settings.getRotCCWKey())) 
//        {
//            game.rotatePieceCCW();
//        }
//        
//        if (input.isKeyDown(settings.getLeftKey())) 
//        {
//            leftTime++;
//        }
//        else 
//        {
//            leftTime = 0;
//        }
//        
//        if ((leftTime - 1) % waitTime == 0) 
//        {
//            game.movePieceLeft();
//        }
//        
//        if (input.isKeyDown(settings.getRightKey())) 
//        {
//            rightTime++;
//        }
//        else 
//        {
//            rightTime = 0;
//        }
//        
//        if ((rightTime - 1) % waitTime == 0) 
//        {
//            game.movePieceRight();
//        }
//        
//        if (input.isKeyDown(settings.getDownKey())) 
//        {
//            downTime++;
//        }
//        else 
//        {
//            downTime = 0;
//        }
//        
//        if ((downTime - 1) % dropWaitTime == 0) 
//        {
//            boolean dropped = game.movePieceDown();
//            game.resetFallTime();
//            
//            if (!dropped) 
//            {
//                game.lockAndGetNextPiece();
//            }
//        }
//        
//        if (input.isKeyJustDown(settings.getHardDropKey())) 
//        {
//            game.hardDropPiece();
//        }
    }
}
