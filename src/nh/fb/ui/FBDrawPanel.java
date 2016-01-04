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
    private int borderSize;
    
    public FBDrawPanel(FallingBlocksGame game, int size) 
    {
        setGame(game);
        this.size = size;
        
        borderSize = 10;
        
        setOffset(0, 0);
        setOffsetType(CENTER);
        
        resize();
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
        
        setWidth(gameWidth * size + borderSize * 2);
        setHeight(gameHeight * size + borderSize * 2);
    }
    
    @Override
    public void drawElement(Graphics g) 
    {
        // not call super, so that it is transparent
        resize();
        
        drawBackground(g);
        
        g.translate(borderSize, borderSize);
        drawBoard(g);
        drawGhostPiece(g);
        drawPiece(g);
        g.translate(-borderSize, -borderSize);
        
        drawNextPieces(g);
        
        drawBorder(g);
    }
    
    private void drawNextPieces(Graphics g)
    {
        BlockRenderer br = new BasicBlockRenderer();
        
        drawNextPiece(g, br, 0);
        drawNextPiece(g, br, 1);
        drawNextPiece(g, br, 2);
        drawNextPiece(g, br, 3);
        drawNextPiece(g, br, 4);
    }
    
    private void drawNextPiece(Graphics g, BlockRenderer br, int i) 
    {
        int padding = 3 * size;
        
        PieceType type = game.getBufferAt(i);
        
        int offX = (4 - type.getWidth()) * size / 2;
        int offY = (4 - type.getHeight()) * size / 2;
        
        offY += type.getBottomOffset(0) * size / 2;
        offY -= type.getTopOffset(0) * size / 2;
        
        if (i != 0) offY += 100;
        
        drawPieceType(g, br, type, 0, 0, getWidth() + borderSize*2 + offX, i*padding + offY);
    }

    private void drawGhostPiece(Graphics g)
    {
        Piece piece = game.getGhostPiece();
        
        BlockRenderer br = new GhostBlockRenderer();
        
        drawBoardPieceType(g, br, piece);
    }

    private void drawPiece(Graphics g)
    {
        Piece piece = game.getPiece();
        
        BlockRenderer br = new BasicBlockRenderer();
        
        drawBoardPieceType(g, br, piece);
    }
    
    private void drawBoardPieceType(Graphics g, BlockRenderer br, Piece piece) 
    {
        int id = piece.getID();
        int rot = piece.getRotation();
        
        PieceType type = piece.getType();
        
        int x = piece.getX();
        int y = piece.getY();
        
        drawPieceType(g, br, type, rot, id, x * size, (game.getBoard().getHeight() - y - piece.getType().getHeight()) * size);
    }
    
    private void drawPieceType(Graphics g, BlockRenderer br, PieceType type, int rot, int id, int tx, int ty) 
    {
        g.translate(tx, ty);
        
        for (int bx = 0; bx < type.getWidth(); bx++) 
        {
            for (int by = 0; by < type.getHeight(); by++) 
            {
                int flags = getFlagsForBlock(type, bx, by, rot);
                
                int val = type.getValue(bx, by, rot);
                
                int x = bx * size;
                int y = (type.getHeight() - by - 1) * size;
                
                if (val != 0) br.draw(g, val, id, flags, x, y, size);
            }
        }
        
        g.translate(-tx, -ty);
    }

    private void drawBackground(Graphics g) 
    {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    private void drawBorder(Graphics g) 
    {
        g.setColor(Color.BLACK);
        
        g.fillRect(0, 0, getWidth(), borderSize);
        g.fillRect(0, getHeight() - borderSize, getWidth(), borderSize);
        g.fillRect(0, 0, borderSize, getHeight());
        g.fillRect(getWidth() - borderSize, 0, borderSize, getHeight());
    }
    
    private void drawBoard(Graphics g) 
    {
        Board board = game.getBoard();
        
        BlockRenderer br = new BasicBlockRenderer();
        
        for (int bx = 0; bx < board.getWidth(); bx++) 
        {
            for (int by = 0; by < board.getHeight(); by++) 
            {
                int x = bx * size;
                int y = (board.getHeight() - by - 1) * size;
                
                int flags = getFlagsForBlock(board, bx, by);
                
                int type = board.getValue(bx, by);
                int id = board.getID(bx, by);
                
                br.draw(g, type, id, flags, x, y, size);
            }
        }
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
