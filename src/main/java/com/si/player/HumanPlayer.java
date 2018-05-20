package com.si.player;

import com.si.Board;

public class HumanPlayer extends Player {
    public int points;
    public Board board;

    public HumanPlayer(){
        this.points = 0;
    }

    @Override
    public void setBoard(Board board){
        this.board = board;
    }

    @Override
    public int getPoints(){
        return points;
    }

    @Override
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
