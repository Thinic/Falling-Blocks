package nh.fb.board;

/*
 * piece shape skeleton
 */
public class PieceType
{
    public static final int EMPTY = 0;
    
    public static final int ROT_CW = 1;
    public static final int ROT_CCW = -1;
    
    public static final int ROT_0 = 0;
    public static final int ROT_90 = 1;
    public static final int ROT_180 = 2;
    public static final int ROT_270 = 3;
    public static final int ROT_TOTAL = 4;
    
    public static final int I_VALUE = 1;
    public static final int T_VALUE = 2;
    public static final int Z_VALUE = 3;
    public static final int S_VALUE = 4;
    public static final int J_VALUE = 5;
    public static final int L_VALUE = 6;
    public static final int O_VALUE = 7;
    
    public static final int JUNK_VALUE = 0xFF;
    
    /*
     * 4 x 1 piece, I block
     */
    public static final PieceType I = new PieceType(new int[][][] {
            {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
            }, {
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 }
            }, {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
            }, {
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 }
            }
    }, "I");
    
    /*
     * 3 x 2 piece, T block
     */
    public static final PieceType T = new PieceType(new int[][][] {
            {
                { 0, 0, 0 },
                { 0, 2, 0 },
                { 2, 2, 2 }
            }, {
                { 0, 2, 0 },
                { 0, 2, 2 }, 
                { 0, 2, 0 }
            }, {
                { 0, 0, 0 },
                { 2, 2, 2 },
                { 0, 2, 0 }
            }, {
                { 0, 2, 0 },
                { 2, 2, 0 }, 
                { 0, 2, 0 }
            }
    }, "T");
    
    /*
     * 3 x 2 piece, Z block
     */
    public static final PieceType Z = new PieceType(new int[][][] {
            {
                { 0, 0, 0 },
                { 3, 3, 0 },
                { 0, 3, 3 }
            }, {
                { 0, 3, 0 },
                { 3, 3, 0 }, 
                { 3, 0, 0 }
            }, {
                { 0, 0, 0 },
                { 3, 3, 0 },
                { 0, 3, 3 }
            }, {
                { 0, 3, 0 },
                { 3, 3, 0 }, 
                { 3, 0, 0 }
            }
    }, "Z");
    
    /*
     * 3 x 2 piece, S block
     */
    public static final PieceType S = new PieceType(new int[][][] {
            {
                { 0, 0, 0 },
                { 0, 4, 4 },
                { 4, 4, 0 }
            }, {
                { 0, 4, 0 },
                { 0, 4, 4 }, 
                { 0, 0, 4 }
            }, {
                { 0, 0, 0 },
                { 0, 4, 4 },
                { 4, 4, 0 }
            }, {
                { 0, 4, 0 },
                { 0, 4, 4 }, 
                { 0, 0, 4 }
            }
    }, "S");
    
    /*
     * 3 x 2 piece, J block
     */
    public static final PieceType J = new PieceType(new int[][][] {
            {
                { 0, 0, 0 },
                { 5, 5, 5 },
                { 0, 0, 5 }
            }, {
                { 0, 5, 0 },
                { 0, 5, 0 }, 
                { 5, 5, 0 }
            }, {
                { 0, 0, 0 },
                { 5, 0, 0 },
                { 5, 5, 5 }
            }, {
                { 0, 5, 5 },
                { 0, 5, 0 }, 
                { 0, 5, 0 }
            }
    }, "J");
    
    /*
     * 3 x 2 piece, L block
     */
    public static final PieceType L = new PieceType(new int[][][] {
            {
                { 0, 0, 0 },
                { 6, 6, 6 },
                { 6, 0, 0 }
            }, {
                { 6, 6, 0 },
                { 0, 6, 0 }, 
                { 0, 6, 0 }
            }, {
                { 0, 0, 0 },
                { 0, 0, 6 },
                { 6, 6, 6 }
            }, {
                { 0, 6, 0 },
                { 0, 6, 0 }, 
                { 0, 6, 6 }
            }
    }, "L");
    
    /*
     * 2 x 2 piece, O block
     */
    public static final PieceType O = new PieceType(new int[][][] {
            {
                { 7, 7 },
                { 7, 7 },
            }, {
                { 7, 7 },
                { 7, 7 },
            }, {
                { 7, 7 },
                { 7, 7 },
            }, {
                { 7, 7 },
                { 7, 7 },
            }
    }, "O");
    
    private int numBlocks;
    
    private String name;
    private int[][][] map;
    
    /*
     * id -> [rot][y][x]
     * 
     * Each rotation should have the same amount of blocks
     */
    public PieceType(int[][][] map, String name) 
    {
        this.map = map;
        this.name = name;
        
        numBlocks = calcNumBlocks();
    }
    
    public int getWidth() { return map[0][0].length; }
    
    public int getHeight() { return map[0].length; }
    
    /*
     * get value of position
     * if out of bounds returns empty
     * 
     * lower left is 0, 0
     */
    public int getValue(int x, int y, int rot) 
    {
        if (rot >= 0 && rot < map.length) 
        {
            if (y >= 0 && y < map[rot].length) 
            {
                if (x >= 0 && x < map[rot][y].length) 
                {
                    return map[rot][getHeight() - 1 - y][x];
                }
            }
        }
        
        return EMPTY;
    }
    
    /*
     * returns how many blocks away from the left side do solids appear
     */
    public int getLeftOffset(int rot) 
    {
        for (int x = 0; x < getWidth(); x++) 
        {
            for (int y = 0; y < getHeight(); y++) 
            {
                if (getValue(x, y, rot) != 0) return x;
            }
        }
        
        return getWidth() - 1;
    }
    
    /*
     * empty from right
     */
    public int getRightOffset(int rot) 
    {
        for (int x = getWidth() - 1; x >= 0; x--) 
        {
            for (int y = 0; y < getHeight(); y++) 
            {
                if (getValue(x, y, rot) != 0) return getWidth() - 1 - x;
            }
        }
        
        return 0;
    }
    
    /*
     * empty from top
     */
    public int getTopOffset(int rot) 
    {
        for (int y = getHeight() - 1; y >= 0; y--) 
        {
            for (int x = 0; x < getWidth(); x++) 
            {
                if (getValue(x, y, rot) != 0) return getHeight() - 1 - y;
            }
        }
        
        return 0;
    }
    
    /*
     * empty from bottom
     */
    public int getBottomOffset(int rot) 
    {
        for (int y = 0; y < getHeight(); y++) 
        {
            for (int x = 0; x < getWidth(); x++) 
            {
                if (getValue(x, y, rot) != 0) return y;
            }
        }
        
        return getHeight() - 1;
    }
    
    /*
     * get number of blocks in a piece (should be regardless of rotation)
     * 
     * with standard piece types, should be 4
     */
    public int getNumBlocks() { return numBlocks; }
    
    public String getName() { return name; }
    
    private int calcNumBlocks() 
    {
        int total = 0;
        
        for (int x = 0; x < getWidth(); x++) 
        {
            for (int y = 0; y < getHeight(); y++) 
            {
                if (getValue(x, y, 0) > 0) total++;
            }
        }
        
        return total;
    }
}
