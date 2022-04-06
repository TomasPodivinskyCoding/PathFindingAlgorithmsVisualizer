package com.company.example.view;

import com.company.example.enums.State;

import javax.swing.*;

public class BoardCell extends JButton {

    private State state;
    public int row;
    public int column;
    public BoardCell parent;
    public int distance = Integer.MAX_VALUE;

    public BoardCell(State state, int row, int column) {
        this.state = state;
        this.row = row;
        this.column = column;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setParent(BoardCell parent) {
        this.parent = parent;
    }



}
