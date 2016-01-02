package nh.fb;

import nh.fb.board.BlockData;
import nh.fb.board.Board;
import nh.fb.board.Piece;
import nh.fb.board.PieceGenerator;
import nh.fb.board.PieceType;
import nh.fb.board.PieceTypeGenerator;
import nh.fb.util.BagGenerator;

public class FallingBlocksGame
{
    private PieceGenerator pieceGen;
    private PieceTypeGenerator pieceTypeGen;
    
    private PieceType[] pieceBuffer;
    
    private Piece current;
    
    private GameSettings settings;
    private Board board;
    
    private int ticksSinceLastFall;
    
    private int ticksSinceOnGround;
    
    public FallingBlocksGame(GameSettings settings) 
    {
        this.settings = settings;
        
        board = new Board(settings.getBoardWidth(), settings.getBoardHeight());
        
        pieceGen = new PieceGenerator();
        
        pieceTypeGen = new BagGenerator(System.nanoTime());
        
        pieceBuffer = new PieceType[5];
        fillPieceTypeBuffer();
        
        createNextPiece();
    }
    
    public GameSettings getGameSettings() { return settings; }
    
    public Board getBoard() { return board; }
    
    public Piece getPiece() { return current; }
    
    public Piece getGhostPiece() 
    {
        Piece p = current.copy();
        
        while (board.isPieceValid(p)) 
        {
            p.move(0, -1);
        }
        
        p.move(0, 1);
        
        return p;
    }
    
    public PieceType getBufferAt(int index) { return pieceBuffer[index]; }
    
    public int getBufferSize() { return pieceBuffer.length; }
    
    public void update() 
    {
        ticksSinceLastFall++;
        
        checkForLines();
        
        if (isOnGround()) 
        {
            ticksSinceLastFall = 0;
            
            ticksSinceOnGround++;
            
            if (ticksSinceOnGround >= settings.getLockWaitTicks()) 
            {
                lockPiece();
                
                ticksSinceOnGround = 0;
            }
        }
        else 
        {
            ticksSinceOnGround = 0;
        }
        
        if (ticksSinceLastFall >= settings.getInitialFallWaitTicks()) 
        {
            movePieceDownNaturally();
            ticksSinceLastFall = 0;
        }
    }
    
    public boolean moveDown() 
    { 
        current.move(0, -1);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.move(0, 1);
        
        return false; 
    }
    
    public boolean moveLeft() 
    {
        current.move(-1, 0);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.move(1, 0);
        
        return false; 
    }
    
    public boolean moveRight() 
    {
        current.move(1, 0);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.move(-1, 0);
        
        return false; 
    }
    
    public boolean rotateCW() 
    {
        current.rotate(PieceType.ROT_CW);
        
        tryToValidatePiece(PieceType.ROT_CW);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.rotate(PieceType.ROT_CCW);
        
        return false; 
    }
    
    public boolean rotateCCW() 
    {
        current.rotate(PieceType.ROT_CCW);
        
        tryToValidatePiece(PieceType.ROT_CCW);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.rotate(PieceType.ROT_CW);
        
        return false; 
    }
    
    private void tryToValidatePiece(int rotAmt) 
    {
        if (board.isPieceValid(current)) return;
        
        int rotCurrent = current.getRotation();
        int rotOld = rotCurrent - rotAmt;
        rotOld += PieceType.ROT_TOTAL;
        rotOld %= PieceType.ROT_TOTAL;
        
        PieceType type = current.getType();
        
        /*
         * move left
         */
        int moveAmount = type.getLeftOffset(rotCurrent) - type.getLeftOffset(rotOld);
        
        current.move(-moveAmount, 0);
        if (board.isPieceValid(current)) return;
        current.move(moveAmount, 0);
        
        /*
         * move right
         */
        moveAmount = type.getRightOffset(rotCurrent) - type.getRightOffset(rotOld);
        
        current.move(-moveAmount, 0);
        if (board.isPieceValid(current)) return;
        current.move(moveAmount, 0);
        
        /*
         * move up
         */
        moveAmount = type.getTopOffset(rotCurrent) - type.getTopOffset(rotOld);
        
        current.move(0, -moveAmount);
        if (board.isPieceValid(current)) return;
        current.move(0, moveAmount);
        
        /*
         * move down
         */
        moveAmount = type.getBottomOffset(rotCurrent) - type.getBottomOffset(rotOld);
        
        current.move(0, -moveAmount);
        if (board.isPieceValid(current)) return;
        current.move(0, moveAmount);
    }
    
    public boolean hardDrop() 
    {
        while (board.isPieceValid(current)) 
        {
            current.move(0, -1);
        }
        
        current.move(0, 1);
        
        lockPiece();
        
        return true;
    }
    
    private void checkForLines() 
    {
        for (int y = board.getHeight() - 1; y >= 0; y--) 
        {
            if (board.isLineFull(y)) 
            {
                board.clearLineAndDropAbove(y);
            }
        }
    }
    
    private void lockPiece() 
    {
        BlockData[] data = current.getBlockData();
        
        for (BlockData b : data) 
        {
            board.setBlock(b);
        }
        
        createNextPiece();
    }
    
    private boolean movePieceDownNaturally() 
    {
        current.move(0, -1);
        
        if (board.isPieceValid(current)) 
        {
            return true;
        }
        
        current.move(0, 1);
        
        return false; 
    }
    
    private boolean isOnGround() 
    {
        current.move(0, -1);
        
        boolean valid = board.isPieceValid(current);
        
        current.move(0, 1);
        
        return !valid; 
    }
    
    private void fillPieceTypeBuffer() 
    {
        for (int i = 0; i < pieceBuffer.length; i++) 
        {
            pieceBuffer[i] = pieceTypeGen.nextPieceType();
        }
    }
    
    private void createNextPiece() 
    {
        genPieceAtTop();
        moveBufferUp();
        fillBufferLastPlace();
    }
    
    private void genPieceAtTop() 
    {
        PieceType type = pieceBuffer[0];
        
        current = pieceGen.create(type, 
                board.getWidth()/2 - (int)Math.ceil(type.getWidth()/2.0), 
                board.getHeight() - type.getTopOffset(0) - 1);
    }
    
    private void moveBufferUp() 
    {
        for (int i = 0; i < pieceBuffer.length - 1; i++) 
        {
            pieceBuffer[i] = pieceBuffer[i + 1];
        }
    }
    
    private void fillBufferLastPlace() 
    {
        pieceBuffer[pieceBuffer.length - 1] = pieceTypeGen.nextPieceType();
    }
}
