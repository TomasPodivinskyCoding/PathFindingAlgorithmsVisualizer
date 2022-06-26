package com.company.example.view;

import com.company.example.algorithm.AlgorithmBase;

import javax.swing.*;

public class AlgorithmButton extends JButton {

    public AlgorithmButton(AlgorithmBase algorithmBase) {
        this.addActionListener(e-> {
            algorithmBase.solve();
        });
    }

}
