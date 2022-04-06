package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardPanel;
import com.company.example.view.BoardCell;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends AlgorithmBase {

    private final HashSet<BoardCell> visited = new HashSet<>();
    private final Stack<BoardCell> s = new Stack<>();

    public DepthFirstSearch(BoardPanel boardPanel) {
        super(boardPanel);
    }

    public void solve() {
        visited.clear();
        s.add(boardPanel.getStart());
        visitedTimer.start();
        boardPanel.disableMouseListener();
    }

    private void addToQueue(int row, int column, BoardCell parent) {
        if (!visited.contains(boardPanel.getBoard()[row][column])) {
            if (boardPanel.getBoard()[row][column].getState() == State.WALL) return;
            s.add(boardPanel.getBoard()[row][column]);
            boardPanel.getBoard()[row][column].setParent(parent);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!s.isEmpty()) {
            BoardCell boardCell = s.pop();

            if (boardCell == boardPanel.getFinish()) {
                visitedTimer.stop();
                showFinalPath(boardCell.parent);
                return;
            }

            if (boardCell.getState() != State.START && boardCell.getState() != State.FINISH)
                boardPanel.getBoard()[boardCell.row][boardCell.column].setState(State.VISITED);

            visited.add(boardCell);
            int row, column;
            row = boardCell.row - 1;
            if (row > -1) {
                addToQueue(row, boardCell.column, boardCell);
            }
            row = boardCell.row + 1;
            if (row < boardPanel.getBoard().length) {
                addToQueue(row, boardCell.column, boardCell);
            }
            column = boardCell.column - 1;
            if (column > -1) {
                addToQueue(boardCell.row, column, boardCell);
            }
            column = boardCell.column + 1;
            if (column < boardPanel.getBoard()[0].length) {
                addToQueue(boardCell.row, column, boardCell);
            }
        } else {
            visitedTimer.stop();
        }
    }
}
