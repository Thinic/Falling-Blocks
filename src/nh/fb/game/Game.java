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
        getWindow().setSize(800, 600);
        getWindow().setTitle("Falling Blocks");
        
        addGameState(STATE_TITLE, new TitleState(this));
        addGameState(STATE_PLAY, new PlayState(this));
        addGameState(STATE_OPTIONS, new OptionsState(this));
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
        Game game = new Game();
        game.start();
    }
}
