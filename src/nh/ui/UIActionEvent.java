package nh.ui;

public class UIActionEvent
{
    private UIElement src;
    
    public UIActionEvent(UIElement e) 
    {
        src = e;
    }
    
    public UIElement getSource() 
    {
        return src;
    }
}
