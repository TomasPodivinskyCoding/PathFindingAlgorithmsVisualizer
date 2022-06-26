package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import java.util.ArrayList;

public abstract class AlgorithmBase implements Runnable {

    protected boolean[][] visited;

    protected static boolean running = false;

    protected BoardPanel boardPanel;

    protected int[][] adjacentTiles = {
            {0,-1},
            {1,0},
            {-1,0},
            {0,1}
    };

    public AlgorithmBase(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public abstract void solve();

    protected void startAlgorithm(AlgorithmBase algoToStart) {
        if (!running) {
            running = true;
            boardPanel.cleanBoard();
            visited = new boolean[boardPanel.getBoard().length][boardPanel.getBoard()[0].length];
            visited[boardPanel.getStart().row][boardPanel.getStart().column] = true;
            boardPanel.disableMouseListener();
            new Thread(algoToStart).start();
        }
    }

    protected void setCellState(BoardCell boardCell) {
        if (boardCell.getState() != State.START && boardCell.getState() != State.FINISH)
            boardPanel.getBoard()[boardCell.row][boardCell.column].setState(State.VISITED);
    }

    protected ArrayList<BoardCell> getValidAdjacentCells(BoardCell boardCell) {
        ArrayList<BoardCell> validCells = new ArrayList<>();
        int row, column;
        for (int[] adjacentTile : adjacentTiles) {
            row = boardCell.row + adjacentTile[0];
            column = boardCell.column + adjacentTile[1];
            if (isValid(row, column)) {
                visited[row][column] = true;
                validCells.add(boardPanel.getBoard()[row][column]);
                boardPanel.getBoard()[row][column].setParent(boardCell);
            }
        }
        return validCells;
    }

    protected boolean isValid(int row, int column) {
        return isInBounds(row, column) && !visited[row][column] && !isWall(row, column);
    }

    private boolean isWall(int row, int column) {
        return boardPanel.getBoard()[row][column].getState() == State.WALL;
    }

    private boolean isInBounds(int row, int column) {
        return row >= 0 && column >= 0 && row < boardPanel.getBoard().length && column < boardPanel.getBoard()[0].length;
    }

    protected void handleFinish(BoardCell finish) {
        if (finish != null) finish = finish.parent;
        while (finish != null && finish != boardPanel.getStart()) {
            boardPanel.getBoard()[finish.row][finish.column].setState(State.PATH);
            finish = finish.parent;
            boardPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        boardPanel.enableMouseListener();
        running = false;
    }
}
