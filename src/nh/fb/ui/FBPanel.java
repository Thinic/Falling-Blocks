package nh.fb.ui;

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
    public void drawElement(Graphics g) 
    {
        super.drawElement(g);
        
        int size = getHeight() - 60;
        
        size /= game.getBoard().getHeight();
        
        IBoardRenderer br = new BasicBoardRenderer();
        
        int width = game.getBoard().getWidth() * size;
        
        setWidth(width);// + 100);
        
        br.draw((Graphics2D)g, 
                game.getBoard(), 
                game.getPiece(), 
                game.getGhostPiece(), 
                getWidth()/2 - width/2, 
                10, 
                size);
        
    }
}
