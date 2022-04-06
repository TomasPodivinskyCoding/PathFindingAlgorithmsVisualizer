package com.company.example.listeners;

import com.company.example.enums.State;
import com.company.example.view.BoardPanel;
import com.company.example.view.BoardCell;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardListener extends MouseAdapter {

    private final BoardPanel boardPanel;

    private boolean needsClearing;

    public BoardListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (needsClearing) {
            boardPanel.cleanBoard();
            needsClearing = false;
        }
        BoardCell cell = boardPanel.getCellAt(e.getX(), e.getY());
        if (cell == null) return;
        if (e.getButton() == MouseEvent.BUTTON1) {
            cell.setState(State.WALL);
        } else {
            cell.setState(State.EMPTY);
        }
        boardPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        BoardCell cell = boardPanel.getCellAt(e.getX(), e.getY());
        if (cell == null) return;
        if (SwingUtilities.isLeftMouseButton(e)) {
            cell.setState(State.WALL);
        } else {
            cell.setState(State.EMPTY);
        }
        boardPanel.repaint();
    }

    public void setNeedsClearing(boolean needsClearing) {
        this.needsClearing = needsClearing;
    }
}
