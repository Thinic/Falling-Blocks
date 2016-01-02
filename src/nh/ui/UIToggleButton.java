package nh.ui;

public class UIToggleButton extends UIButton
{
    private int index;
    private String[] options;
    
    public UIToggleButton() 
    {
        super();
        
        addUIActionListener(new ToggleAction());
    }
    
    public void setItems(String[] options) 
    {
        this.options = options;
        
        setIndex(0);
    }
    
    public int getIndex() 
    {
        return index;
    }
    
    public void setIndex(int i) 
    {
        index = i;
        
        setText(options[index]);
    }
    
    private class ToggleAction implements UIActionListener 
    {
        
        public void actionPerformed(UIActionEvent e) 
        {
            index++;
            
            if (options != null) 
            {
                if (index >= options.length) 
                {
                    index = 0;
                }
            }
            
            setIndex(index);
            
            System.out.println(index + " " + getText());
        }
    }
}
