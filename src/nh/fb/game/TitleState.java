package nh.fb.game;

import java.awt.Color;
import java.awt.Graphics;

import nh.core.GameState;
import nh.ui.SceneUtil;
import nh.ui.UIActionEvent;
import nh.ui.UIActionListener;
import nh.ui.UIButton;
import nh.ui.UIElement;

public class TitleState extends GameState implements UIActionListener
{
    private static final Color back = new Color(0xCCEEFF);
    
    private UIButton exitBtn, optionsBtn, helpBtn, duelBtn, playBtn;
    
    public TitleState(Game game)
    {
        super(game);
        
        SceneUtil util = getSceneUtil();
        
        util.offsetType = UIElement.BOTTOM_CENTER;
        
        exitBtn    = util.createButton("Exit", this);
        optionsBtn = util.createButton("Options", this);
        helpBtn    = util.createButton("Tutorial", this);
        duelBtn    = util.createButton("2 Player", this);
        playBtn    = util.createButton("1 Player", this);
        
        util.offsetType = UIElement.TOP | UIElement.CENTER_X;
        util.paddingY = 100;
        util.reset();

        util.createLabel("Falling Blocks").setFontSize(60);
    }

    @Override
    public void reset()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.setColor(back);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void actionPerformed(UIActionEvent e)
    {
        UIElement source = e.getSource();
        
        if (source == exitBtn) 
        {
            getGame().stop();
        }
        else if (source == playBtn) 
        {
            changeGameState(Game.STATE_PLAY);
        }
        else if (source == duelBtn) 
        {
            changeGameState(Game.STATE_DUEL);
        }
        else if (source == optionsBtn) 
        {
            changeGameState(Game.STATE_OPTIONS);
        }
        else if (source == helpBtn) 
        {
            changeGameState(Game.STATE_HELP);
        }
    }
}
