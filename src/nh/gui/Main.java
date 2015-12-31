package nh.gui;

public class Main
{
    public static Action exitAction = new Action(){
        public void performAction() {
            System.exit(0);
        }
    };
    
    public static void main(String args[]) 
    {
        Window window = new Window();
        window.setTitle("Test GUI");
        window.setSize(800, 600);
        window.show();
        
        Scene scene = new Scene();
        window.setScene(scene);
        
        SceneUtil util = new SceneUtil(scene);
        util.offsetType = Element.BOTTOM | Element.CENTER_X;
        
        util.createButton("Exit", exitAction);
        util.createButton("Options");
        util.createButton("Help");
        util.createButton("2 Player");
        util.createButton("1 Player");
        util.createToggleButton("Toggle").toggleOn();
        util.createCounter(314, 300, 320);
        util.createLabel("Set a value:");
        
        while (true) 
        {
            window.draw();
        }
    }
}
