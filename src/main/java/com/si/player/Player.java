package com.si.player;

import com.si.Board;

public abstract class Player {
    public abstract int getPoints();
    public abstract void setBoard(Board board);
    public abstract void addPoints(int pointsToAdd);
}
