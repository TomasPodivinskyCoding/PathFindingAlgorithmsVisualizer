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

    private boolean isPieceMoving;
    private State movingPiece;
    private BoardCell previousPiece;
    private State previousState;

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
        if (cell.getState() == State.START || cell.getState() == State.FINISH) {
            isPieceMoving = true;
            movingPiece = cell.getState();
            previousState = State.EMPTY;
            previousPiece = cell;
        }
        replaceCell(cell, e.getButton() == MouseEvent.BUTTON1);
        boardPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        BoardCell cell = boardPanel.getCellAt(e.getX(), e.getY());
        if (isPieceMoving) {
            moveCell(cell);
        } else {
            replaceCell(cell, SwingUtilities.isLeftMouseButton(e));
        }
        boardPanel.repaint();
    }

    private void moveCell(BoardCell cell) {
        if ((movingPiece == State.START && cell.getState() == State.FINISH) || (movingPiece == State.FINISH && cell.getState() == State.START)) return;
        if (previousPiece != null) {
            boardPanel.getBoard()[previousPiece.row][previousPiece.column].setState(previousState);
            previousState = cell.getState();
        }
        previousPiece = cell;
        cell.setState(movingPiece);
    }

    private void replaceCell(BoardCell cell, boolean replace) {
        if (cell == null || isNotReplaceable(cell)) return;

        if (replace) {
            cell.setState(State.WALL);
        } else {
            cell.setState(State.EMPTY);
        }
    }

    private boolean isNotReplaceable(BoardCell cell) {
        return cell.getState() == State.START || cell.getState() == State.FINISH;
    }

    public void setNeedsClearing(boolean needsClearing) {
        this.needsClearing = needsClearing;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (isPieceMoving) {
            if (previousPiece.getState() == State.START) boardPanel.setStart(previousPiece);
            else if (previousPiece.getState() == State.FINISH) boardPanel.setFinish(previousPiece);
        }
        this.isPieceMoving = false;
    }
}
