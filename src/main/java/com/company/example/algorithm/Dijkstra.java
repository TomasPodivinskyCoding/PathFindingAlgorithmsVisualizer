package com.company.example.algorithm;

import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra extends AlgorithmBase {

    private final Queue<BoardCell> q = new LinkedList<>();

    public Dijkstra(BoardPanel boardPanel) {
        super(boardPanel);
    }

    @Override
    public void solve() {
        q.add(boardPanel.getStart());
        boardPanel.getStart().distance = 0;
        startAlgorithm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!q.isEmpty()) {
            BoardCell cell = q.poll();
            visited[cell.row][cell.column] = true;
            ArrayList<BoardCell> neighbours = getValidAdjacentCells(cell);
            neighbours.forEach(neighbour -> neighbour.distance = cell.distance + 1);
            q.addAll(neighbours);
        } else {
            visitedTimer.stop();
            setRunning(false);
            boardPanel.enableMouseListener();
        }
    }
}
