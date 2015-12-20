package nh.fb.game;

import java.awt.Graphics2D;

import nh.core.GameAction;
import nh.core.GameState;
import nh.core.StateBasedGame;
import nh.core.gui.GameButton;
import nh.core.gui.GameLabel;

public class EmptyState extends GameState
{
    private GameButton returnButton;
    private GameLabel emptyTextLabel;
    
    public EmptyState(StateBasedGame game)
    {
        super(game);
        
        returnButton = createButton("Return to Menu");
        returnButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_TITLE);
            }
        });
        
        emptyTextLabel = createLabel("This feature is not yet available.");
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        setButtonLocation(returnButton, 0);
        
        setLabelLocation(emptyTextLabel, 2);
    }
    
    private void setButtonLocation(GameButton button, int yOrder) 
    {
        button.setLocation(getWidth()/2 - button.getWidth()/2, getHeight() - (button.getHeight() + 10)*(1 + yOrder));
    }
    
    private void setLabelLocation(GameLabel lbl, int yOrder) 
    {
        lbl.setLocation(getWidth()/2 - lbl.getWidth()/2, (lbl.getHeight() + 10)*yOrder);
    }
    
    private GameButton createButton(String text) 
    {
        GameButton btn = new GameButton(text);
        btn.setSize(300, 40);
        
        add(btn);
        
        return btn;
    }
    
    private GameLabel createLabel(String text) 
    {
        GameLabel lbl = new GameLabel(text);
        lbl.setSize(300, 40);
        
        add(lbl);
        
        return lbl;
    }

    @Override
    public void reset()
    {
        returnButton.setMouseOver(false);
    }
}
