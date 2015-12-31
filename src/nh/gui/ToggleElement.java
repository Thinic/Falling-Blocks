package nh.gui;

public abstract class ToggleElement extends Element
{
    private ToggleAction action;
    private boolean toggle;
    
    public void setAction(ToggleAction a) 
    {
        action = a;
    }
    
    public void toggleOn() 
    {
        toggle = true;
        
        if (action != null) action.toggleOn();
    }
    
    public void toggleOff() 
    {
        toggle = false;
        
        if (action != null) action.toggleOff();
    }
    
    public void toggle() 
    {
        if (isToggled()) 
        {
            toggleOff();
        }
        else 
        {
            toggleOn();
        }
    }
    
    public boolean isToggled() 
    {
        return toggle;
    }
}
