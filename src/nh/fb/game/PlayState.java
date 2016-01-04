package nh.fb.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import nh.core.GameState;
import nh.fb.FallingBlocksGame;
import nh.fb.GameSettings;
import nh.fb.Player;
import nh.fb.PlayerSettings;
import nh.fb.ui.FBDrawPanel;
import nh.fb.ui.FBPanel;
import nh.ui.SceneUtil;
import nh.ui.UIActionEvent;
import nh.ui.UIActionListener;
import nh.ui.UIButton;
import nh.ui.UIElement;

public class PlayState extends GameState implements UIActionListener
{
    private GameSettings settings;
    private FallingBlocksGame fbGame;
    
    private PlayerSettings playerSettings;
    private Player player;
    
    private UIButton returnBtn;
    
    private FBPanel fbPanel;
    private FBDrawPanel p;
    
    public PlayState(Game game) 
    {
        super(game);
        
        settings = new GameSettings();
        fbGame = new FallingBlocksGame(settings);
        
        fbPanel = new FBPanel(fbGame);
        fbPanel.setSize(10, 10);
        fbPanel.setOffset(0, 0);
        fbPanel.setOffsetType(UIElement.CENTER);
        fbPanel.setMaximized(true);
//        getScene().add(fbPanel);
        
        p = new FBDrawPanel(fbGame, 24);
        p.setOffset(0, -22);
        getScene().add(p);
        
        SceneUtil util = getSceneUtil();
        util.offsetType = UIElement.BOTTOM_CENTER;
        
        returnBtn = util.createButton("Return to Menu", this);
        
        reset();
    }
    
    @Override
    public void reset()
    {
        settings = new GameSettings();
        fbGame = new FallingBlocksGame(settings);
        
        fbPanel.setGame(fbGame);
        p.setGame(fbGame);
        
        playerSettings = new PlayerSettings();
        playerSettings.setLeftKey(KeyEvent.VK_LEFT);
        playerSettings.setRightKey(KeyEvent.VK_RIGHT);
        playerSettings.setDownKey(KeyEvent.VK_DOWN);
        playerSettings.setSecRotCWKey(KeyEvent.VK_UP);
        playerSettings.setRotCCWKey(KeyEvent.VK_Z);
        playerSettings.setRotCWKey(KeyEvent.VK_X);
        playerSettings.setHardDropKey(KeyEvent.VK_SPACE);
        playerSettings.setKeyWait(18);
        playerSettings.setKeyRepeat(6);
        
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
//        int size = 24;
//        
//        IBoardRenderer br = new BasicBoardRenderer();
//        
//        int width = fbGame.getBoard().getWidth() * size;
//        
//        br.draw((Graphics2D)g, fbGame.getBoard(), fbGame.getPiece(), fbGame.getGhostPiece(), getWidth()/2 - width/2, 10, size);
    }

    @Override
    public void actionPerformed(UIActionEvent e)
    {
        UIElement source = e.getSource();
        
        if (source == returnBtn) 
        {
            changeGameState(Game.STATE_TITLE);
        }
    }
}
