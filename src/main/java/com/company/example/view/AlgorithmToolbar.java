package com.company.example.view;

import com.company.example.State;
import com.company.example.algorithm.DepthFirstSearch;

import javax.swing.*;
import java.awt.*;

public class AlgorithmToolbar extends JPanel {

    private Board board;
    private BoardCell finish;

    private final Timer pathTimer = new Timer(50, e-> {
        if (finish == null || finish == board.getStart())
            ((Timer) e.getSource()).stop();

        board.getBoard()[finish.row][finish.column].setState(State.PATH);
        board.repaint();
        finish = finish.parent;
    });

    public AlgorithmToolbar(Board board) {
        this.board = board;
        this.setBackground(Color.black);
        DepthFirstSearch dfs = new DepthFirstSearch(board);
        JButton dfsButton = new JButton();
        dfsButton.addActionListener(e-> {
            finish = dfs.solve();
            pathTimer.start();
        });
        this.add(dfsButton);
    }
}
