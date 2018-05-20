package com.si.player;

import com.si.Board;

public class MinMaxPlayer extends Player {
    private int points;
    private Board board;
    private final int DEPTH;
    int depthUse;

    public MinMaxPlayer(int depth){
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

    public void addPoints(int pointsToAdd){
        this.points += pointsToAdd;
    }


    private int[] minmax(){//(int depth) {
        int best[] = new int[2];
        int maxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            // field.setStatus(Field.FieldStatus.RED);
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = minValue(depthUse-1,nodeValue,0);
            if (value > maxValue){
                maxValue = value;
                best[0] = emptyFields[i][0];
                best[1] = emptyFields[i][1];
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
            // field.setStatus(Field.FieldStatus.EMPTY);
        }
        depthUse = DEPTH;
        return best;
    }

    private int maxValue(int depth, int playerScore, int enemyScore){
        if (depth==0 || board.isFull()){
            return playerScore - enemyScore;
        }
        int maxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            //field.setStatus(Field.FieldStatus.RED);
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = minValue(depth-1,nodeValue + playerScore,enemyScore);
            if (value > maxValue){
                maxValue = value;
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
            //  field.setStatus(Field.FieldStatus.EMPTY);
        }
        return maxValue;
    }
    private int minValue(int depth, int playerScore, int enemyScore){
        if (depth==0 || board.isFull()){
            return playerScore - enemyScore;
        }
        int minValue = Integer.MAX_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            //field.setStatus(Field.FieldStatus.RED);
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int value = maxValue(depth-1,playerScore,enemyScore + nodeValue);
            if (value < minValue){
                minValue = value;
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
            //field.setStatus(Field.FieldStatus.EMPTY);
        }
        return minValue;
    }




    public int[] move() {//parametry bez znaczenia
        int[] positions = minmax();
        int points = board.putAndGetNewPoints(positions[0], positions[1]);
        if (points > 0) { // Punkty dodane
            addPoints(points);
        }
        return positions;
    }
}
