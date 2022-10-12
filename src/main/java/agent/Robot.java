/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

/**
 *
 * @author ccf20
 */
public class Robot {

    public enum Action {

        MOVE_NORTH {

            @Override
            public void execute() {
                System.out.println("NORTH");
            }
        },
        MOVE_SOUTH {
            @Override
            public void execute() {
                System.out.println("SOUTH");
            }
        },
        MOVE_EAST {
            @Override
            public void execute() {
                System.out.println("EAST");
            }
        },
        MOVE_WEST {
            @Override
            public void execute() {
                System.out.println("WEST");
            }
        };

        public void execute() {
            System.out.println("NOT IMPLEMENTED");
        }
    }

}
