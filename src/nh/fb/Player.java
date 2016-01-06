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
        
        if (input.isKeyJustDown(settings.getPauseKey())) game.setPaused(!game.isPaused());
        
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
        
        if (input.isKeyJustDown(settings.getHoldKey())) 
        {
            game.holdPiece();
        }
    }
}
