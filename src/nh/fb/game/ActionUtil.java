package nh.fb.game;

import nh.core.StateBasedGame;
import nh.gui.Action;

public class ActionUtil
{
    public static Action exitAction(final StateBasedGame game) 
    {
        return new Action() {
            public void performAction() {
                game.stop();
            }
        };
    }
    
    public static Action changeState(final StateBasedGame game, final int id) 
    {
        return new Action() {
            public void performAction() {
                game.setGameState(id);
            }
        };
    }
}
