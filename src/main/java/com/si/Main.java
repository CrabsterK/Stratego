package com.si;

import com.si.player.AlphaBetaPlayer;
import com.si.player.HumanPlayer;
import com.si.player.MinMaxPlayer;
import com.si.player.Player;

public class Main {
    public static void main(String[] args) {
        final int BOARD_SIZE = 3;
        final int DEPTH = 4;
        boolean enableGui = true;
        boolean player1MoveFirst = true;

        Board board = new Board(BOARD_SIZE);
        Player player1 = new MinMaxPlayer(DEPTH);
        Player player2 = new MinMaxPlayer(DEPTH);
        Player player3 = new HumanPlayer();
        Player player4 = new HumanPlayer();
        Player player5 = new AlphaBetaPlayer(DEPTH);
        Player player6 = new AlphaBetaPlayer(DEPTH);

        Game game = new Game(player3, player5, BOARD_SIZE, board, enableGui, player1MoveFirst);
        game.runGame();



    }
}