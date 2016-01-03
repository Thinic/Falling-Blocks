package nh.fb.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import nh.core.GameState;
import nh.fb.FallingBlocksGame;
import nh.fb.GameSettings;
import nh.fb.Player;
import nh.fb.PlayerSettings;
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
    private FBPanel panel1, panel2;
    private PlayerSettings pSettings1, pSettings2;
    private Player player1, player2;
    
    public DuelState(Game game) 
    {
        super(game);
        
        SceneUtil util = getSceneUtil();
        
        util.offsetType = UIElement.BOTTOM_CENTER;
        util.reset();
        
        panel1 = new FBPanel(null);
        panel1.setMaximizedVertical(true);
        panel1.setOffset(20, 0);
        panel1.setOffsetType(UIElement.CENTER_Y | UIElement.LEFT);
        getScene().add(panel1);
        
        panel2 = new FBPanel(null);
        panel2.setMaximizedVertical(true);
        panel2.setOffset(20, 0);
        panel2.setOffsetType(UIElement.CENTER_Y | UIElement.RIGHT);
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
        // TODO Auto-generated method stub
        
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
