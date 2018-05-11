package com.si;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GridTest {
    final int SIZE = 15;
    public static void main(String[] args) {
        new GridTest();
    }

















    public GridTest() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Stratego");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());//zawartość frame
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);



            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println(actionEvent.getActionCommand());
                }
            };
            setLayout(new GridLayout(SIZE + 1, SIZE + 1));

            for (int row = 0; row <= SIZE; row++) {
                if (row > 0) {
                    add(new JLabel(Integer.toString(row - 1)));
                }
                else {
                    add(new JLabel(" "));
                }
                for (int col = 0; col <= SIZE - 1; col++) {
                    if (row == 0) {
                        add(new JLabel(Integer.toString(col)));
                    }
                    else {
                        String label = Integer.toString(row-1) + ',' + Integer.toString(col);
                        JButton but = new JButton(label);
                        but.addActionListener(actionListener);
                        add(but);
                    }
                }
            }
        }
    }
}