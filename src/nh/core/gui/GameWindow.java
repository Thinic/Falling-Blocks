package nh.core.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Window for game rendering
 */
public class GameWindow
{
    private JFrame frame;
    /*
     * TODO have jpanel draw gameCanvas
     */
    private JPanel canvas;
    private GameCanvas gameCanvas;
    
    public GameWindow() 
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("GameWindow");
        frame.setLocationRelativeTo(null);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                hide();
            }
        });
        
        canvas = new GamePanel();
        frame.add(canvas);
        
        gameCanvas = new GameCanvas();
        gameCanvas.setColor(0x000000);
        
        setSize(800, 600);
    }
    
    public void show() 
    {
        frame.setVisible(true);
        
        frame.getContentPane().requestFocus();
    }
    
    public void hide() 
    {
        frame.setVisible(false);
    }
    
    public boolean isVisible() 
    {
        return frame.isVisible();
    }
    
    public void update() 
    {
        if (gameCanvas != null) gameCanvas.update();
    }
    
    public void draw() 
    {
        canvas.repaint();
    }
    
    public String getTitle() 
    { 
        return frame.getTitle(); 
    }
    
    public void setTitle(String string)
    {
        frame.setTitle(string);
    }
    
    public void setSize(int w, int h) 
    {
        canvas.setPreferredSize(new Dimension(w, h));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void setGameCanvas(GameCanvas c) 
    {
        gameCanvas = c;
    }
    
    @SuppressWarnings("serial")
    private class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener 
    {
        public GamePanel() 
        {
            super();
            
            /*
             * TODO figure out why need all these
             */
            frame.addKeyListener(this);
            this.addKeyListener(this);
            frame.getContentPane().addKeyListener(this);
            frame.getContentPane().addMouseListener(this);
            frame.getContentPane().addMouseMotionListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g2) 
        {
            Graphics2D g = (Graphics2D)g2;
            
            if (gameCanvas != null) 
            {
                gameCanvas.setSize(getWidth(), getHeight());
                gameCanvas.draw(g);
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (gameCanvas != null) 
            {
                gameCanvas.mouseMoved(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            if (gameCanvas != null) 
            {
                gameCanvas.mouseMoved(e.getX(), e.getY());
            }
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
            if (gameCanvas != null) 
            {
                gameCanvas.mousePressed(e.getButton());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (gameCanvas != null) 
            {
                gameCanvas.mouseReleased(e.getButton());
            }
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (gameCanvas != null) 
            {
                gameCanvas.keyPressed(e.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            if (gameCanvas != null) 
            {
                gameCanvas.keyReleased(e.getKeyCode());
            }
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
            
        }
        
    }
}
