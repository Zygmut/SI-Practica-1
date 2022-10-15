package com.hallwayrobot;

import gui.RobotGui;

public class Main {

    public static void main(String[] args) {
        (new Main()).start();
    }

    public void start() {
        
        RobotGui gui = new RobotGui();
        gui.showGui();

        
    }

}