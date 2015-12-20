package nh.fb.util;

import java.util.Random;

import nh.fb.board.PieceType;
import nh.fb.board.PieceTypeGenerator;

public class BagGenerator implements PieceTypeGenerator
{
    private int index;
    private int[] next;
    
    private Random random;
    
    public BagGenerator(long seed) 
    {
        random = new Random(seed);
        
        next = new int[7];
        
        index = 0;
        
        genNextBag();
    }
    
    private void genNextBag() 
    {
        for (int i = 0; i < next.length; i++) 
        {
            next[i] = i % 7;
        }
        
        for (int i = 0; i < next.length; i++) 
        {
            int j = random.nextInt(next.length);
            
            int tmp = next[i];
            
            next[i] = next[j];
            next[j] = tmp;
        }
    }
    
    public void printBag() 
    {
        for (int i = 0; i < next.length; i++) 
        {
            System.out.print(getPieceType(next[i]).getName());
            
            if (i != next.length - 1) System.out.print(", ");
        }
        
        System.out.println();
    }

    @Override
    public PieceType nextPieceType()
    {
        PieceType type = getPieceType(next[index]);
        
        index++;
        
        if (index >= next.length) 
        {
            index = 0;
            genNextBag();
        }
        
        return type;
    }
    
    private PieceType getPieceType(int i) 
    {
        switch (i) 
        {
            case 0: return PieceType.I;
            case 1: return PieceType.Z;
            case 2: return PieceType.S;
            case 3: return PieceType.L;
            case 4: return PieceType.J;
            case 5: return PieceType.O;
            default: return PieceType.T;
        }
    }
}
