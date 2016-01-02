package nh.fb.game;

import nh.ui.UIActionEvent;
import nh.ui.UIActionListener;
import nh.ui.UILabel;
import nh.ui.UIPanel;
import nh.ui.UIToggleButton;

public class OptionPanel extends UIPanel
{
    private UILabel label;
    private UIToggleButton button;
    
    public OptionPanel(String desc, String[] options) 
    {
        super();
        
        final OptionPanel panel = this;
        
        setWidth(500);
        
        setHeight(40 + 10);
        
        label = new UILabel();
        label.setText(desc);
        label.setSize(200, 40);
        label.setOffsetType(CENTER_Y | LEFT);
        label.setOffset(10, 0);
        add(label);
        
        button = new UIToggleButton();
        button.setItems(options);
        button.setIndex(0);
        button.setSize(200, 40);
        button.setOffsetType(CENTER_Y | RIGHT);
        button.setOffset(10, 0);
        button.addUIActionListener(new UIActionListener() {
            public void actionPerformed(UIActionEvent e) 
            {
                panel.performAction();
            }
        });
        add(button);
    }
    
    public int getToggleButtonIndex() 
    {
        return button.getIndex();
    }
    
    public void setToggleButtonIndex(int i) 
    {
        button.setIndex(i);
    }
}
