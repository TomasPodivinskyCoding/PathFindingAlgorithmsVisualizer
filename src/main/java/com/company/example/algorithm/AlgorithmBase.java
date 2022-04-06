package com.company.example.algorithm;

import com.company.example.enums.State;
import com.company.example.view.BoardCell;
import com.company.example.view.BoardPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashSet;

public abstract class AlgorithmBase implements ActionListener {

    protected boolean[][] visited;

    protected static boolean running = false;

    private BoardCell finish;

    protected BoardPanel boardPanel;

    private final Timer finalPathTimer = new Timer(10, e-> {
        if (finish == null || finish == boardPanel.getStart()) {
            ((Timer) e.getSource()).stop();
            boardPanel.enableMouseListener();
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
}
