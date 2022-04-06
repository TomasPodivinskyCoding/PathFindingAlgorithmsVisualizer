package com.company.example.view;

import com.company.example.enums.State;
import com.company.example.algorithm.DepthFirstSearch;

import javax.swing.*;
import java.awt.*;

public class AlgorithmToolbar extends JPanel {

    public AlgorithmToolbar(BoardPanel boardPanel) {
        this.setBackground(Color.black);
        AlgorithmButton dfsButton = new AlgorithmButton(new DepthFirstSearch(boardPanel));
        dfsButton.setText("DFS");
        this.add(dfsButton);
    }

}
