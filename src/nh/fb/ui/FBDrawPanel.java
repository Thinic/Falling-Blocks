package nh.fb.ui;

import java.awt.Color;
import java.awt.Graphics;

import nh.fb.FallingBlocksGame;
import nh.fb.board.Board;
import nh.fb.board.Piece;
import nh.fb.board.PieceType;
import nh.fb.gfx.BasicBlockRenderer;
import nh.fb.gfx.BlockRenderer;
import nh.fb.gfx.GhostBlockRenderer;
import nh.ui.UIPanel;

public class FBDrawPanel extends UIPanel
{
    public static final int SIZE_SMALL = 16;
    public static final int SIZE_MEDIUM = 24;
    public static final int SIZE_LARGE = 32;
    
    private FallingBlocksGame game;
    private int size;
    
    private final int holdWidth = 6;
    private final int holdHeight = 6;
    private final int holdYFromTop = 8;
    
    private BasicBlockRenderer blockRenderer = new BasicBlockRenderer();
    private GhostBlockRenderer ghostRenderer = new GhostBlockRenderer();
    
    public FBDrawPanel(FallingBlocksGame game, int size) 
    {
        setGame(game);
        this.size = size;
        
        setOffset(0, 0);
        setOffsetType(CENTER);
        
        resize();
    }
    
    public void setSize(int s) 
    {
        size = s;
    }
    
    public void setGame(FallingBlocksGame game) 
    {
        this.game = game;
    }
    
    private void resize() 
    {
        setMaximized(false);
        
        int gameWidth = game.getBoard().getWidth();
        int gameHeight = game.getBoard().getHeight();
        
        setWidth(getBlockSizeX(gameWidth + holdWidth*2));
        setHeight(getBlockSizeY(gameHeight + 2));
    }
    
    @Override
    public void drawElement(Graphics g) 
    {
        // not call super, so that it is transparent
        resize();
        
//        g.setColor(Color.GREEN);
//        g.fillRect(0, 0, getWidth(), getHeight());
        
        drawGameBackground(g);
        drawBoard(g);
        drawGhostPiece(g);
        drawPiece(g);
        drawNextPieces(g);
        drawGameOverlay(g);
    }
    
    private void drawNextPieces(Graphics g) 
    {
        drawNextPiece(g);
        
        drawNextPiece(g, 1);
        drawNextPiece(g, 2);
        drawNextPiece(g, 3);
    }
    
    private void drawNextPiece(Graphics g, int i) 
    {
        PieceType type = game.getBufferAt(i);
        
        int rot = 0;
        int id = 0;
        
        int boardx = holdWidth - 1;
        int width = game.getBoard().getWidth() + 2;
        int height = game.getBoard().getHeight() + 1 - 4*i;
        
        double offX = (4 - type.getWidth() + type.getLeftOffset(0) - type.getRightOffset(0)) / 2.0;
        double offY = (4 - type.getHeight() + type.getTopOffset(0) - type.getBottomOffset(0)) / 2.0;
        
        
        
        drawPieceType(g, blockRenderer, type, rot, id, boardx + width + offX, height - holdHeight - 1 + offY);
    }
    
    private void drawNextPiece(Graphics g) 
    {
        PieceType type = game.getBufferAt(0);
        
        int rot = 0;
        int id = 0;
        
        int boardx = holdWidth - 1;
        int width = game.getBoard().getWidth() + 2;
        int height = game.getBoard().getHeight() + 2;
        
        double offX = (4 - type.getWidth() + type.getLeftOffset(0) - type.getRightOffset(0)) / 2.0;
        double offY = (4 - type.getHeight() + type.getTopOffset(0) - type.getBottomOffset(0)) / 2.0;
        
        
        
        drawPieceType(g, blockRenderer, type, rot, id, boardx + width + offX, height - holdHeight - 1 + offY);
    }
    
    private void drawGhostPiece(Graphics g) 
    {
        Piece piece = game.getGhostPiece();
        
        int rot = piece.getRotation();
        int id = piece.getID();
        
        drawPieceType(g, ghostRenderer, piece.getType(), rot, id, piece.getX() + holdWidth, piece.getY() + 1);
    }
    
    private void drawPiece(Graphics g) 
    {
        Piece piece = game.getPiece();
        
        int rot = piece.getRotation();
        int id = piece.getID();
        
        drawPieceType(g, blockRenderer, piece.getType(), rot, id, piece.getX() + holdWidth, piece.getY() + 1);
    }
    
    private void drawPieceType(Graphics g, BlockRenderer br, PieceType type, int rot, int id, double drawx, double drawy) 
    {
        for (int x = 0; x < type.getWidth(); x++) 
        {
            for (int y = 0; y < type.getHeight(); y++) 
            {
                int val = type.getValue(x, y, rot);
                int flags = getFlagsForBlock(type, x, y, rot);
                
                if (val != 0) br.draw(g, val, id, flags, getBlockX(drawx + x), getBlockY(drawy + y + 1), size);
            }
        }
    }
    
    private void drawBoard(Graphics g)
    {
        final int tx = holdWidth;
        final int ty = 1;
        
        Board b = game.getBoard();
        
        for (int x = 0; x < b.getWidth(); x++) 
        {
            for (int y = 0; y < b.getHeight(); y++) 
            {
                int type = b.getValue(x, y);
                int id = b.getID(x, y);
                int flags = getFlagsForBlock(b, x, y);
                
                blockRenderer.draw(g, type, id, flags, getBlockX(tx + x), getBlockY(ty + y + 1), size);
            }
        }
    }

    private void drawGameBackground(Graphics g) 
    {
        g.setColor(Color.GRAY);
        
        int boardx = holdWidth - 1;
        int boardy = 0;
        int width = game.getBoard().getWidth() + 2;
        int height = game.getBoard().getHeight() + 2;
        
        fillRect(g, boardx, boardy, width, height);
        fillRect(g, 0, boardy + height - holdYFromTop, holdWidth, holdHeight);
        fillRect(g, boardx+width-1, boardy + height - holdYFromTop, holdWidth, holdHeight);
        fillRect(g, boardx+width-1, boardy+height-holdYFromTop - 5*3 + 2, holdWidth, 5*3 -1);
    }
    
    private void drawGameOverlay(Graphics g) 
    {
        g.setColor(Color.BLACK);
        
        int boardx = holdWidth - 1;
        int boardy = 0;
        int width = game.getBoard().getWidth() + 2;
        int height = game.getBoard().getHeight() + 2;
        
        drawRect(g, boardx, boardy, width, height);
        drawRect(g, 0, boardy + height - holdYFromTop, holdWidth, holdHeight);
        drawRect(g, boardx+width-1, boardy + height - holdYFromTop, holdWidth, holdHeight);
        drawRect(g, boardx+width-1, boardy+height-holdYFromTop - 5*3 + 2, holdWidth, 5*3 -1);
    }
    
    private void drawRect(Graphics g, double x, double y, double w, double h) 
    {
        fillRect(g, x, y, 1, h);
        fillRect(g, x, y, w, 1);
        fillRect(g, x + w - 1, y, 1, h);
        fillRect(g, x, y + h - 1, w, 1);
    }
    
    private void fillRect(Graphics g, double x, double y, double w, double h) 
    {
        int height = getBlockSizeY(h);
        
        g.fillRect(getBlockX(x), getBlockY(y) - height, getBlockSizeX(w), height);
    }
    
    private int getBlockX(double x) 
    {
        return getBlockSizeX(x);
    }
    
    private int getBlockY(double y) 
    {
        return getHeight() - getBlockSizeY(y);
    }
    
    private int getBlockSizeX(double x) 
    {
        return (int)(x * size);
    }
    
    private int getBlockSizeY(double y) 
    {
        return (int)(y * size);
    }
    
    private int getFlagsForBlock(Board b, int bx, int by) 
    {
        int type = b.getValue(bx, by);
        int id = b.getID(bx, by);
        
        int flags = 0;
        
        if (b.getValue(bx, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP);
        if (b.getValue(bx, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN);
        if (b.getValue(bx - 1, by) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_LEFT);
        if (b.getValue(bx + 1, by) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_RIGHT);
        
        if (b.getValue(bx + 1, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_RIGHT);
        if (b.getValue(bx - 1, by + 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_UP_LEFT);
        if (b.getValue(bx + 1, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_RIGHT);
        if (b.getValue(bx - 1, by - 1) == type) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_TYPE_DOWN_LEFT);
        
        if (b.getID(bx, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP);
        if (b.getID(bx, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN);
        if (b.getID(bx + 1, by) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_RIGHT);
        if (b.getID(bx - 1, by) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_LEFT);
        
        if (b.getID(bx + 1, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_RIGHT);
        if (b.getID(bx - 1, by + 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_LEFT);
        if (b.getID(bx + 1, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_RIGHT);
        if (b.getID(bx - 1, by - 1) == id) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_LEFT);
        
        return flags;
    }
    
    private int getFlagsForBlock(PieceType type, int x, int y, int rot) 
    {
        int flags = 0;
        
        if (type.getValue(x - 1, y, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_LEFT | BlockRenderer.FLAG_SAME_TYPE_LEFT);
        if (type.getValue(x + 1, y, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_RIGHT | BlockRenderer.FLAG_SAME_TYPE_RIGHT);
        if (type.getValue(x, y - 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN | BlockRenderer.FLAG_SAME_TYPE_DOWN);
        if (type.getValue(x, y + 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP | BlockRenderer.FLAG_SAME_TYPE_UP);
        
        if (type.getValue(x - 1, y - 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_LEFT | BlockRenderer.FLAG_SAME_TYPE_DOWN_LEFT);
        if (type.getValue(x + 1, y - 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_DOWN_RIGHT | BlockRenderer.FLAG_SAME_TYPE_DOWN_RIGHT);
        if (type.getValue(x - 1, y + 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_LEFT | BlockRenderer.FLAG_SAME_TYPE_UP_LEFT);
        if (type.getValue(x + 1, y + 1, rot) > 0) flags = BlockRenderer.getAddFlag(flags, BlockRenderer.FLAG_SAME_ID_UP_RIGHT | BlockRenderer.FLAG_SAME_TYPE_UP_RIGHT);
        
        return flags;
    }
}
