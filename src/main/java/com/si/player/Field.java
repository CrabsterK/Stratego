package com.si.player;

public class Field {
    private int x;
    private int y;
    private int N;

    public Field(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.N = n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistanceToMiddle(){
        double distance = Math.sqrt(Math.pow(x - N/2, 2) + Math.pow(y - N/2, 2));
        return distance;
    }
}
