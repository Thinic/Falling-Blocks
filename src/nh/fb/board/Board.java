package nh.fb.board;

/*
 * Board for Falling Blocks
 */
public class Board
{
    private int width, height;
    private int[][] value, id;
    
    public Board(int w, int h) 
    {
        width = w;
        height = h;
        
        value = new int[width][height];
        id = new int[width][height];
    }
    
    /*
     * get value (what type/color) at board location
     */
    public int getValue(int x, int y) 
    {
        if (!inBounds(x, y)) return 0;
        
        return value[x][y];
    }
    
    /*
     * gets id, for connection visuals
     */
    public int getID(int x, int y) 
    {
        if (!inBounds(x, y)) return 0;
        
        return id[x][y];
    }
    
    public void setBlock(BlockData data) 
    {
        if (!inBounds(data.getX(), data.getY())) return;
        
        value[data.getX()][data.getY()] = data.getValue();
        id[data.getX()][data.getY()] = data.getID();
    }
    
    public void clearBlock(int x, int y) 
    {
        setBlock(new BlockData(x, y, 0, 0));
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    public boolean inBounds(BlockData bd) 
    {
        return inBounds(bd.getX(), bd.getY());
    }
    
    /*
     * checks if piece is on board, and not covering another piece
     */
    public boolean isPieceValid(Piece p) 
    {
        BlockData[] data = p.getBlockData();
        
        for (BlockData d : data) 
        {
            if (!inBounds(d)) return false;
            if (getValue(d.getX(), d.getY()) != 0) return false;
        }
        
        return true;
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight() 
    {
        return height;
    }

    public boolean isLineFull(int y)
    {
        for (int x = 0; x < getWidth(); x++) if (getValue(x, y) == 0) return false;
        
        return true;
    }

    public void clearLineAndDropAbove(int yStart)
    {
        for (int y = yStart; y < getHeight() - 2; y++) 
        {
            for (int x = 0; x < getWidth(); x++) 
            {
                value[x][y] = value[x][y+1];
                id[x][y] = id[x][y+1];
            }
        }
    }
}
