package nh.fb.game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import nh.fb.FallingBlocksGame;
import nh.fb.gfx.BasicBoardRenderer;
import nh.fb.gfx.IBoardRenderer;
import nh.ui.UIPanel;

public class FBPanel extends UIPanel
{
    private FallingBlocksGame game;
    
    public FBPanel(FallingBlocksGame game) 
    {
        super();
        
        setGame(game);
    }
    
    public void setGame(FallingBlocksGame game) 
    {
        this.game = game;
    }
    
    @Override
    public void draw(Graphics g) 
    {
        super.draw(g);
        
        int size = getHeight() - 60;
        
        size /= game.getBoard().getHeight();
        
        IBoardRenderer br = new BasicBoardRenderer();
        
        int width = game.getBoard().getWidth() * size;
        
        setWidth(width);
        
        br.draw((Graphics2D)g, 
                game.getBoard(), 
                game.getPiece(), 
                game.getGhostPiece(), 
                getAbsCenterX() - width/2, 
                10, 
                size);
    }
}
