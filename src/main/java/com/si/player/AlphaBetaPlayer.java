package com.si.player;

import com.si.Board;

public class AlphaBetaPlayer extends Player{
    private int points;
    private Board board;
    private final int DEPTH;
    int depthUse;

    public AlphaBetaPlayer(int depth){
        this.points = 0;
        this.DEPTH = depth;
        this.depthUse = depth;
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

    private int[] alphaBeta() {
        int best[] = new int[2];
        int maxValue = Integer.MIN_VALUE;
        int aRoot = Integer.MIN_VALUE;
        int bRoot = Integer.MAX_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = minValue(depthUse - 1, nodeValue, 0, aRoot, bRoot);
            if (value > maxValue) {
                maxValue = value;
                best[0] = emptyFields[i][0];
                best[1] = emptyFields[i][1];
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
        }
        depthUse = DEPTH;
        return best;
    }

    private int maxValue(int maxDepth, int playerScore, int enemyScore, int aRoot, int bRoot) {
        if (maxDepth == 0 ||  board.isFull()) {
            return playerScore - enemyScore;
        }
        int a = aRoot;
        int b = bRoot;
        int maxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = minValue(maxDepth - 1, nodeValue + playerScore, enemyScore, a, b);
            if (value > maxValue) {
                maxValue = value;
                if (value > a) {
                    a = value;
                }
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;

            if (value > b) {
                break;
            }
        }
        return maxValue;
    }

    private int minValue(int maxDepth, int playerScore, int enemyScore, int aRoot, int bRoot) {
        if (maxDepth == 0 || board.isFull()) {
            return playerScore - enemyScore;
        }
        int a = aRoot;
        int b = bRoot;
        int minValue = Integer.MAX_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue =  board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = maxValue(maxDepth - 1, playerScore, enemyScore + nodeValue, a, b);
            if (value < minValue) {
                minValue = value;
                if (value < b) {
                    b = value;
                }
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;

            if (value < a) {
                break;
            }
        }
        return minValue;
    }


    public int[] move() {//parametry bez znaczenia
        int[] positions = alphaBeta();
        int points = board.putAndGetNewPoints(positions[0], positions[1]);
        if (points > 0) { // Punkty dodane
            addPoints(points);
        }
        return positions;
    }
}
