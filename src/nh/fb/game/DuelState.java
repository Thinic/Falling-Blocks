package nh.fb.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import nh.core.GameState;
import nh.fb.FallingBlocksGame;
import nh.fb.GameSettings;
import nh.fb.Player;
import nh.fb.PlayerSettings;
import nh.fb.ui.FBDrawPanel;
import nh.ui.SceneUtil;
import nh.ui.UIActionEvent;
import nh.ui.UIActionListener;
import nh.ui.UIButton;
import nh.ui.UIElement;

public class DuelState extends GameState implements UIActionListener
{
    private UIButton returnBtn;
    
    private GameSettings settings;
    private FallingBlocksGame game1, game2;
    private FBDrawPanel panel1, panel2;
    private PlayerSettings pSettings1, pSettings2;
    private Player player1, player2;
    
    public DuelState(Game game) 
    {
        super(game);
        
        SceneUtil util = getSceneUtil();
        
        settings = new GameSettings();
        game1 = new FallingBlocksGame(settings);
        game2 = new FallingBlocksGame(settings);
        
        util.offsetType = UIElement.BOTTOM_CENTER;
        util.reset();
        
        int size = 16;
        
        panel1 = new FBDrawPanel(game1, size);
        panel1.setMaximizedVertical(true);
        panel1.setOffset(0, -22);
        panel1.setOffsetType(UIElement.CENTER);
        getScene().add(panel1);
        
        panel2 = new FBDrawPanel(game2, size);
        panel2.setMaximizedVertical(true);
        panel2.setOffset(0, -22);
        panel2.setOffsetType(UIElement.CENTER);
        getScene().add(panel2);
        
        returnBtn = util.createButton("Return to Menu", this);
        
        reset();
    }
    
    @Override
    public void reset()
    {
        settings = new GameSettings();
        
        game1 = new FallingBlocksGame(settings);
        game2 = new FallingBlocksGame(settings);
        
        panel1.setGame(game1);
        panel2.setGame(game2);
        
        pSettings1 = new PlayerSettings();
        pSettings1.setLeftKey(KeyEvent.VK_A);
        pSettings1.setRightKey(KeyEvent.VK_D);
        pSettings1.setDownKey(KeyEvent.VK_S);
        pSettings1.setSecRotCWKey(KeyEvent.VK_W);
        pSettings1.setRotCCWKey(KeyEvent.VK_Z);
        pSettings1.setRotCWKey(KeyEvent.VK_X);
        pSettings1.setHardDropKey(KeyEvent.VK_Q);
        pSettings1.setHoldKey(KeyEvent.VK_E);
        pSettings1.setKeyWait(18);
        pSettings1.setKeyRepeat(6);
        player1 = new Player(pSettings1);
        
        pSettings2 = new PlayerSettings();
        pSettings2.setLeftKey(KeyEvent.VK_LEFT);
        pSettings2.setRightKey(KeyEvent.VK_RIGHT);
        pSettings2.setDownKey(KeyEvent.VK_DOWN);
        pSettings2.setSecRotCWKey(KeyEvent.VK_UP);
        pSettings2.setRotCCWKey(KeyEvent.VK_DELETE);
        pSettings2.setRotCWKey(KeyEvent.VK_PAGE_DOWN);
        pSettings2.setHardDropKey(KeyEvent.VK_END);
        pSettings2.setHoldKey(KeyEvent.VK_HOME);
        pSettings2.setKeyWait(18);
        pSettings2.setKeyRepeat(6);
        player2 = new Player(pSettings2);
    }
    
    @Override
    public void updateState()
    {
        game1.update();
        game2.update();
        
        player1.update(getKeyboard(), game1);
        player2.update(getKeyboard(), game2);
    }
    
    @Override
    public void draw(Graphics g)
    {
        int sizeHeight = (getHeight() - 50) / (game1.getBoard().getHeight() + 2);
        
        int sizeWidth  = (getWidth() - 150) / (game1.getBoard().getWidth() + 10) / 2;
        
        panel1.setSize(Math.min(sizeHeight, sizeWidth));
        panel2.setSize(Math.min(sizeHeight, sizeWidth));
        
        int offset = 20;
        
        panel1.setOffset(-panel1.getWidth()/2 - offset, -22);
        panel2.setOffset(panel2.getWidth()/2 + offset, -22);
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
