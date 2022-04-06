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
            BoardCell boardCell = q.poll();

            if (boardCell == boardPanel.getFinish()) {
                visitedTimer.stop();
                showFinalPath(boardCell.parent);
                setRunning(false);
                return;
            }

            if (boardCell.getState() != State.START && boardCell.getState() != State.FINISH)
                boardPanel.getBoard()[boardCell.row][boardCell.column].setState(State.VISITED);

            int row, column;
            row = boardCell.row - 1;
            if (row > -1) {
                addToToCheck(row, boardCell.column, boardCell);
            }
            row = boardCell.row + 1;
            if (row < boardPanel.getBoard().length) {
                addToToCheck(row, boardCell.column, boardCell);
            }
            column = boardCell.column - 1;
            if (column > -1) {
                addToToCheck(boardCell.row, column, boardCell);
            }
            column = boardCell.column + 1;
            if (column < boardPanel.getBoard()[0].length) {
                addToToCheck(boardCell.row, column, boardCell);
            }
        } else {
            visitedTimer.stop();
            setRunning(false);
        }
    }

    private void addToToCheck(int row, int column, BoardCell parent) {
        if (!visited[row][column]) {
            visited[row][column] = true;
            if (boardPanel.getBoard()[row][column].getState() == State.WALL) return;
            q.add(boardPanel.getBoard()[row][column]);
            boardPanel.getBoard()[row][column].setParent(parent);
        }
    }
}
