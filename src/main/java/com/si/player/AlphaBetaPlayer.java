package com.si.player;

import com.si.Board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlphaBetaPlayer extends Player{
    private int points;
    private Board board;
    private final int DEPTH;
    private int depthUse;
    private boolean cornerHeuristic;
    private boolean middleHeuristic;

    public AlphaBetaPlayer(int depth, boolean cornerHeuristic, boolean middleHeuristic){
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

    private int[] alphaBeta() {
        int best[] = new int[2];
        int bestMaxValue = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
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
            int tmpValue = minValue(depthUse - 1, currentNodeVal, 0, alpha, beta);
            if (tmpValue > bestMaxValue) {
                bestMaxValue = tmpValue;
                best[0] = emptyFields[i][0];
                best[1] = emptyFields[i][1];
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
        }
        depthUse = DEPTH;
        return best;
    }

    private int maxValue(int maxDepth, int player1points, int player2points, int alpha, int beta) {
        if (maxDepth == 0 ||  board.isFull()) {
            return player1points - player2points;
        }
        int bestMaxValue = Integer.MIN_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int currentNodeVal = board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int tmpValue = minValue(maxDepth - 1, currentNodeVal + player1points, player2points, alpha, beta);
            if (tmpValue > bestMaxValue) {
                bestMaxValue = tmpValue;
                if (tmpValue > alpha) {
                    alpha = tmpValue;
                }
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
            if (tmpValue >= beta) {
                break;
            }
        }
        return bestMaxValue;
    }

    private int minValue(int maxDepth, int player1points, int player2points, int alpha, int beta) {
        if (maxDepth == 0 || board.isFull()) {
            return player1points - player2points;
        }
        int bestMinValue = Integer.MAX_VALUE;
        int[][] emptyFields = board.getEmptyFields();
        for (int i = 0; i < emptyFields.length; i++){
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = true;
            int currentNodeVal =  board.getPoints(emptyFields[i][0], emptyFields[i][1]);
            int tmpValue = maxValue(maxDepth - 1, player1points, player2points + currentNodeVal, alpha, beta);
            if (tmpValue < bestMinValue) {
                bestMinValue = tmpValue;
                if (tmpValue < beta) {
                    beta = tmpValue;
                }
            }
            board.getBoard()[emptyFields[i][0]][emptyFields[i][1]] = false;
            if (tmpValue <= alpha) {
                break;
            }
        }
        return bestMinValue;
    }

    public int[] move() {
        int[] positions = alphaBeta();
        int points = board.putAndGetNewPoints(positions[0], positions[1]);
        if (points > 0) {
            addPoints(points);
        }
        return positions;
    }
}
