package com.si;

public class Player {
    public int points;

    public Player(){
        points = 0;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int pointsToAdd){
        this.points += pointsToAdd;
    }
}
