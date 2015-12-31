package nh.gui;

public class SceneUtil
{
    public int paddingX = 0, paddingY = 2;
    public int offsetType = Element.TOP_LEFT;
    
    public int buttonWidth = 200;
    public int buttonHeight = 40;
    
    public boolean horizontal = false;
    
    private int x, y;
    
    private Scene scene;
    
    public SceneUtil(Scene scene) 
    {
        this.scene = scene;
        
        reset();
    }
    
    public void reset() 
    {
        x = paddingX;
        y = paddingY;
    }
    
    public Button createButton(String text) 
    {
        return createButton(text, null);
    }
    
    public Button createButton(String text, Action action) 
    {
        Button e = new Button();
        
        setProps(e, buttonWidth, buttonHeight);
        e.setText(text);
        e.setAction(action);
        
        return e;
    }
    
    public ToggleButton createToggleButton(String text) 
    {
        return createToggleButton(text, null);
    }
    
    public ToggleButton createToggleButton(String text, ToggleAction action) 
    {
        ToggleButton e = new ToggleButton();
        
        setProps(e, buttonWidth, buttonHeight);
        e.setText(text);
        e.setAction(action);
        
        return e;
    }
    
    public Counter createCounter(int value, int min, int max) 
    {
        return createCounter(value, min, max, null);
    }
    
    public Counter createCounter(int value, int min, int max, Action action) 
    {
        Counter e = new Counter();
        e.setValue(value);
        e.setBounds(min, max);
        e.setAction(action);
        setProps(e, buttonWidth, buttonHeight);
        
        return e;
    }
    
    public Label createLabel(String text) 
    {
        Label e = new Label();
        e.setText(text);
        setProps(e, buttonWidth, buttonHeight);
        
        return e;
    }
    
    private void setProps(Element e, int w, int h) 
    {
        e.setSize(w, h);
        e.setOffset(x, y);
        e.setOffsetType(offsetType);
        
        scene.add(e);
        
        if (horizontal) 
        {
            x += w + paddingX;
        }
        else {
            y += h + paddingY;
        }
    }
}
