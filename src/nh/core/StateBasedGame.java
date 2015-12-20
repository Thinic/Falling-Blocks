package nh.core;

import java.util.HashMap;

import nh.core.gui.GameWindow;

public abstract class StateBasedGame
{
    private GameWindow window;
    
    private boolean running = false;
    
    private HashMap<Integer, GameState> states;
    
    private GameState current;
    
    public StateBasedGame() 
    {
        window = new GameWindow();
        
        states = new HashMap<>();
    }
    
    public abstract void initialize();
    public abstract void end();
    
    public GameWindow getWindow() { return window; }
    
    public void start() 
    {
        if (running) return; 
        
        running = true;
        run();
    }
    
    public void stop() 
    {
        running = false;
        window.hide();
    }
    
    private void run() 
    {
        initialize();
        
        window.show();
        
        while (running) 
        {
            update();
            draw();
            
            try
            {
                Thread.sleep(16);
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            running = window.isVisible();
        }
        
        end();
        exit();
    }
    
    private void update() 
    {
        window.update();
        current.update();
    }
    
    private void draw() 
    {
        window.draw();
    }
    
    public void addGameState(int id, GameState state) 
    {
        states.put(id, state);
    }
    
    public void setGameState(int id) 
    {
        current = states.get(id);
        
        current.reset();
        
        window.setGameCanvas(current.getGameCanvas());
    }
    
    public void exit() 
    {
        System.exit(0);
    }
}
