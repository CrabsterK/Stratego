package com.si;

public class Player {
    public int points;
    public Board board;

    public Player(Board board){
        this.points = 0;
        this.board = board;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int pointsToAdd){
        this.points += pointsToAdd;
    }

    public void move(int x, int y){
        int points = board.putAndGetNewPoints(x, y);
        if(points == 0){
            System.out.println("Ruch niepunktowany");
        }
        else if (points > 0){
            addPoints(points);
        }
        if(points < 0){
            System.out.println("Coś poszło nie tak, Menager.move(" + x + ", " + y + ")");
        }
    }
}
