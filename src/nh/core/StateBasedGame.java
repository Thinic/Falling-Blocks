package nh.core;

import java.util.HashMap;

import nh.ui.Window;

public abstract class StateBasedGame
{
    private Window window;
    
    private boolean running = false;
    
    private HashMap<Integer, GameState> states;
    
    private GameState current;
    
    public StateBasedGame() 
    {
        window = new Window();
        
        states = new HashMap<>();
    }
    
    public abstract void initialize();
    public abstract void end();
    
    public Window getWindow() { return window; }
    
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
                Thread.sleep(1000 / 100);
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
        GameState state = states.get(id);
        
        if (state == null) 
        {
            System.err.println("State with id " + id + " does not exist, returning to last state");
            
            return;
        }
        
        current = state;
        
        current.reset();
        
        window.setScene(current.getScene());
    }
    
    public void exit() 
    {
        System.exit(0);
    }
}

