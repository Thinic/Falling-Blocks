package nh.fb.gfx;

import java.awt.Graphics;

import nh.fb.board.Board;
import nh.fb.board.Piece;
import nh.fb.board.PieceType;

public interface IBoardRenderer
{
    void draw(Graphics g, Board b, Piece p, Piece ghost, int x, int y, int blockSize);
    
    void drawNextPiece(Graphics g, PieceType type, int x, int y, int size);
}
