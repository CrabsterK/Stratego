package com.si;

import com.si.player.AlphaBetaPlayer;
import com.si.player.HumanPlayer;
import com.si.player.MinMaxPlayer;
import com.si.player.Player;

public class Main {
    public static void main(String[] args) {
        final int BOARD_SIZE = 6;
        final int DEPTH = 5;
        boolean enableGui = true;
        boolean player1MoveFirst = false;


        Board board = new Board(BOARD_SIZE);
        Player player1 = new MinMaxPlayer(DEPTH, true, false);
        Player player2 = new MinMaxPlayer(DEPTH, false, true);
        Player player3 = new HumanPlayer();
        Player player4 = new HumanPlayer();
        Player player5 = new AlphaBetaPlayer(DEPTH, true, false);
        Player player6 = new AlphaBetaPlayer(DEPTH, false, true);

        Game game = new Game(player5, player6, BOARD_SIZE, board, enableGui, player1MoveFirst);
        game.runGame();



    }
}