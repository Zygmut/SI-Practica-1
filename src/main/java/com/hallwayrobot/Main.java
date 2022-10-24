package com.hallwayrobot;

import gui.RobotGui;

public class Main {

    public static void main(String[] args) {
        (new Main()).start();
    }

    public void start() {
        RobotGui gui = new RobotGui();
        gui.showGui();

//        Environment<Robot> env = new Environment<>(Environment.useMap(".\\test_maps\\test-8x8.map"));
//        Robot r = new Robot();
//        env.setAgent(r);
//        gui.setEnv(env);
    }

}