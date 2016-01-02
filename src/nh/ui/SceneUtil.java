package nh.ui;

public class SceneUtil
{
    public int paddingX = 0, paddingY = 2;
    public int offsetType = UIElement.TOP_LEFT;
    
    public int buttonWidth = 200;
    public int buttonHeight = 40;
    
    public boolean horizontal = false;
    
    public int x, y;
    
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
    
    public void addElement(UIElement e) 
    {
        setProps(e, e.getWidth(), e.getHeight());
    }
    
    public UIButton createButton(String text, UIActionListener listener) 
    {
        UIButton b = createButton(text);
        b.addUIActionListener(listener);
        return b;
    }
    
    public UIButton createButton(String text) 
    {
        UIButton e = new UIButton();
        
        setProps(e, buttonWidth, buttonHeight);
        e.setText(text);
        
        return e;
    }
    
    public UISelector createSelector(int value, String[] values) 
    {
        UISelector e = new UISelector();
        e.setValue(value);
        e.setSelections(values);
        setProps(e, buttonWidth, buttonHeight);
        
        return e;
    }
    
    public UICounter createCounter(int value, int min, int max) 
    {
        UICounter e = new UICounter();
        e.setValue(value);
        e.setBounds(min, max);
        setProps(e, buttonWidth, buttonHeight);
        
        return e;
    }
    
    public UILabel createLabel(String text) 
    {
        UILabel e = new UILabel();
        e.setText(text);
        setProps(e, buttonWidth, buttonHeight);
        
        return e;
    }
    
    private void setProps(UIElement e, int w, int h) 
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
