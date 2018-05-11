package com.si;

public class Menager {
    final int BOARD_SIZE = 5;
    Board board;
    Player player1;

    public Menager(){
        board = new Board(BOARD_SIZE);
        player1 = new Player();
    }

    public void move(int x, int y){
        int points = board.putAndGetNewPoints(x, y);
        if(points == 0){
            System.out.println("Ruch niepunktowany");
        }
        else if (points > 0){
            player1.addPoints(points);
        }
        if(points < 0){
            System.out.println("Coś poszło nie tak, Menager.move(" + x + ", " + y + ")");
        }
        System.out.println("pkt: " + player1.getPoints());
        board.print();
    }
}
