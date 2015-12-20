package nh.fb.gfx;

import java.awt.Graphics2D;

import nh.fb.board.Board;
import nh.fb.board.Piece;

public interface IBoardRenderer
{
    void draw(Graphics2D g, Board b, Piece p, Piece ghost, int x, int y, int blockSize);
}
