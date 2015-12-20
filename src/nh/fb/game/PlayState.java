package nh.fb.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import nh.core.GameAction;
import nh.core.GameState;
import nh.core.StateBasedGame;
import nh.core.gui.GameButton;
import nh.fb.FallingBlocks;
import nh.fb.GameSettings;
import nh.fb.Player;
import nh.fb.PlayerSettings;
import nh.fb.gfx.BasicBoardRenderer;
import nh.fb.gfx.IBoardRenderer;

public class PlayState extends GameState
{
    private GameButton exitButton;
    
    private PlayerSettings playerSettings;
    private Player player;
    
    private GameSettings settings;
    private FallingBlocks fbGame;
    
    
    
    public PlayState(StateBasedGame game) 
    {
        super(game);
        
        exitButton = new GameButton("Return to Menu");
        exitButton.setSize(300, 40);
        exitButton.setAction(new GameAction() {
            public void performAction() {
                changeState(Game.STATE_TITLE);
            }
        });
        add(exitButton);
        
        playerSettings = new PlayerSettings();
        playerSettings.setDownKey(KeyEvent.VK_DOWN);
        playerSettings.setLeftKey(KeyEvent.VK_LEFT);
        playerSettings.setRightKey(KeyEvent.VK_RIGHT);
        playerSettings.setRotCCWKey(KeyEvent.VK_Z);
        playerSettings.setRotCWKey(KeyEvent.VK_X);
        playerSettings.setSecRotCWKey(KeyEvent.VK_UP);
        playerSettings.setHardDropKey(KeyEvent.VK_SPACE);
        
        playerSettings.setLineClearWait(0);
        
        player = new Player(playerSettings);
        
        reset();
    }
    
    @Override
    public void update()
    {
        exitButton.setLocation(getWidth()/2 - exitButton.getWidth()/2, getHeight() - 10 - exitButton.getHeight());
        
        fbGame.update();
        
        player.update(this, fbGame);
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        int size = 24;
        
        {
            IBoardRenderer br = new BasicBoardRenderer();
            
            int width = fbGame.getBoard().getWidth()*size;
            
            br.draw(g, fbGame.getBoard(), fbGame.getCurrentPiece(), fbGame.getGhostPiece(), getWidth()/2 - width/2, 10, size);
        }
        
    }
    
    @Override
    public void reset()
    {
        settings = new GameSettings();
        fbGame = new FallingBlocks(settings);
    }
    
}
