package com.company.example.view;

import com.company.example.listeners.BoardListener;
import com.company.example.enums.State;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoardPanel extends JPanel {

    private final int rows = 30;
    private final int cols = 30;

    private final BoardCell[][] board = new BoardCell[rows][cols];

    private BoardCell start;
    private BoardCell finish;

    private final BoardListener boardListener = new BoardListener(this);

    public BoardPanel() {
        setUpBoard();

        this.addMouseListener(boardListener);
        this.addMouseMotionListener(boardListener);
    }

    private void setUpBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new BoardCell(State.EMPTY, i, j);
            }
        }
        board[0][0] = new BoardCell(State.START, 0, 0);
        start = board[0][0];
        board[rows - 1][cols - 1] = new BoardCell(State.FINISH, rows - 1, cols - 1);
        finish = board[rows - 1][cols - 1];
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gd = (Graphics2D) g;
        double x = 0;
        double y = 0;
        double width = (double) this.getWidth() / cols;
        double height = (double) this.getHeight() / rows;

        for (BoardCell[] boardCells : board) {
            for (BoardCell boardCell : boardCells) {
                Rectangle2D.Double cell = new Rectangle2D.Double(x, y, width, height);
                gd.setColor(getBoardColor(boardCell));
                gd.fill(cell);

                gd.setColor(Color.LIGHT_GRAY);
                gd.draw(cell);

                x += width;
            }
            y += height;
            x = 0;
        }
    }

    private Color getBoardColor(BoardCell cell) {
        Color color = Color.white;
        switch (cell.getState()) {
            case FINISH -> color = Color.RED;
            case START -> color = Color.GREEN;
            case WALL -> color = Color.BLACK;
            case PATH -> color = Color.YELLOW;
            case VISITED -> color = Color.CYAN;
        }
        return color;
    }

    public BoardCell getCellAt(int x, int y) {
        double width = (double) this.getWidth() / cols;
        double height = (double) this.getHeight() / rows;
        int column = (int) (x / width);
        int row = (int) (y / height);
        return isInBounds(row, column) ? board[row][column] : null;
    }

    private boolean isInBounds(int column, int row) {
        return row >= 0 && column >= 0 && row < this.rows && column < this.cols;
    }

    public BoardCell[][] getBoard() {
        return board;
    }

    public BoardCell getStart() {
        return start;
    }

    public BoardCell getFinish() {
        return finish;
    }

    public void disableMouseListener() {
        removeMouseListener(this.boardListener);
        removeMouseMotionListener(this.boardListener);
    }

    public void enableMouseListener() {
        this.addMouseListener(boardListener);
        this.addMouseMotionListener(boardListener);
        boardListener.setNeedsClearing(true);
    }

    public void cleanBoard() {
        for (BoardCell[] boardCells : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (boardCells[j].getState() == State.PATH || boardCells[j].getState() == State.VISITED)
                    boardCells[j].setState(State.EMPTY);
            }
        }
    }
}
