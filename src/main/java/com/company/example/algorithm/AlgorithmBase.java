package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class AlgorithmBase implements ActionListener {

    protected boolean[][] visited;

    protected static boolean running = false;

    private BoardCell finish;

    protected BoardPanel boardPanel;

    protected int[][] adjacentTiles = {
            {0,-1},
            {1,0},
            {-1,0},
            {0,1}
    };

    private final Timer finalPathTimer = new Timer(10, e-> {
        if (finish == null || finish == boardPanel.getStart()) {
            ((Timer) e.getSource()).stop();
            boardPanel.enableMouseListener();
            setRunning(false);
        } else {
            boardPanel.getBoard()[finish.row][finish.column].setState(State.PATH);
            boardPanel.repaint();
            finish = finish.parent;
        }
    });

    protected final Timer visitedTimer = new Timer(10, e-> {
        this.actionPerformed(e);
        boardPanel.repaint();
    });

    public AlgorithmBase(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public abstract void solve();

    protected void showFinalPath(BoardCell boardCell) {
        this.finish = boardCell;
        this.finalPathTimer.start();
    }

    public static boolean isNotRunning() {
        return !running;
    }

    public static void setRunning(boolean running) {
        AlgorithmBase.running = running;
    }

    protected void handleFinish(BoardCell boardCell) {
        visitedTimer.stop();
        showFinalPath(boardCell.parent);
    }

    protected void setCellState(BoardCell boardCell) {
        if (boardCell.getState() != State.START && boardCell.getState() != State.FINISH)
            boardPanel.getBoard()[boardCell.row][boardCell.column].setState(State.VISITED);
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

    protected void startAlgorithm() {
        if (isNotRunning()) {
            setRunning(true);
            boardPanel.cleanBoard();
            visited = new boolean[boardPanel.getBoard().length][boardPanel.getBoard()[0].length];
            visited[boardPanel.getStart().row][boardPanel.getStart().column] = true;
            visitedTimer.start();
            boardPanel.disableMouseListener();
        }
    }

}
