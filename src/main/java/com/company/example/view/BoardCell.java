package com.company.example.view;

import com.company.example.State;

import javax.swing.*;
import java.awt.*;

public class BoardCell extends JButton {

    private State state;
    public int row;
    public int column;
    public BoardCell parent;

    public BoardCell(State state, int row, int column, BoardCell parent) {
        this.state = state;
        this.row = row;
        this.column = column;
        this.parent = parent;
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
