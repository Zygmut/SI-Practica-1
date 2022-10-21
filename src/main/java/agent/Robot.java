package agent;

import java.awt.Point;

import productionrules.BC;
import productionrules.Characteristic;

public class Robot extends GridAgent<Executable> {

    // ==========================================================================

    public enum Action implements Executable {

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

    private final String[] LABELS = { "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8" };

    public Robot() {
        this.bc = new BC<>();
        this.position = new Point(-1, -1);

        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }
    }

    public Robot(int x, int y) {
        this.bc = new BC<>();
        this.position = new Point(x, y);

        // Initialize characteristics to add labels
        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }

    }

    public Robot(Point position) {
        this.bc = new BC<>();
        this.position = position;

        // Initialize characteristics to add labels
        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }

    }

    public void processPerceptions(boolean[] perceptions) {

        enum PerceptionIndex {
            TOP_LEFT, TOP, TOP_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT
        }

        System.out.println("Robot perceptions: ");
        for (int i = 0; i < PerceptionIndex.values().length; i++) {
            System.out.println(PerceptionIndex.values()[i] + ": " + perceptions[i]);
        }
    }

}
