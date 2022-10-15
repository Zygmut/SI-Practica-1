package com.hallwayrobot;

import gui.RobotGui;
import utils.ImageLoader;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        (new Main()).start();
    }

    public void start() {

        RobotGui gui = new RobotGui();
        gui.showGui();

        BufferedImage im = ImageLoader.loadImage("src/main/java/images/box.png");
        
    }

}