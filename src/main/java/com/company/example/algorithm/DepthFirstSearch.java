package com.company.example.algorithm;

import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.util.Stack;

public class DepthFirstSearch extends AlgorithmBase {

    private final Stack<BoardCell> s = new Stack<>();

    public DepthFirstSearch(BoardPanel boardPanel) {
        super(boardPanel);
    }

    @Override
    public void solve() {
        s.add(boardPanel.getStart());
        startAlgorithm(this);
    }

    @Override
    public void run() {
        while (!s.isEmpty()) {
            BoardCell currentCell = s.pop();

            if (currentCell == boardPanel.getFinish()) {
                handleFinish(currentCell);
                s.clear();
                return;
            }

            setCellState(currentCell);

            s.addAll(getValidAdjacentCells(currentCell));
            try {
                Thread.sleep(10);
                boardPanel.repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        running = false;
        boardPanel.enableMouseListener();
        s.clear();
    }
}
