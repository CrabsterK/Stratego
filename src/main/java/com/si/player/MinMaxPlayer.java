package com.si.player;

import com.si.Board;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinMaxPlayer extends Player {
    private int points;
    private Board board;
    private final int DEPTH;
    private int depthUse;
    private boolean cornerHeuristic;
    private boolean middleHeuristic;


    public MinMaxPlayer(int depth, boolean cornerHeuristic, boolean middleHeuristic){
        this.points = 0;
        this.DEPTH = depth;
        this.depthUse = depth;
        this.cornerHeuristic = cornerHeuristic;
        this.middleHeuristic = middleHeuristic;
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

    private int[][] sortMiddleWorst(int[][] emptyFields){
        List<Field> list = new ArrayList();
        for (int i = 0; i < emptyFields.length; i++) {
            list.add(new Field(emptyFields[i][0], emptyFields[i][1], board.getSize()));
        }
        Comparator<Field> distanceFromTheMiddleComparator = Comparator.comparingDouble(Field::getDistanceToMiddle);
        list.sort(distanceFromTheMiddleComparator.reversed());
        int[][] emptyFieldsSorted = new int[emptyFields.length][2];
        for (int i = 0; i < list.size(); i++){
            emptyFieldsSorted[i][0] = list.get(i).getX();
            emptyFieldsSorted[i][1] = list.get(i).getY();
        }
        return emptyFieldsSorted;
    }

    private int[][] sortMiddleBest(int[][] emptyFields){
        List<Field> list = new ArrayList();
        for (int i = 0; i < emptyFields.length; i++) {
            list.add(new Field(emptyFields[i][0], emptyFields[i][1], board.getSize()));
        }
        Comparator<Field> distanceFromTheMiddleComparator = Comparator.comparingDouble(Field::getDistanceToMiddle);
        list.sort(distanceFromTheMiddleComparator);
        int[][] emptyFieldsSorted = new int[emptyFields.length][2];
        for (int i = 0; i < list.size(); i++){
            emptyFieldsSorted[i][0] = list.get(i).getX();
            emptyFieldsSorted[i][1] = list.get(i).getY();
        }
        return emptyFieldsSorted;
    }

    private int[] minmax() {
        int best[] = new int[2];
        int bestMaxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        if (cornerHeuristic){
            emptyFields = sortMiddleWorst(emptyFields);
        }
        if (middleHeuristic){
            emptyFields = sortMiddleBest(emptyFields);
        }
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int currentNodeVal = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int tmpValue = minValue(depthUse-1, currentNodeVal,0);
            if (tmpValue > bestMaxValue){
                bestMaxValue = tmpValue;
                best[0] = emptyFields[i][0];
                best[1] = emptyFields[i][1];
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
        }
        depthUse = DEPTH;
        return best;
    }

    private int maxValue(int depth, int player1points, int player2points){
        if (depth==0 || board.isFull()){
            return player1points - player2points;
        }
        int bestMaxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int currentNodeVal = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int tmpValue = minValue(depth-1,currentNodeVal + player1points, player2points);
            if (tmpValue > bestMaxValue){
                bestMaxValue = tmpValue;
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
        }
        return bestMaxValue;
    }

    private int minValue(int depth, int player1points, int player2points){
        if (depth==0 || board.isFull()){
            return player1points - player2points;
        }
        int bestMinValue = Integer.MAX_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int nodeValue = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int tmpValue = maxValue(depth-1, player1points,player2points + nodeValue);
            if (tmpValue < bestMinValue){
                bestMinValue = tmpValue;
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
        }
        return bestMinValue;
    }

    public int[] move() {
        int[] positions = minmax();
        int points = board.putAndGetNewPoints(positions[0], positions[1]);
        if (points > 0) {
            addPoints(points);
        }
        return positions;
    }
}
