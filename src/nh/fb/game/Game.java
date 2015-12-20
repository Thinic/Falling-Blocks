package nh.fb.game;

import nh.core.StateBasedGame;


public class Game extends StateBasedGame
{
    public static final int STATE_TITLE = 0;
    public static final int STATE_PLAY = 1;
    public static final int STATE_DUEL = 2;
    public static final int STATE_OPTIONS = 3;
    public static final int STATE_HELP = 4;
    
    public Game() 
    {
        super();
        
        addGameState(STATE_TITLE, new TitleState(this));
        addGameState(STATE_PLAY, new PlayState(this));
        addGameState(STATE_DUEL, new EmptyState(this));
        addGameState(STATE_OPTIONS, new EmptyState(this));
        addGameState(STATE_HELP, new EmptyState(this));
        
        getWindow().setTitle("Falling Blocks");
    }
    
    @Override
    public void initialize()
    {
        setGameState(STATE_TITLE);
    }

    @Override
    public void end()
    {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String args[]) 
    {
        new Game().start();
    }
}
