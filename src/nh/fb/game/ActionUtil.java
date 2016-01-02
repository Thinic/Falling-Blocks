package nh.fb.game;

import nh.core.StateBasedGame;
import nh.ui.UIAction;

public class ActionUtil
{
    public static UIAction exitAction(final StateBasedGame game) 
    {
        return new UIAction() {
            public void performAction() {
                game.stop();
            }
        };
    }
    
    public static UIAction changeState(final StateBasedGame game, final int id) 
    {
        return new UIAction() {
            public void performAction() {
                game.setGameState(id);
            }
        };
    }
}
