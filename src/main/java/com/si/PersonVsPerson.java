package com.si;

import com.si.player.Player;

import java.util.Scanner;

public class PersonVsPerson implements Game{
    private final int BOARD_SIZE;
    private Board board;
    private Player player1;
    private Player player2;
    private Scanner sc;
    private boolean enableGui;
    private boolean player1MoveFirst;
    GameWindow g;

    public PersonVsPerson(int n, boolean enableGui, boolean player1MoveFirst){
        BOARD_SIZE = n;
        board = new Board(BOARD_SIZE);
        player1 = new Player(board);
        player2 = new Player(board);
        sc = new Scanner(System.in);
        this.enableGui = enableGui;
        this.player1MoveFirst = player1MoveFirst;

    }

    public void run() {
        while (!board.isFull()) {
            int errorCode;
            do {
                if(player1MoveFirst) {
                    System.out.println("Ruch gracza I");
                }
                else {
                    System.out.println("Ruch gracza II");
                }
                System.out.println("x: ");
                int playerX = sc.nextInt();
                System.out.println("y: ");
                int playerY = sc.nextInt();
                if(player1MoveFirst) {
                    errorCode = player1.move(playerX, playerY);
                }
                else {
                    errorCode = player2.move(playerX, playerY);
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

            board.print();
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            System.out.println();
        }
    }



    public void run(int[] positions) {
        if (player1MoveFirst){
            player1.move(positions[0], positions[1]);
        }
        else {
            player2.move(positions[0], positions[1]);
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
