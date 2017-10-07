package com.game.views;

import com.game.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Display extends JFrame {

    public static void main(String[] args) {
        Display app = new Display();
    }
    public Display(){
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu start = new JMenu("Start!");
        JMenu restart = new JMenu("Restart");
        JMenu help = new JMenu("Help");
        menuBar.add(start);
        menuBar.add(restart);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(500,500));
        HashMap<Integer, Runnable> Actions = new HashMap<Integer, Runnable>(); //не работает
        Actions.put(KeyEvent.VK_BACK_SPACE, () -> new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Display.this,
                        "Continue?",
                        "Pause",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    JOptionPane.showConfirmDialog(Display.this,
                            "Вы не отказываетесь?");
                else if (result == JOptionPane.NO_OPTION)
                    JOptionPane.showConfirmDialog(Display.this,
                            "Вы отказались?");
            }
        });
     //  frame.addKeyListener(new KeyListener() {

        //    @Override
         //   public void keyPressed(KeyEvent e) {
           //     {

            //    }
         //   }

      //  });
        frame.pack();
        frame.setVisible(true);

    }

}
