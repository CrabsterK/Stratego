package com.si;

import com.si.Board;
import com.si.GameWindow;
import com.si.player.HumanPlayer;
import com.si.player.MinMaxPlayer;
import com.si.player.Player;

import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;
    private final int BOARD_SIZE;
    private Board board;
    private boolean enableGui;
    private boolean player1MoveFirst;
    GameWindow g;
    Scanner sc;

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



    public void run(int[] positions) {//to wywołuję z gui
        if (player1MoveFirst){
            //      player1.move(positions[0], positions[1]);
        }
        else {
            //       player2.move(positions[0], positions[1]);
        }
        player1MoveFirst = !player1MoveFirst;
        board.print();
        System.out.println("pkt1: " + player1.getPoints());
        System.out.println("pkt2: " + player2.getPoints());
        System.out.println();
    }

    public void runGame() {
        if (enableGui){
            g = new GameWindow("Game", BOARD_SIZE, this);
            g.setVisible(true);
            g.setSize(500, 500);
        }
        else {
            run();
        }
    }
}
