package nh.fb.game;

import java.awt.Graphics2D;

import nh.core.GameAction;
import nh.core.GameState;
import nh.core.StateBasedGame;
import nh.core.gui.GameButton;
import nh.core.gui.GameLabel;

public class TitleState extends GameState
{
    private GameButton exitButton, playButton, duelButton, optButton, helpButton;
    private GameLabel title;
    
    public TitleState(StateBasedGame game) 
    {
        super(game);
        
        title = createLabel("Falling Blocks");
        
        exitButton = createButton("Exit");
        exitButton.setAction(new GameAction() {
            public void performAction() {
                getGame().stop();
            }
        });
        
        playButton = createButton("1 Player");
        playButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_PLAY);
            }
        });
        
        duelButton = createButton("2 Player");
        duelButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_DUEL);
            }
        });
        
        optButton = createButton("Options");
        optButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_OPTIONS);
            }
        });
        
        helpButton = createButton("Help");
        helpButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_HELP);
            }
        });
    }
    
    @Override
    public void update()
    {
        
    }

    @Override
    public void draw(Graphics2D g)
    {
        setButtonLocation(exitButton, 0);
        setButtonLocation(playButton, 4);
        setButtonLocation(duelButton, 3);
        setButtonLocation(optButton, 2);
        setButtonLocation(helpButton, 1);
        setLabelLocation(title, 1);
    }
    
    private void setButtonLocation(GameButton button, int yOrder) 
    {
        button.setLocation(getWidth()/2 - button.getWidth()/2, getHeight() - (button.getHeight() + 10)*(1 + yOrder));
    }
    
    private GameButton createButton(String text) 
    {
        GameButton btn = new GameButton(text);
        btn.setSize(200, 40);
        
        add(btn);
        
        return btn;
    }
    
    private GameLabel createLabel(String text) 
    {
        GameLabel lbl = new GameLabel(text);
        lbl.setSize(300, 70);
        
        add(lbl);
        
        return lbl;
    }
    
    private void setLabelLocation(GameLabel lbl, int yOrder) 
    {
        lbl.setLocation(getWidth()/2 - lbl.getWidth()/2, (lbl.getHeight() + 10)*yOrder);
    }

    @Override
    public void reset()
    {
        exitButton.setMouseOver(false);
        playButton.setMouseOver(false);
        duelButton.setMouseOver(false);
        optButton.setMouseOver(false);
        helpButton.setMouseOver(false);
    }
}
