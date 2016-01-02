package nh.ui;

public class _TestMain
{
    public static void main(String args[]) 
    {
        Window window = new Window();
        window.setTitle("Test");
        window.setSize(800, 600);
        window.show();
        
        Scene scene = new Scene();
        window.setScene(scene);
        
        SceneUtil util = new SceneUtil(scene);
        
        util.createButton("Test");
        
        
        while (window.isVisible()) 
        {
            window.draw();
        }
        
        System.exit(0);
    }
}
