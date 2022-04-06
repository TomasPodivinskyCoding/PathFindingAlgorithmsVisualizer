package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AlgorithmBase {

    private final Queue<BoardCell> q = new LinkedList<>();

    public BreadthFirstSearch(BoardPanel boardPanel) {
        super(boardPanel);
    }

    @Override
    public void solve() {
        if (isNotRunning()) {
            setRunning(true);
            visited = new boolean[boardPanel.getBoard().length][boardPanel.getBoard()[0].length];
            q.add(boardPanel.getStart());
            visitedTimer.start();
            boardPanel.disableMouseListener();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!q.isEmpty()) {
            q.addAll(getValidAdjacentCells(q.poll()));
        } else {
            visitedTimer.stop();
            setRunning(false);
        }
    }
}
