package com.si;

public class Main {
    public static void main(String[] args) {
        final int BOARD_SIZE = 3;
        boolean enableGui = false;
        boolean player1MoveFirst = false;
        
        PersonVsPerson pvp = new PersonVsPerson(BOARD_SIZE, enableGui, player1MoveFirst);
        pvp.runGame();


    }
}