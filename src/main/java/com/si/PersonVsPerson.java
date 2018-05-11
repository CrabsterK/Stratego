package com.si;

import java.util.Scanner;

public class PersonVsPerson {
    final int BOARD_SIZE;
    Board board;
    Player player1;
    Player player2;
    Scanner sc;

    public PersonVsPerson(int n){
        BOARD_SIZE = n;
        board = new Board(BOARD_SIZE);
        player1 = new Player(board);
        player2 = new Player(board);
        sc = new Scanner(System.in);
    }

    public void run() {
        while (!board.isFull()) {
            board.print();

            int errorCode;
            do {
                System.out.println("Róch gracza I");
                System.out.println("x: ");
                int player1x = sc.nextInt();
                System.out.println("y: ");
                int player1y = sc.nextInt();
                errorCode = player1.move(player1x, player1y);

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
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            board.print();


            int errorCode2;
            do {
                System.out.println("Róch gracza II");
                System.out.println("x: ");
                int player2x = sc.nextInt();
                System.out.println("y: ");
                int player2y = sc.nextInt();
                errorCode2 = player2.move(player2x, player2y);

                if (errorCode2 == 2) {
                    System.out.println("Pole zajęte");
                    System.out.println("Powtórz ruch");
                }
                if (errorCode2 == 4) {
                    System.out.println("Wartość spoza zakresu");
                    System.out.println("Powtórz ruch");
                }
            }
            while (errorCode2 == 2 || errorCode2 == 4);
            System.out.println("pkt1: " + player1.getPoints());
            System.out.println("pkt2: " + player2.getPoints());
            board.print();
        }
    }
}
