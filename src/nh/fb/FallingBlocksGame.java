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
    private static final int SCORE_TIME_DROP = 1;
    private static final int SCORE_HARD_DROP = 5;
    private static final int SCORE_SINGLE = 80;
    private static final int SCORE_DOUBLE = 200;
    private static final int SCORE_TRIPLE = 500;
    private static final int SCORE_4_LINE = 1000;
    private static final int SCORE_T_SPIN = 900;
    
    private PieceGenerator pieceGen;
    private PieceTypeGenerator pieceTypeGen;
    
    private PieceType[] pieceBuffer;
    
    private Piece current;
    private PieceType holdPieceType;
    
    private GameSettings settings;
    private Board board;
    
    private int ticksSinceLastFall, ticksBeforeFall;
    
    private int ticksSinceOnGround;
    
    private int timer, score, 
                lines, linesToNextLevel,
                level;
    
    private boolean pause = false, gameEnded = false;
    
    private boolean canHold = true;
    
    private int numJunkToAdd = 0;
    private int timeJunkToAdd = 0;
    
    private int combo = 0;
    
    private FallingBlocksGame game2p;
    
    public FallingBlocksGame(GameSettings settings) 
    {
        this.settings = settings;
        
        game2p = null;
        
        board = new Board(settings.getBoardWidth(), settings.getBoardHeight());
        
        pieceGen = new PieceGenerator();
        
        pieceTypeGen = new BagGenerator(System.nanoTime());
        
        timer = 0;
        
        score = 0;
        
        lines = 0;
        linesToNextLevel = 4;
        
        level = 0;
        
        ticksBeforeFall = settings.getInitialFallWaitTicks();
        
        pieceBuffer = new PieceType[5];
        fillPieceTypeBuffer();
        
        createNextPiece();
        
        //TODO remove
//        addJunkLine();
//        addJunkLine();
//        addJunkLine();
//        addJunkLine();
//        addJunkLine();
    }
    
    public void setGame2Player(FallingBlocksGame game) 
    {
        game2p = game;
    }
    
    private void addCombo() 
    {
        combo++;
    }
    
    private void resetCombo() 
    {
        combo = 0;
    }
    
    private void addJunkLinesToBuffer(int num) 
    {
        numJunkToAdd += num;
        
        timeJunkToAdd = 3 * settings.getTicksPerSecond();
        
        System.out.println(numJunkToAdd + " " + timeJunkToAdd);
    }
    
    private void removeJunkLinesToBuffer(int num) 
    {
        addJunkLinesToBuffer(-num);
    }
    
    private void calcJunkLines(int num) 
    {
        if (game2p != null) 
        {
            int linesToAdd = num;
            
            if (numJunkToAdd == 0) 
            {
                game2p.addJunkLinesToBuffer(linesToAdd);  
            }
            else if (numJunkToAdd >= linesToAdd) 
            {
                this.removeJunkLinesToBuffer(linesToAdd);
            }
            else if (numJunkToAdd < linesToAdd) 
            {
                int send = linesToAdd - numJunkToAdd;
                
                this.removeJunkLinesToBuffer(numJunkToAdd);
                game2p.addJunkLinesToBuffer(send);  
            }
        }
    }
    
    private void addJunkLine() 
    {
        if (gameEnded) return;
        
        // move blocks up
        for (int y = board.getHeight(); y >= 1; y--) 
        {
            for (int x = 0; x < board.getWidth(); x++) 
            {
                int value = board.getValue(x, y - 1);
                int id = board.getID(x, y - 1);
                
                board.setBlock(new BlockData(x, y, value, id));
            }
        }
        
        for (int x = 0; x < board.getWidth(); x++) 
        {
            int value = PieceType.JUNK_VALUE;
            int id = pieceGen.genID();
            
            board.setBlock(new BlockData(x, 0, value, id));
        }
        
        // TODO figure out why it won't work without this line
        // without this line it doesn't work?
        board.clearBlock((int)(Math.random()*board.getWidth()), 0);
        
        while (!gameEnded && !board.isPieceValid(current)) 
        {
            if (!board.isPieceInBounds(current)) 
            {
                gameEnded = true;
            }
            else 
            {
                current.move(0, 1);
            }
        }
    }
    
    public boolean holdPiece() 
    {
        if (!canHold) return false;
        
        PieceType oldHold = holdPieceType;
        
        holdPieceType = current.getType();
        
        if (oldHold == null) createNextPiece();
        else genPieceAtTop(oldHold);
        
        canHold = false;
        
        return true;
    }
    
    public boolean hasHoldPiece() 
    {
        return holdPieceType != null;
    }
    
    public PieceType getHoldPiece() 
    {
        return holdPieceType;
    }
    
    public String getTime() 
    {
        float seconds = timer / (float)settings.getTicksPerSecond();
        
        int sec = (int)seconds;
        int frac = (int)((seconds - sec) * 100);
        int minutes = sec / 60;
        
        sec -= minutes*60;
        
        return minutes + ":" + String.format("%02d", sec) + "." + String.format("%02d", frac);
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
        if (pause || gameEnded) return;
        
        timer++;
        
        timeJunkToAdd--;
        if (timeJunkToAdd <= 0) 
        {
            for (int i = 0; i < numJunkToAdd; i++) 
            {
                addJunkLine();
            }
            
            numJunkToAdd = 0;
        }
        
        while (lines >= linesToNextLevel) 
        {
            level++;
            
            linesToNextLevel += 4 + (int)((level + 1) * 1.5);
            
            ticksBeforeFall = (int) Math.floor(1.0 / (1 + 0.03*level*level) * settings.getInitialFallWaitTicks());
        }
        
        ticksSinceLastFall++;
        
        if (isOnGround()) 
        {
            ticksSinceLastFall = 0;
            
            ticksSinceOnGround++;
            
            if (ticksSinceOnGround >= settings.getLockWaitTicks()) 
            {
                lockPiece();
                
                score += SCORE_TIME_DROP * (level+1);
                
                ticksSinceOnGround = 0;
            }
        }
        else 
        {
            ticksSinceOnGround = 0;
        }
        
        boolean move = true;
        
        while (move && ticksSinceLastFall >= ticksBeforeFall) 
        {
            move = movePieceDownNaturally();
            ticksSinceLastFall = 0;
        }
    }
    
    public boolean moveDown() 
    { 
        if (pause || gameEnded) return false;
        
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
        if (pause || gameEnded) return false;
        
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
        if (pause || gameEnded) return false;
        
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
        if (pause || gameEnded) return false;
        
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
        if (pause || gameEnded) return false;
        
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
        
        current.move(moveAmount, 0);
        if (board.isPieceValid(current)) return;
        current.move(-moveAmount, 0);
        
        /*
         * move up
         */
        moveAmount = type.getTopOffset(rotCurrent) - type.getTopOffset(rotOld);
        
        current.move(0, moveAmount);
        if (board.isPieceValid(current)) return;
        current.move(0, -moveAmount);
        
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
        if (pause || gameEnded) return false;
        
        while (board.isPieceValid(current)) 
        {
            current.move(0, -1);
        }
        
        current.move(0, 1);
        
        lockPiece();
        
        score += SCORE_HARD_DROP * (level+1);
        
        return true;
    }
    
    private boolean checkForLines(boolean validAbove) 
    {
        int numLines = 0;
        
        for (int y = board.getHeight() - 1; y >= 0; y--) 
        {
            if (board.isLineFull(y)) 
            {
                board.clearLineAndDropAbove(y);
                
                numLines++;
            }
        }
        
        lines += numLines;
        
        boolean isTSpin = false;
        
        if (current.getType() == PieceType.T) 
        {
            if (current.getRotation() == 0 || current.getRotation() == 2) 
            {
                if (numLines == 2) 
                {
                    isTSpin = !validAbove;
                }
            } 
        }
        
        if (numLines > 0) 
        {
            addCombo();
            
            if (combo > 1) 
            {
                calcJunkLines(1);//Math.min(combo - 1, 3));
            }
        }
        
        if (numLines == 4 || isTSpin) 
        {
            calcJunkLines(3);
        }
        else if (numLines > 1) 
        {
            calcJunkLines(numLines - 1);
        }
        
        if (isTSpin) 
        {
            score += SCORE_T_SPIN * (level+1) * (combo);
        }
        else 
        {
            switch (numLines) 
            {
                case 1: score += SCORE_SINGLE * (level+1) * (combo); break;
                case 2: score += SCORE_DOUBLE * (level+1) * (combo); break;
                case 3: score += SCORE_TRIPLE * (level+1) * (combo); break;
                case 4: score += SCORE_4_LINE * (level+1) * (combo); break;
                default: break;
            }
        }
        
        return numLines > 0;
    }
    
    private void lockPiece() 
    {
        boolean validAbove = isValidAbove();
        
        BlockData[] data = current.getBlockData();
        
        for (BlockData b : data) 
        {
            board.setBlock(b);
        }
        
        canHold = true;
        
        if (!checkForLines(validAbove)) resetCombo();
        
        createNextPiece();
    }
    
    private boolean isValidAbove() 
    {
        boolean b = false;
        
        current.move(0, 1);
        b = board.isPieceValid(current);
        current.move(0, -1);
        
        return b;
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
        genPieceAtTop(pieceBuffer[0]);
    }
    
    private void genPieceAtTop(PieceType type) 
    {
        current = pieceGen.create(type, 
                board.getWidth()/2 - (int)Math.ceil(type.getWidth()/2.0), 
                board.getHeight() - type.getHeight() + type.getTopOffset(0));
        
        if (!board.isPieceValid(current)) 
        {
            gameEnded = true;
        }
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

    public int getScore()
    {
        return score;
    }
    
    public int getLines() 
    {
        return lines;
    }
    
    public int getLinesToNextLevel() 
    {
        return linesToNextLevel;
    }
    
    public int getLevel() 
    {
        return level;
    }
    
    public boolean isPaused() 
    {
        return pause;
    }
    
    public void setPaused(boolean p) 
    {
        pause = p;
    }
    
    public boolean isGameEnded() 
    {
        return gameEnded;
    }
}
