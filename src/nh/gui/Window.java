package nh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window
{
    private JFrame frame;
    private GamePanel panel;
    private Scene scene;
    
    public Window() 
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel();
        frame.add(panel);
        
        scene = null;
        
        setSize(640, 400);
    }
    
    public void show() 
    {
        frame.setVisible(true);
        frame.requestFocus();
    }
    
    public void hide() 
    {
        frame.setVisible(false);
    }
    
    public boolean isVisible() 
    {
        return frame.isVisible();
    }
    
    public void setTitle(String title) 
    {
        frame.setTitle(title);
    }
    
    public String getTitle() 
    {
        return frame.getTitle();
    }
    
    public void setSize(int width, int height) 
    {
        panel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public int getWidth() 
    {
        return panel.getWidth(); 
    }
    
    public int getHeight() 
    {
        return panel.getHeight();
    }
    
    public void draw() 
    {
        frame.repaint();
    }
    
    public void setScene(Scene s) 
    {
        scene = s;
    }
    
    @SuppressWarnings("serial")
    private class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
    {
        public GamePanel() 
        {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            frame.addKeyListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g) 
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            if (scene == null) return;
            
            scene.setSize(getWidth(), getHeight());
            
            scene.getRoot().draw(g);
            scene.getRoot().drawOverlay(g);
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (scene != null) scene.mouseMoved(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            if (scene != null) scene.mouseMoved(e.getX(), e.getY());
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            if (scene != null) scene.mousePressed(e.getButton());
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (scene != null) scene.mouseReleased(e.getButton());
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (scene != null) scene.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            if (scene != null) scene.keyReleased(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
            
        }
    }
}
