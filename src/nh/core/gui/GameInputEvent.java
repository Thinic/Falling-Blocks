package nh.core.gui;


/*
 * Events for mouse movement, clicking, key presses, etc.
 */
public class GameInputEvent
{
    private GameInput input;
    
    public GameInputEvent(GameInput input) 
    {
        this.input = input;
    }
    
    /*
     * can use to view anything about input
     */
    public GameInput getGameInput() 
    {
        return input;
    }
}
