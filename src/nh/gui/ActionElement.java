package nh.gui;

public abstract class ActionElement extends Element
{
    private Action action;
    
    public void setAction(Action a) 
    {
        action = a;
    }
    
    public void performAction() 
    {
        if (action != null) action.performAction();
    }
}
