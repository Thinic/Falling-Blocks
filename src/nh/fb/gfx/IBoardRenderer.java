package nh.fb.gfx;

import java.awt.Graphics2D;

import nh.fb.board.*;

public interface IBoardRenderer
{
    void draw(Graphics2D g, Board b, Piece p, Piece ghost, int x, int y, int blockSize);
    
    void drawNextPiece(Graphics2D g, PieceType type, int x, int y, int size);
}
