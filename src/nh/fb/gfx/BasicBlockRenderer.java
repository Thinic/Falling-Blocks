package nh.fb.gfx;

import java.awt.Color;
import java.awt.Graphics2D;

import nh.fb.board.PieceType;

public class BasicBlockRenderer extends BlockRenderer
{
    @Override
    public void draw(Graphics2D g, int type, int id, int flags, int x, int y, int size)
    {
        g.setColor(getColor(type));
        
        g.fillRect(x, y, size, size);
        
        g.setColor(getColor(type).darker());
        
        int border = 3;
        
        int sizeBorder = size - border;
        
        if (type != 0) 
        {
            if (!hasFlag(flags, FLAG_SAME_ID_DOWN)) 
            {
                g.fillRect(x, y + sizeBorder, size, border);
            }
            
            if (!hasFlag(flags, FLAG_SAME_ID_RIGHT)) 
            {
                g.fillRect(x + sizeBorder, y, border, size);
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
        
        g.setColor(getColor(type).brighter());
        
        if (type != 0) 
        {
            if (!hasFlag(flags, FLAG_SAME_ID_UP)) 
            {
                g.fillRect(x, y, size, border);
            }
            
            if (!hasFlag(flags, FLAG_SAME_ID_LEFT)) 
            {
                g.fillRect(x, y, border, size);
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
        }
        
        if (type == 0) 
        {
            g.setColor(new Color(0x7F7F7F));
            
            g.fillRect(x, y, size, size);
            
            g.setColor(new Color(0x8F8F8F));
            
            border = 1;
            
            g.fillRect(x + border, y + border, size - border*2, size - border*2);
        }
    }
    
    private Color getColor(int type) 
    {
        switch (type) 
        {
            case PieceType.I_VALUE: return new Color(0x7FFFFF);
            case PieceType.J_VALUE: return new Color(0x003FFF);
            case PieceType.L_VALUE: return new Color(0xFF7F00);
            case PieceType.T_VALUE: return new Color(0x5F1F8F);
            case PieceType.S_VALUE: return new Color(0x3FDF3F);
            case PieceType.Z_VALUE: return new Color(0xBF1F1F);
            case PieceType.O_VALUE: return new Color(0xDFDF2F);
            default: return new Color(0x9F9F9F);
        }
    }
}
