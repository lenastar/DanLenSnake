package com.levelConstructor.views;

import com.levelConstructor.models.Constructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class ResizeCanvasDialog{
    private final ConstructorView constructorView;
    private final Constructor constructor;
    private final int MAX = 100;
    private final int MIN = 6;

    private static String widthString = "Width: ";
    private static String heightString = "Height: ";

    private JFormattedTextField widthField;
    private JFormattedTextField heightField;

    private JLabel widthLabel;
    private JLabel heightLabel;

    private NumberFormat format;

    public ResizeCanvasDialog(ConstructorView constructorView){
        this.constructorView = constructorView;
        this.constructor = constructorView.getConstructor();
    }

    public void addComponentToPane(Container pane) {
        format = NumberFormat.getIntegerInstance();

        widthLabel = new JLabel(widthString);
        heightLabel = new JLabel(heightString);

        widthField = new JFormattedTextField(format);
        widthField.setValue(constructor.getLevel().getWidth());
        widthField.setColumns(10);

        heightField = new JFormattedTextField(format);
        heightField.setValue(constructor.getLevel().getHeight());
        heightField.setColumns(10);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            constructor.setWidth((int)widthField.getValue());
            constructor.setHeight((int) heightField.getValue());
            constructorView.repaint();
            constructorView.getParent().revalidate();
        });

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(widthLabel);
        labelPane.add(heightLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(widthField);
        fieldPane.add(heightField);

        pane.add(labelPane, BorderLayout.CENTER);
        pane.add(fieldPane, BorderLayout.LINE_END);
        pane.add(okButton, BorderLayout.SOUTH);
    }

    public static void show(ConstructorView constructorView) {
        //Create and set up the window.
        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        ResizeCanvasDialog dialog = new ResizeCanvasDialog(constructorView);
        dialog.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
