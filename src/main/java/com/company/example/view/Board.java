package com.company.example.view;

import com.company.example.BoardListener;
import com.company.example.State;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Board extends JPanel {

    private final int rows = 30;
    private final int cols = 30;

    private final BoardCell[][] board = new BoardCell[rows][cols];

    private BoardCell start;
    private BoardCell finish;

    public Board() {
        setUpBoard();

        BoardListener boardListener = new BoardListener(this);
        this.addMouseListener(boardListener);
        this.addMouseMotionListener(boardListener);
    }

    private void setUpBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new BoardCell(State.EMPTY, i, j, null);
            }
        }
        board[0][0] = new BoardCell(State.START, 0, 0, null);
        start = board[0][0];
        board[rows - 1][cols - 1] = new BoardCell(State.FINISH, rows - 1, cols - 1, null);
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
        }
        return color;
    }

    public BoardCell getCellAt(int x, int y) {
        // TODO handle out of bounds
        double width = (double) this.getWidth() / cols;
        double height = (double) this.getHeight() / rows;
        int column = (int) (x / width);
        int row = (int) (y / height);
        return board[row][column];
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
}
