package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AlgorithmBase {

    private final Queue<BoardCell> q = new LinkedList<>();

    public BreadthFirstSearch(BoardPanel boardPanel) {
        super(boardPanel);
    }

    @Override
    public void solve() {
        q.add(boardPanel.getStart());
        startAlgorithm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!q.isEmpty()) {
            BoardCell currentCell = q.poll();

            if (currentCell == boardPanel.getFinish()) {
                handleFinish(currentCell);
                q.clear();
                return;
            }

            setCellState(currentCell);

            q.addAll(getValidAdjacentCells(currentCell));
        } else {
            visitedTimer.stop();
            setRunning(false);
            boardPanel.enableMouseListener();
            q.clear();
        }
    }
}
