package nh.core;

import java.awt.Graphics;

import nh.ui.Scene;
import nh.ui.SceneUtil;
import nh.ui.UIElement;

public abstract class GameState
{
    private Scene scene;
    private SceneUtil util;
    private GamePanel panel;
    private StateBasedGame game;
    private Keyboard keys;
    
    public GameState(StateBasedGame game) 
    {
        this.game = game;
        
        keys = new Keyboard();
        
        panel = new GamePanel(this);
        panel.setSize(400, 400);
        
        scene = new Scene();
        scene.add(panel);
        
        util = new SceneUtil(scene);
    }
    
    public Keyboard getKeyboard() 
    {
        return keys;
    }
    
    public StateBasedGame getGame() 
    {
        return game;
    }
    
    public Scene getScene() 
    {
        return scene;
    }
    
    public SceneUtil getSceneUtil() 
    {
        return util;
    }
    
    public void changeGameState(int id) 
    {
        game.setGameState(id);
    }
    
    public int getWidth() 
    {
        return panel.getWidth();
    }
    
    public int getHeight() 
    {
        return panel.getHeight();
    }
    
    public void update() 
    {
        updateState();
        
        keys.update();
    }
    
    public abstract void reset();
    
    public abstract void updateState();
    
    public abstract void draw(Graphics g);
    
    public boolean isKeyJustDown(int key)
    {
        return keys.isKeyJustDown(key);
    }
    
    public boolean isKeyDown(int key) 
    {
        return keys.isKeyDown(key);
    }
    
    private class GamePanel extends UIElement
    {
        private GameState state;
        
        public GamePanel(GameState state) 
        {
            this.state = state;
        }
        
        @Override
        public void drawElement(Graphics g)
        {
            if (hasParent()) 
            {
                setSize(getParent().getWidth(), getParent().getHeight());
            }
            
            state.draw(g);
        }

        @Override
        public void drawElementOverlay(Graphics g)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onResize()
        {
            
        }

        @Override
        public void onMouseMove(int x, int y)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onMousePress(int x, int y, int button)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onMouseRelease(int x, int y, int button)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onKeyPress(int key)
        {
            keys.keyPressed(key);
        }

        @Override
        public void onKeyRelease(int key)
        {
            keys.keyReleased(key);
        }
        
    }
}
