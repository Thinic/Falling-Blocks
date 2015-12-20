package nh.fb;

import nh.fb.board.BlockData;
import nh.fb.board.Board;
import nh.fb.board.Piece;
import nh.fb.board.PieceGenerator;
import nh.fb.board.PieceType;
import nh.fb.board.PieceTypeGenerator;
import nh.fb.util.BagGenerator;

/*
 * Falling Blocks game logic
 */
public class FallingBlocks
{
    private PieceGenerator pieceGen;
    private PieceTypeGenerator pieceTypeGen;
    
    private PieceType[] pieceBuffer;
    
    private Piece currentPiece;
    
    private GameSettings settings;
    private Board board;
    
    private int fallTicks, fallTicksAmt;
    
    public FallingBlocks(GameSettings settings) 
    {
        this.settings = settings;
        
        board = new Board(settings.getBoardWidth(), settings.getBoardHeight());
        
        pieceGen = new PieceGenerator();
        
        pieceTypeGen = new BagGenerator(System.nanoTime());
        
        pieceBuffer = new PieceType[5];
        fillPieceTypeBuffer();
        
        createNextPiece();
        
        reset();
    }
    
    public void reset() 
    {
        fallTicksAmt = 20;
    }
    
    public GameSettings getSettings() { return settings; }
    
    public Board getBoard() { return board; }
    
    public Piece getCurrentPiece() { return currentPiece; }
    
    /*
     * one tick worth of logic
     */
    public void update() 
    {
        fallTicks++;
        
        if (fallTicks >= fallTicksAmt) 
        {
            resetFallTime();
            boolean movedDown = movePieceDownNaturally();
            
            if (!movedDown) 
            {
                lockAndGetNextPiece();
            }
        }
        
        clearLines();
    }
    
    public void clearLines() 
    {
        for (int y = board.getHeight() - 1; y >= 0; y--) 
        {
            if (board.isLineFull(y)) 
            {
                board.clearLineAndDropAbove(y);
            }
        }
    }
    
    public void lockAndGetNextPiece()
    {
        lockPiece();
        createNextPiece();
    }

    public void resetFallTime() 
    {
        fallTicks = 0;
    }
    
    private void lockPiece() 
    {
        BlockData[] data = currentPiece.getBlockData();
        
        for (BlockData b : data) 
        {
            board.setBlock(b);
        }
    }
    
    /*
     * use these methods instead of manually moving piece
     * 
     * TODO add rotation correction
     */
    public boolean rotatePieceCW() 
    {
        currentPiece.rotate(PieceType.ROT_CW);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.rotate(PieceType.ROT_CCW);
        
        return false;
    }
    
    public boolean rotatePieceCCW() 
    {
        currentPiece.rotate(PieceType.ROT_CCW);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.rotate(PieceType.ROT_CW);
        
        return false;
    }
    
    public boolean rotatePiece180() 
    {
        currentPiece.rotate(PieceType.ROT_CW);
        currentPiece.rotate(PieceType.ROT_CW);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.rotate(PieceType.ROT_CCW);
        currentPiece.rotate(PieceType.ROT_CCW);
        
        return false;
    }
    
    public boolean movePieceLeft() 
    {
        currentPiece.move(-1, 0);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.move(1, 0);
        
        return false;
    }
    
    public boolean movePieceRight() 
    {
        currentPiece.move(1, 0);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.move(-1, 0);
        
        return false;
    }
    
    public boolean movePieceDown() 
    {
        currentPiece.move(0, -1);
        
        if (board.isPieceValid(currentPiece)) 
        {
            return true;
        }
        
        currentPiece.move(0, 1);
        
        return false;
    }
    
    private boolean movePieceDownNaturally() 
    {
        return movePieceDown();
    }
    
    private void createNextPiece() 
    {
        /*
         * TODO calculate position
         */
        PieceType type = getNextPieceType();
        
        currentPiece = pieceGen.create(type, board.getWidth()/2 - (int)Math.ceil(type.getWidth()/2.0), board.getHeight() - type.getTopOffset(0) - 1);
        
        pushPieceTypeBuffer();
    }
    
    public PieceType getNextPieceType() { return getPieceTypeBuffer(0); }
    
    public PieceType getPieceTypeBuffer(int index) { return pieceBuffer[index]; }
    
    public int getPieceTypeBufferSize() { return pieceBuffer.length; }
    
    private void pushPieceTypeBuffer() 
    {
        for (int i = 0; i < pieceBuffer.length - 1; i++) 
        {
            pieceBuffer[i] = pieceBuffer[i+1];
        }
        
        pieceBuffer[pieceBuffer.length - 1] = pieceTypeGen.nextPieceType();
    }
    
    private void fillPieceTypeBuffer() 
    {
        for (int i = 0; i < pieceBuffer.length; i++) 
        {
            pieceBuffer[i] = pieceTypeGen.nextPieceType();
        }
    }

    public void hardDropPiece()
    {
        while (movePieceDown());
        
        lockAndGetNextPiece();
    }
    
    private void setPiece(Piece p) { currentPiece = p; }

    public Piece getGhostPiece()
    {
        Piece g = currentPiece.copy();
        
        Piece p = currentPiece;
        
        setPiece(g);
        
        while (movePieceDown());
        
        setPiece(p);
        
        return g;
    }
}
