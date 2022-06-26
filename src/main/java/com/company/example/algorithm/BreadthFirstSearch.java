package com.company.example.algorithm;

import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AlgorithmBase {

    private final Queue<BoardCell> s = new LinkedList<>();

    public BreadthFirstSearch(BoardPanel boardPanel) {
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
            BoardCell currentCell = s.poll();

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
