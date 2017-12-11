package com.levelConstructor.views;

import com.game.classes.exceptions.LevelBadSizeException;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ResizeCanvasDialog{
    private ConstructorView constructorView;
    private final int MAX = 100;
    private final int MIN = 6;

    private static String widthString = "Width: ";
    private static String heightString = "Height: ";

    private JFormattedTextField widthField;
    private JFormattedTextField heightField;

    private JLabel widthLabel;
    private JLabel heightLabel;
    private NumberFormatter numberFormatter;
    private NumberFormat format;

    public ResizeCanvasDialog(ConstructorView constructorView)
    {
        this.constructorView = constructorView;
        this.format = new DecimalFormat("##0");
        this.numberFormatter = new NumberFormatter(format);
        numberFormatter.setAllowsInvalid(false);
        format.setMaximumIntegerDigits(3);
        format.setMinimumIntegerDigits(1);


    }

    public void addComponentToPane(Container pane) {

        widthLabel = new JLabel(widthString);
        heightLabel = new JLabel(heightString);


        widthField = new JFormattedTextField(numberFormatter);
        widthField.setColumns(20);

        heightField = new JFormattedTextField(numberFormatter);
        heightField.setColumns(20);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            try {
                ok();
            } catch (LevelBadSizeException e1) {
                e1.printStackTrace();
            }
        });
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(e -> cancel());

        JPanel setter = new JPanel();
        setter.setLayout(new FlowLayout());
        setter.add(widthLabel);
        setter.add(widthField);
        setter.add(heightLabel);
        setter.add(heightField);

        JPanel card1 = new JPanel();
        card1.add(okButton);
        card1.add(closeButton);

        JPanel cards = new JPanel(new CardLayout());
        cards.add(card1 );

        pane.add(setter, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    private void cancel(){
        widthField.setValue(null);
        heightField.setValue(null);
    }

    private void ok() throws LevelBadSizeException {
        int widthValue=((Number)widthField.getValue()).intValue();
        int heightValue = ((Number)heightField.getValue()).intValue();
        if (widthField.getValue()!=null && heightField.getValue()!=null && widthValue<=MAX && widthValue>=MIN && heightValue>=MIN && heightValue<=MAX) {
            constructorView.setWidth(widthValue);
            constructorView.setHeight(heightValue);
        }
        else {
            JOptionPane.showMessageDialog(constructorView,"Please enter values");
        }
    }



    public static void show(ConstructorView constructorView) {
        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ResizeCanvasDialog dialog = new ResizeCanvasDialog(constructorView);
        dialog.addComponentToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
}
