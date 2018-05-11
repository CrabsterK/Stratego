package com.si.player;

import com.si.Board;

public class Player implements PlayerI{
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

    public int move(int x, int y){
        if (x < board.getSize() && x >= 0 && y < board.getSize() && y >= 0) { // Wartości z zakresu
            int points = board.putAndGetNewPoints(x, y);
            if (points == 0) { // Ruch niepunktowany
                return 0;
            } else if (points > 0) { // Punkty dodane
                addPoints(points);
                return 1;
            } else if (points < 0) {// Pole zajęte
                return 2;
            } else {
                return 3;
            }
        }
        else{ // Wartości spoza zakresu
            return 4;
        }

    }
}
