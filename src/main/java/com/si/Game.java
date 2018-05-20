package com.si;

import com.si.player.AlphaBetaPlayer;
import com.si.player.HumanPlayer;
import com.si.player.MinMaxPlayer;
import com.si.player.Player;
import java.awt.*;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private final int BOARD_SIZE;
    private Board board;
    private boolean enableGui;
    private boolean player1MoveFirst;
    private GameWindow g;
    private Scanner sc;

    public Game(Player player1, Player player2, int BOARD_SIZE, Board board, boolean enableGui, boolean player1MoveFirst) {
        this.player1 = player1;
        player1.setBoard(board);
        this.player2 = player2;
        player2.setBoard(board);
        this.BOARD_SIZE = BOARD_SIZE;
        this.board = board;
        this.enableGui = enableGui;
        this.player1MoveFirst = player1MoveFirst;
        this.g = g;
        sc = new Scanner(System.in);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private void run() { //no gui play
        while (!board.isFull()) {
            int errorCode;
            do {
                if(player1MoveFirst) {
                    System.out.println("Ruch gracza I");
                }
                else {
                    System.out.println("Ruch gracza II");
                }

                if(player1MoveFirst) {
                    errorCode = 99;
                    if (player1 instanceof MinMaxPlayer){
                        ((MinMaxPlayer) player1).move();
                    }
                    if (player1 instanceof AlphaBetaPlayer){
                        ((AlphaBetaPlayer) player1).move();
                    }
                    if (player1 instanceof HumanPlayer){
                        System.out.println("x: ");
                        int playerX = sc.nextInt();
                        System.out.println("y: ");
                        int playerY = sc.nextInt();
                        errorCode = ((HumanPlayer) player1).move(playerX, playerY);
                    }

                }
                else {
                    errorCode = 99;
                    if (player2 instanceof MinMaxPlayer) {
                        ((MinMaxPlayer) player2).move();
                    }
                    if (player2 instanceof AlphaBetaPlayer){
                        ((AlphaBetaPlayer) player2).move();
                    }
                    if (player2 instanceof HumanPlayer) {
                        System.out.println("x: ");
                        int playerX = sc.nextInt();
                        System.out.println("y: ");
                        int playerY = sc.nextInt();
                        errorCode = ((HumanPlayer) player2).move(playerX, playerY);
                    }
                }

                if (errorCode == 2) {
                    System.out.println("Pole zajęte");
                    System.out.println("Powtórz ruch");
                }
                if (errorCode == 4) {
                    System.out.println("Wartość spoza zakresu");
                    System.out.println("Powtórz ruch");
                }
            }
            while (errorCode == 2 || errorCode == 4);
            player1MoveFirst = !player1MoveFirst;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }

    }

    boolean goAll = true;
    public void run(int[] positions) {//to wywołuję z gui
            int[] toMark;

            if (player1MoveFirst) {
                System.out.println("Ruch gracza I");
            } else {
                System.out.println("Ruch gracza II");
            }
            if (player1MoveFirst) {
                if (player1 instanceof MinMaxPlayer) {
                    toMark = ((MinMaxPlayer) player1).move();//move już go kłądzie
                    g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                    g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
                }
                if (player1 instanceof AlphaBetaPlayer) {
                    toMark = ((AlphaBetaPlayer) player1).move();//move już go kłądzie
                    g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                    g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
                }
                if (player1 instanceof HumanPlayer) {
                    System.out.println("pl1");
                    ((HumanPlayer) player1).move(positions[0], positions[1]);
                }

            } else {
                if (player2 instanceof MinMaxPlayer) {
                    toMark = ((MinMaxPlayer) player2).move();
                    g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                    g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
                }
                if (player2 instanceof AlphaBetaPlayer) {
                    toMark = ((AlphaBetaPlayer) player2).move();
                    g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                    g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
                }
                if (player2 instanceof HumanPlayer) {
                    System.out.println("pl2");
                    ((HumanPlayer) player2).move(positions[0], positions[1]);
                }
            }
            player1MoveFirst = !player1MoveFirst;
            if (!(player1 instanceof HumanPlayer && player2 instanceof HumanPlayer)) {
                if (!board.isFull() && goAll) {// działa tylko jeśli pierwszy ruch to human
                    goAll = false;
                    run(positions);
                }
            }
            goAll = true;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
    }

    public void runMvsM() {//to wywołuję z gui
        while (!board.isFull()) {
            int[] toMark;
            if(player1MoveFirst) {
                toMark = ((MinMaxPlayer) player1).move();//move już go kłądzie
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            else {
                toMark = ((MinMaxPlayer) player2).move();
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            player1MoveFirst = !player1MoveFirst;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }
    }

    public void runABvsAB() {//to wywołuję z gui
        while (!board.isFull()) {
            int[] toMark;
            if(player1MoveFirst) {
                toMark = ((AlphaBetaPlayer) player1).move();//move już go kłądzie
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            else {
                toMark = ((AlphaBetaPlayer) player2).move();
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            player1MoveFirst = !player1MoveFirst;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }
    }

    public void runABvsM() {//to wywołuję z gui
        while (!board.isFull()) {
            int[] toMark;
            if(player1MoveFirst) {
                toMark = ((AlphaBetaPlayer) player1).move();//move już go kłądzie
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            else {
                toMark = ((MinMaxPlayer) player2).move();
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            player1MoveFirst = !player1MoveFirst;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }
    }

    public void runMvsAB() {//to wywołuję z gui
        while (!board.isFull()) {
            int[] toMark;
            if(player1MoveFirst) {
                toMark = ((MinMaxPlayer) player1).move();//move już go kłądzie
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            else {
                toMark = ((AlphaBetaPlayer) player2).move();
                g.getBoardGrid()[toMark[0]][toMark[1]].setBackground(Color.BLUE);
                g.getBoardGrid()[toMark[0]][toMark[1]].setSelected(true);
            }
            player1MoveFirst = !player1MoveFirst;
            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }
    }

    public void runGame() {
        if (enableGui){
            g = new GameWindow("Game", BOARD_SIZE, this);
            g.setVisible(true);
            g.setSize(500, 500);
            if (player1 instanceof MinMaxPlayer && player2 instanceof MinMaxPlayer) {
                runMvsM();
            }
            if (player1 instanceof AlphaBetaPlayer && player2 instanceof AlphaBetaPlayer) {
                runABvsAB();
            }
            if (player1 instanceof AlphaBetaPlayer && player2 instanceof MinMaxPlayer) {
                runABvsM();
            }
            if (player1 instanceof MinMaxPlayer && player2 instanceof AlphaBetaPlayer) {
                runMvsAB();
            }
        }
        else {
            run();
        }
    }
}
