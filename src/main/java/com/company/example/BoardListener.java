package com.company.example;

import com.company.example.view.Board;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardListener extends MouseAdapter {

    private final Board board;

    public BoardListener(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
            board.getCellAt(e.getX(), e.getY()).setState(State.WALL);
        } else {
            board.getCellAt(e.getX(), e.getY()).setState(State.EMPTY);
        }
        board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (SwingUtilities.isLeftMouseButton(e)) {
            board.getCellAt(e.getX(), e.getY()).setState(State.WALL);
        } else {
            board.getCellAt(e.getX(), e.getY()).setState(State.EMPTY);
        }
        board.repaint();
    }

}
