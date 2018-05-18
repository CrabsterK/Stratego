package com.si;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {
    private final int SIZE;
    private JButton boardGrid[][];
    private Game game;

    public GameWindow(String title, int size, Game game) {
        super(title);
        this.SIZE = size;
        boardGrid = new JButton[size][size];
        this.game = game;

        setLayout(new GridLayout(size, size));
        setDefaultCloseOperation(EXIT_ON_CLOSE );
        addButtons();
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        ((JButton)ae.getSource()).setBackground(Color.red);
        if (!((JButton)ae.getSource()).isSelected()){
            makeMove(ae);
        }
        ((JButton)ae.getSource()).setSelected(true);
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