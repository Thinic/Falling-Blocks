package nh.core;

import java.awt.Graphics2D;

import nh.core.gui.GameCanvas;
import nh.core.gui.GameComponent;

public abstract class GameState
{
    private GameCanvas canvas;
    private StateBasedGame game;
    
    public GameState(StateBasedGame game) 
    {
        this.game = game;
        
        final GameState state = this;
        
        canvas = new GameCanvas() 
        {
            public void drawBackground(Graphics2D g) 
            {
                state.draw(g);
            }
        };
    }
    
    public abstract void reset();
    
    public abstract void update();
    
    public abstract void draw(Graphics2D g);
    
    public boolean isKeyDown(int code) 
    {
        return canvas.getGameInput().isKeyDown(code);
    }
    
    public boolean isKeyJustDown(int code) 
    {
        return canvas.getGameInput().isKeyJustDown(code);
    }
    
    public void changeState(int id) 
    {
        game.setGameState(id);
    }
    
    public void add(GameComponent gc) 
    {
        canvas.add(gc);
    }
    
    public void remove(GameComponent gc) 
    {
        canvas.remove(gc);
    }
    
    public int getWidth() { return canvas.getWidth(); }
    
    public int getHeight() { return canvas.getHeight(); }
    
    public StateBasedGame getGame() { return game; }
    
    public GameCanvas getGameCanvas() { return canvas; }
}
