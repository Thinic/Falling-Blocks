package nh.fb.gfx;

import java.awt.Color;
import java.awt.Graphics2D;

public class GhostBlockRenderer extends BlockRenderer
{
    @Override
    public void draw(Graphics2D g, int type, int id, int flags, int x, int y, int size)
    {
//        g.setColor(getColor(type));
        
//        g.fillRect(x, y, size, size);
        
        g.setColor(getColor(type));
        
        int border = 4;
        
        int sizeBorder = size - border;
        
        if (type != 0) 
        {
            if (!hasFlag(flags, FLAG_SAME_ID_UP)) 
            {
                g.fillRect(x, y, size, border);
            }
            
            if (!hasFlag(flags, FLAG_SAME_ID_DOWN)) 
            {
                g.fillRect(x, y + sizeBorder, size, border);
            }
            
            if (!hasFlag(flags, FLAG_SAME_ID_LEFT)) 
            {
                g.fillRect(x, y, border, size);
            }
            
            if (!hasFlag(flags, FLAG_SAME_ID_RIGHT)) 
            {
                g.fillRect(x + sizeBorder, y, border, size);
            }
            
            if (hasFlag(flags, FLAG_SAME_ID_UP | FLAG_SAME_ID_LEFT) &&
               !hasFlag(flags, FLAG_SAME_ID_UP_LEFT)) 
            {
                g.fillRect(x, y, border, border);
            }
            
            if (hasFlag(flags, FLAG_SAME_ID_UP | FLAG_SAME_ID_RIGHT) &&
               !hasFlag(flags, FLAG_SAME_ID_UP_RIGHT)) 
            {
                g.fillRect(x + sizeBorder, y, border, border);
            }
            
            if (hasFlag(flags, FLAG_SAME_ID_DOWN | FLAG_SAME_ID_LEFT) &&
               !hasFlag(flags, FLAG_SAME_ID_DOWN_LEFT)) 
            {
                g.fillRect(x, y + sizeBorder, border, border);
            }
            
            if (hasFlag(flags, FLAG_SAME_ID_DOWN | FLAG_SAME_ID_RIGHT) &&
               !hasFlag(flags, FLAG_SAME_ID_DOWN_RIGHT)) 
            {
                g.fillRect(x + sizeBorder, y + sizeBorder, border, border);
            }
        }
    }
    
    private Color getColor(int type) 
    {
        switch (type) 
        {
            default: return new Color(0x6F6F6F);
        }
    }
}
