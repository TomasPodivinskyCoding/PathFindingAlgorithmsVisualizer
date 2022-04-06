package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardPanel;
import com.company.example.view.BoardCell;

import java.awt.event.ActionEvent;
import java.util.Stack;

public class DepthFirstSearch extends AlgorithmBase {

    private final Stack<BoardCell> s = new Stack<>();

    public DepthFirstSearch(BoardPanel boardPanel) {
        super(boardPanel);
    }

    public void solve() {
        s.add(boardPanel.getStart());
        startAlgorithm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!s.isEmpty()) {
            s.addAll(getValidAdjacentCells(s.pop()));
        } else {
            visitedTimer.stop();
            setRunning(false);
            boardPanel.enableMouseListener();
        }
    }
}
