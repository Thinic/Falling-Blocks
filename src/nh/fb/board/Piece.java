package nh.fb.board;

/*
 * actual object that goes on the board
 */
public class Piece
{
    private PieceType type;
    private int rot;
    
    private int id;
    
    private int x, y;
    
    public Piece(PieceType type, int id) 
    {
        this.type = type;
        rot = x = y = 0;
        
        this.id = id;
    }
    
    public Piece copy() 
    {
        Piece p = new Piece(type, id);
        
        p.rot = rot;
        p.x = x;
        p.y = y;
        
        return p;
    }
    
    public PieceType getType() { return type; }
    
    public int getID() { return id; }
    
    public int getRotation() { return rot; }
    
    public void setRotation(int rot) 
    {
        rot %= PieceType.ROT_TOTAL;
        rot += PieceType.ROT_TOTAL;
        rot %= PieceType.ROT_TOTAL;
        
        this.rot = rot;
    }
    
    public void rotate(int dRot) 
    {
        setRotation(rot + dRot);
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    
    public void move(int dx, int dy) 
    {
        x += dx;
        y += dy;
    }
    
    /*
     * list of positions to set to piece value and id
     */
    public BlockData[] getBlockData() 
    {
        BlockData[] blocks = new BlockData[type.getNumBlocks()];
        
        int i = 0;
        
        for (int x = 0; x < type.getWidth(); x++) 
        {
            for (int y = 0; y < type.getHeight(); y++) 
            {
                int val;
                
                if ((val = type.getValue(x, y, rot)) != 0) 
                {
                    blocks[i++] = new BlockData(getX() + x, getY() + y, val, id);
                }
            }
        }
        
        return blocks;
    }
    
    public int getLeftOffset() { return type.getLeftOffset(rot); }
    public int getRightOffset() { return type.getRightOffset(rot); }
    public int getTopOffset() { return type.getTopOffset(rot); }
    public int getBottomOffset() { return type.getBottomOffset(rot); }

}
