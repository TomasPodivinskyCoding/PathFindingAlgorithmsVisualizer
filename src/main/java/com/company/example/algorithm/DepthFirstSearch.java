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
        if (isNotRunning()) {
            setRunning(true);
            visited = new boolean[boardPanel.getBoard().length][boardPanel.getBoard()[0].length];
            s.add(boardPanel.getStart());
            visitedTimer.start();
            boardPanel.disableMouseListener();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!s.isEmpty()) {
            s.addAll(getValidAdjacentCells(s.pop()));
        } else {
            visitedTimer.stop();
            setRunning(false);
        }
    }
}
