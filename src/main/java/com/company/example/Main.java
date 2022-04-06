package com.company.example;

import com.company.example.view.AlgorithmToolbar;
import com.company.example.view.BoardPanel;
import com.company.example.view.Container;
import com.company.example.view.Settings;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final Dimension frameSize = new Dimension(640,480);

    public static void main(String[] args) {
        final String title = "Pathfinder";
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setSize(new Dimension(640,480));
        frame.setVisible(true);

        BoardPanel boardPanel = new BoardPanel();
        Container container = new Container(boardPanel, new AlgorithmToolbar(boardPanel), new Settings());
        frame.add(container);

        frame.repaint();
        frame.revalidate();
    }

    public static Dimension getFrameSize() {
        return frameSize;
    }
}
