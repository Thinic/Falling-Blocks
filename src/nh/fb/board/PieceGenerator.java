package nh.fb.board;


public class PieceGenerator
{
    private static int nextID = 1;
    
    public PieceGenerator() {} 
    
    /*
     * generates a piece
     */
    public Piece create(PieceType type, int x, int y) 
    {
        Piece p = new Piece(type, nextID++);
        
        p.setX(x);
        p.setY(y);
        
        return p;
    }
    
    public int genID() { return nextID++; }
}
