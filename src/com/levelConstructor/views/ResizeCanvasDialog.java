package com.levelConstructor.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class ResizeCanvasDialog implements ItemListener {
    private final ConstructorView constructorView;
    private final int MAX = 100;
    private final int MIN = 6;

    private static String widthString = "Width: ";
    private static String heightString = "Height: ";

    private JFormattedTextField widthField;
    private JFormattedTextField heightField;

    private JLabel widthLabel;
    private JLabel heightLabel;

    private NumberFormat format;

    public ResizeCanvasDialog(ConstructorView constructorView) {
        this.constructorView = constructorView;
    }

    public void addComponentToPane(Container pane) {
        format = NumberFormat.getNumberInstance();

        widthLabel = new JLabel(widthString);
        heightLabel = new JLabel(heightString);

        widthField = new JFormattedTextField(format);
        heightField = new JFormattedTextField(format);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(ok);
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(close);

        JFormattedTextField widthField = new JFormattedTextField();

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));

        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public static void show() {
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
