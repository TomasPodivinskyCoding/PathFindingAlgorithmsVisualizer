package com.company.example.view;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {

    public Container(Board board, AlgorithmToolbar algorithmToolbar, Settings settings) {
        setLayout(new BorderLayout());
        this.add(algorithmToolbar, BorderLayout.SOUTH);
        this.add(settings, BorderLayout.WEST);
        this.add(board, BorderLayout.CENTER);
    }

}
