package com.si.player;

public interface PlayerI {
    int getPoints();
    void addPoints(int pointsToAdd);
    int move(int x, int y);
}
