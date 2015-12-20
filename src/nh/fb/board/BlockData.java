package nh.fb.board;

public class BlockData
{
    private final int x, y, val, id;
    
    public BlockData(int x, int y, int val, int id) 
    {
        this.x = x;
        this.y = y;
        this.val = val;
        this.id = id;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public int getValue() { return val; }
    
    public int getID() { return id; }
}
