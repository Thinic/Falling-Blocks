package nh.fb.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import nh.core.GameState;
import nh.fb.FallingBlocks;
import nh.fb.GameSettings;
import nh.fb.Player;
import nh.fb.PlayerSettings;
import nh.fb.gfx.BasicBoardRenderer;
import nh.fb.gfx.IBoardRenderer;
import nh.gui.Element;
import nh.gui.SceneUtil;

public class PlayState extends GameState
{
    private GameSettings settings;
    private FallingBlocks fbGame;
    
    private PlayerSettings playerSettings;
    private Player player;
    
    public PlayState(Game game) 
    {
        super(game);
        
        SceneUtil util = getSceneUtil();
        util.offsetType = Element.BOTTOM_CENTER;
        
        util.createButton("Return to Menu", ActionUtil.changeState(game, Game.STATE_TITLE));
        
        reset();
    }

    @Override
    public void reset()
    {
        settings = new GameSettings();
        fbGame = new FallingBlocks(settings);
        
        playerSettings = new PlayerSettings();
        playerSettings.setLeftKey(KeyEvent.VK_LEFT);
        playerSettings.setRightKey(KeyEvent.VK_RIGHT);
        playerSettings.setDownKey(KeyEvent.VK_DOWN);
        playerSettings.setSecRotCWKey(KeyEvent.VK_UP);
        playerSettings.setRotCCWKey(KeyEvent.VK_Z);
        playerSettings.setRotCWKey(KeyEvent.VK_X);
        playerSettings.setHardDropKey(KeyEvent.VK_SPACE);
        playerSettings.setKeyWait(15);
        playerSettings.setKeyRepeat(4);
        
        player = new Player(playerSettings);
    }

    @Override
    public void updateState()
    {
        fbGame.update();
        
        player.update(getKeyboard(), fbGame);
    }

    @Override
    public void draw(Graphics g)
    {
        int size = 24;
        
        IBoardRenderer br = new BasicBoardRenderer();
        
        int width = fbGame.getBoard().getWidth() * size;
        
        br.draw((Graphics2D)g, fbGame.getBoard(), fbGame.getCurrentPiece(), fbGame.getGhostPiece(), getWidth()/2 - width/2, 10, size);
    }
}
