package com.company.example.view;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {

    public Container(BoardPanel boardPanel, AlgorithmToolbar algorithmToolbar, Settings settings) {
        setLayout(new BorderLayout());
        this.add(algorithmToolbar, BorderLayout.SOUTH);
        this.add(settings, BorderLayout.WEST);
        this.add(boardPanel, BorderLayout.CENTER);
    }

}
