package com.si.player;

import com.si.Board;

import java.util.Random;

public class RandomPlayer extends Player {
    public int points;
    public Board board;
    private long time = 0;

    public RandomPlayer() {
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

    @Override
    public long getTime() {
        return time;
    }

    private int[] random() {
        int random[] = new int[2];
        int[][] emptyFields = board.getEmptyFields();
        Random rn =new Random();
        int randomIndex = rn.nextInt(emptyFields.length);
        random[0] = emptyFields[randomIndex][0];
        random[1] = emptyFields[randomIndex][1];
        return random;
    }

    public int[] move() {
        long startTime = System.currentTimeMillis();
        int[] positions = random();
        int points = board.putAndGetNewPoints(positions[0], positions[1]);
        if (points > 0) {
            addPoints(points);
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        time += estimatedTime;
        return positions;
    }
}
