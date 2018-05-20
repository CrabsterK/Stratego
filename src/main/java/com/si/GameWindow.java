package com.si;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {
    private final int SIZE;
    private JButton boardGrid[][];
    private Game game;
    Label label1;
    Label label2;

    public GameWindow(String title, int size, Game game) {
        super(title);
        this.SIZE = size;
        boardGrid = new JButton[size][size];
        this.game = game;
        setLayout(new GridLayout(size + 1, size));
        setDefaultCloseOperation(EXIT_ON_CLOSE );

        addButtons();
        addGameInfo();


    }

    public JButton[][] getBoardGrid() {
        return boardGrid;
    }

    private void addButtons(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boardGrid[i][j] = new JButton();
                boardGrid[i][j].addActionListener(this);
                add(boardGrid[i][j]);
            }
        }
    }

    private void addGameInfo(){
        label1 = new Label("Gracz I: 0pkt");
        label2 = new Label("Gracz II: 0pkt");
        add(label1);
        add(label2);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!((JButton)ae.getSource()).isSelected()){
            ((JButton)ae.getSource()).setBackground(Color.red);
            makeMove(ae);
            ((JButton)ae.getSource()).setSelected(true);
            updatePoints();
        }
    }

    private void updatePoints(){
        int player1Points = game.getPlayer1().getPoints();
        label1.setText("Gracz I: " + player1Points + "pkt");
        int player2Points = game.getPlayer2().getPoints();
        label2.setText("Gracz II: " + player2Points + "pkt");
    }

    private void makeMove(ActionEvent ae){
        int[] position = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if( boardGrid[i][j] == ae.getSource() ) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        game.run(position);
    }
}