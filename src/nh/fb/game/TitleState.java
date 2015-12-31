package nh.fb.game;

import java.awt.Color;
import java.awt.Graphics;

import nh.core.GameState;
import nh.gui.Element;
import nh.gui.SceneUtil;

public class TitleState extends GameState
{
    private static final Color back = new Color(0xCCEEFF);
    
    public TitleState(Game game)
    {
        super(game);
        
        SceneUtil util = getSceneUtil();
        
        util.offsetType = Element.BOTTOM_CENTER;
        
        util.createButton("Exit", ActionUtil.exitAction(getGame()));
        util.createButton("Options", ActionUtil.changeState(getGame(), Game.STATE_OPTIONS));
        util.createButton("Tutorial", ActionUtil.changeState(getGame(), Game.STATE_HELP));
        util.createButton("2 Player", ActionUtil.changeState(getGame(), Game.STATE_DUEL));
        util.createButton("1 Player", ActionUtil.changeState(getGame(), Game.STATE_PLAY));
        
        util.offsetType = Element.TOP | Element.CENTER_X;
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
    
}
