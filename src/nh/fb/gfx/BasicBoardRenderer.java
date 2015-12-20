package nh.fb.gfx;

import java.awt.Color;
import java.awt.Graphics2D;

import nh.fb.board.BlockData;
import nh.fb.board.Board;
import nh.fb.board.Piece;

public class BasicBoardRenderer implements IBoardRenderer
{
    private BlockRenderer blockRenderer;
    private BlockRenderer ghostRenderer;
    
    public BasicBoardRenderer() 
    {
        blockRenderer = new BasicBlockRenderer();
        ghostRenderer = new GhostBlockRenderer();
    }

    @Override
    public void draw(Graphics2D g, Board b, Piece p, Piece ghost, int x, int y, int blockSize)
    {
        int startX = x;
        int startY = y + b.getHeight() * blockSize - blockSize;
        
        int width = b.getWidth()*blockSize;
        int height = b.getHeight()*blockSize;
        
        for (int bx = 0; bx < b.getWidth(); bx++) 
        {
            for (int by = 0; by < b.getHeight(); by++) 
            {
                int drawX = startX + bx*blockSize;
                int drawY = startY - by*blockSize;
                
                int type = b.getValue(bx, by);
                int id = b.getID(bx, by);
                
                int flags = 0;
                
                if (b.getValue(bx, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP);
                if (b.getValue(bx, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN);
                if (b.getValue(bx - 1, by) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_LEFT);
                if (b.getValue(bx + 1, by) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_RIGHT);
                
                if (b.getValue(bx + 1, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_RIGHT);
                if (b.getValue(bx - 1, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_LEFT);
                if (b.getValue(bx + 1, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_RIGHT);
                if (b.getValue(bx - 1, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_LEFT);
                
                if (b.getID(bx, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP);
                if (b.getID(bx, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN);
                if (b.getID(bx + 1, by) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_RIGHT);
                if (b.getID(bx - 1, by) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_LEFT);
                
                if (b.getID(bx + 1, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_RIGHT);
                if (b.getID(bx - 1, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_LEFT);
                if (b.getID(bx + 1, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_RIGHT);
                if (b.getID(bx - 1, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_LEFT);
                
                blockRenderer.draw(g, type, id, flags, drawX, drawY, blockSize);
            }
        }
        
        BlockData[] data = ghost.getBlockData();
        
        for (BlockData d : data) 
        {
            if (!b.inBounds(d)) continue;
            
            int drawX = startX + d.getX()*blockSize;
            int drawY = startY - d.getY()*blockSize;
            
            int type = d.getValue();
            int id = d.getID();
            
            int flags = 0;
            
            for (BlockData d2 : data) 
            {
                if (d2.getX() == d.getX() && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP);
                }
                
                if (d2.getX() == d.getX() && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_RIGHT);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_RIGHT);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_RIGHT);
                }
            }
            
            ghostRenderer.draw(g, type, id, flags, drawX, drawY, blockSize);
        }
        
        data = p.getBlockData();
        
        for (BlockData d : data) 
        {
            if (!b.inBounds(d)) continue;
            
            int drawX = startX + d.getX()*blockSize;
            int drawY = startY - d.getY()*blockSize;
            
            int type = d.getValue();
            int id = d.getID();
            
            int flags = 0;
            
            for (BlockData d2 : data) 
            {
                if (d2.getX() == d.getX() && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP);
                }
                
                if (d2.getX() == d.getX() && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_RIGHT);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()+1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_RIGHT);
                }
                
                if (d2.getX() == d.getX()-1 && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_LEFT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_LEFT);
                }
                
                if (d2.getX() == d.getX()+1 && d2.getY() == d.getY()-1) 
                {
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_RIGHT);
                    flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_RIGHT);
                }
            }
            
            blockRenderer.draw(g, type, id, flags, drawX, drawY, blockSize);
        }
        
        g.setColor(Color.GRAY.darker());
        
        int border = 4;
        int doubleBorder = border * 2;
        
        g.fillRect(x - border, y - border, border, height + doubleBorder);
        g.fillRect(x + width, y - border, border, height + doubleBorder);
        
        g.fillRect(x - border, y - border, width + doubleBorder, border);
        g.fillRect(x - border, y + height, width + doubleBorder, border);
    }
}
