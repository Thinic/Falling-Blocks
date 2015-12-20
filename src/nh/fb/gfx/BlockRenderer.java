package nh.fb.gfx;

import java.awt.Graphics2D;

public abstract class BlockRenderer
{   
    public static final int FLAG_SAME_ID_UP           = 0x0001;
    public static final int FLAG_SAME_ID_DOWN         = 0x0002;
    public static final int FLAG_SAME_ID_LEFT         = 0x0004;
    public static final int FLAG_SAME_ID_RIGHT        = 0x0008;
    
    public static final int FLAG_SAME_ID_UP_LEFT      = 0x0010;
    public static final int FLAG_SAME_ID_UP_RIGHT     = 0x0020;
    public static final int FLAG_SAME_ID_DOWN_LEFT    = 0x0040;
    public static final int FLAG_SAME_ID_DOWN_RIGHT   = 0x0080;
    
    public static final int FLAG_SAME_TYPE_UP         = 0x0100;
    public static final int FLAG_SAME_TYPE_DOWN       = 0x0200;
    public static final int FLAG_SAME_TYPE_LEFT       = 0x0400;
    public static final int FLAG_SAME_TYPE_RIGHT      = 0x0800;
    
    public static final int FLAG_SAME_TYPE_UP_LEFT    = 0x1000;
    public static final int FLAG_SAME_TYPE_UP_RIGHT   = 0x2000;
    public static final int FLAG_SAME_TYPE_DOWN_LEFT  = 0x4000;
    public static final int FLAG_SAME_TYPE_DOWN_RIGHT = 0x8000;
    
    public static final int FLAGS_ALL = 0xFFFF;
    
    public abstract void draw(Graphics2D g, int type, int id, int flags, int x, int y, int size);
    
    public static boolean hasFlag(int flags, int flagsToTest) 
    {
        return ((flags & flagsToTest) == flagsToTest);
    }
    
    public static int getAddFlag(int flags, int flagToAdd) 
    {
        return (flags | flagToAdd);
    }
    
    public static int getRemoveFlag(int flags, int flagToRemove) 
    {
        return (flags & (FLAGS_ALL ^ flagToRemove));
    }
}
