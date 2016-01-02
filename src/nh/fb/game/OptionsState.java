package nh.fb.game;

import java.awt.Graphics;

import nh.core.GameState;
import nh.ui.Scene;
import nh.ui.SceneUtil;
import nh.ui.UIActionEvent;
import nh.ui.UIActionListener;
import nh.ui.UIButton;
import nh.ui.UIElement;
import nh.ui.UIPanel;

public class OptionsState extends GameState implements UIActionListener
{
    private UIPanel menuPanel, gpPanel;
    
    private UIButton menuReturnBtn, menuGPBtn;
    private UIButton gpMenuBtn;
    
    public OptionsState(Game game)
    {
        super(game);
        
        initMenuPanel();
        initGPPanel();
        
        setToOptionsMenu();
    }
    
    private void initMenuPanel() 
    {
        menuPanel = new UIPanel();
        menuPanel.setMaximized(true);
        getScene().add(menuPanel);
        SceneUtil util = new SceneUtil(new Scene(menuPanel));
        
        util.offsetType = UIElement.BOTTOM_CENTER;
        menuReturnBtn = util.createButton("Return to Menu", this);
                        util.createButton("Controls", this);
        menuGPBtn     = util.createButton("Gameplay", this);
        
        util.offsetType = UIElement.TOP_CENTER;
        util.reset();
        util.createLabel("Options");
    }
    
    private void initGPPanel() 
    {
        gpPanel = new UIPanel();
        gpPanel.setMaximized(true);
        getScene().add(gpPanel);
        SceneUtil util = new SceneUtil(new Scene(gpPanel));
        util.offsetType = UIElement.TOP_CENTER;
        util.reset();
        
        util.createLabel("Gameplay");
        
        util.offsetType = UIElement.BOTTOM_CENTER;
        util.reset();
        
        gpMenuBtn = util.createButton("Back", this);
        
        util.addElement(new OptionPanel("Test", new String[] { "1", "2", "three"}));
        util.addElement(new OptionPanel("Test 2", new String[] { "one", "two", "3"}));
    }
    
    public void setToOptionsMenu() 
    {
        menuPanel.show();
        gpPanel.hide();
    }
    
    public void setToGameplayMenu() 
    {
        menuPanel.hide();
        gpPanel.show();
    }

    @Override
    public void reset()
    {
        
    }
    
    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void draw(Graphics g)
    {
        menuPanel.setSize(getWidth(), getHeight());
    }
    
    @Override
    public void actionPerformed(UIActionEvent e)
    {
        UIElement source = e.getSource();
        
        if (source == menuReturnBtn) 
        {
            changeGameState(Game.STATE_TITLE);
        }
        if (source == menuGPBtn) 
        {
            setToGameplayMenu();
        }
        if (source == gpMenuBtn) 
        {
            setToOptionsMenu();
        }
    }
}
