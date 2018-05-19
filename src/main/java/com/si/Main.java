package com.si;

import com.si.player.HumanPlayer;
import com.si.player.MinMaxPlayer;
import com.si.player.Player;

public class Main {
    public static void main(String[] args) {
        final int BOARD_SIZE = 3;
        final int DEPTH = 5;
        boolean enableGui = false;
        boolean player1MoveFirst = false;

        Board board = new Board(BOARD_SIZE);
        Player player1 = new MinMaxPlayer(DEPTH);
        Player player2 = new HumanPlayer();
        Game game = new Game(player1, player2, BOARD_SIZE, board, enableGui, player1MoveFirst);
        game.runGame();



    }
}