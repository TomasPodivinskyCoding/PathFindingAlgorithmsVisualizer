package com.company.example.algorithm;

import com.company.example.State;
import com.company.example.view.Board;
import com.company.example.view.BoardCell;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch {

    private final Board board;
    private final HashSet<BoardCell> visited = new HashSet<>();
    private final Stack<BoardCell> s = new Stack<>();

    public DepthFirstSearch(Board board) {
        this.board = board;
    }

    public BoardCell solve() {
        visited.clear();
        s.add(board.getStart());

        while (!s.isEmpty()) {
            BoardCell boardCell = s.pop();
            if (boardCell == board.getFinish()) {
                System.out.println("wooooo");
                return boardCell.parent;
            }
            visited.add(boardCell);
            int row = boardCell.row - 1;
            if (row > -1) {
                addToQueue(row, boardCell.column, boardCell);
            }
            row = boardCell.row + 1;
            if (row < board.getBoard().length) {
                addToQueue(row, boardCell.column, boardCell);
            }
            int column = boardCell.column - 1;
            if (column > -1) {
                addToQueue(boardCell.row, column, boardCell);
            }
            column = boardCell.column + 1;
            if (column < board.getBoard()[0].length) {
                addToQueue(boardCell.row, column, boardCell);
            }
        }
        System.out.println("Finished");
        return null;
    }

    private void addToQueue(int row, int column, BoardCell parent) {
        if (!visited.contains(board.getBoard()[row][column])) {
            if (board.getBoard()[row][column].getState() == State.WALL) return;
            s.add(board.getBoard()[row][column]);
            board.getBoard()[row][column].setParent(parent);
        }
    }

}
