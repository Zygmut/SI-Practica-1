package com.hallwayrobot;

import java.util.Iterator;
import java.util.LinkedList;

import agent.Robot;
import agent.Robot.Action;
import gui.RobotGui;
import productionrules.BC;
import productionrules.Condition;
import productionrules.LogicalExpresion;
import productionrules.Rule;

public class Main {

    public static void main(String[] args) {
        // RobotGui gui = new RobotGui();
        // gui.showGui();

        BC bc = new BC();
        Robot r = new Robot();
        boolean[] characteristics = new boolean[] { false, true, false, false, false, false, false, false };

        bc.addRule(new Rule(new Condition((vals) -> {
            return vals[0] || vals[1];
        }, "1"), Action.MOVE_EAST));
        bc.addRule(new Rule(new Condition((vals) -> {
            return vals[0] && vals[1];
        }, "2"), Action.MOVE_NORTH));
        bc.addRule(new Rule(new Condition((vals) -> {
            return false;
        }, "3"), Action.MOVE_SOUTH));
        bc.addRule(new Rule(new Condition((vals) -> {
            return true;
        }, "4"), Action.MOVE_WEST));

        System.out.println(bc.toString());

        LinkedList<Rule> cr = bc.getContentRules();
        Iterator<Rule> it = cr.iterator();

        while (it.hasNext()) {
            Rule rule = it.next();
            System.out.println(rule.eval(characteristics));
        }
        characteristics[1] = false;
        cr = bc.getContentRules();
        it = cr.iterator();

        while (it.hasNext()) {
            Rule rule = it.next();
            System.out.println(rule.eval(characteristics));
        }

    }
}