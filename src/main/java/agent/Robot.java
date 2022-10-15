package agent;

import java.awt.Point;
import productionrules.BC;
import productionrules.Characteristic;
import productionrules.Condition;
import productionrules.Rule;

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

    // ==========================================================================

    private final String[] LABELS = { "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8" };
    private final Characteristic[] characteristics;

    private BC bc;
    private Point position;

    public Robot() {
        this.position = new Point();

        // Initialize characteristics to add labels
        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }

        initBC();
    }

    public Robot(int x, int y) {
        this.position = new Point(x, y);

        // Initialize characteristics to add labels
        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }

        initBC();
    }

    private void initBC() {
        this.bc = new BC();

        // TODO: Add rules
    }

    public Robot(Point position) {
        this.position = position;

        // Initialize characteristics to add labels
        characteristics = new Characteristic[LABELS.length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(LABELS[i]);
        }

        initBC();
    }

    private void addRule(Rule rule) {
        this.bc.addRule(rule);
    }

    private void addRule(int[] indices, Action action) {
        this.bc.addRule(new Rule(new Condition(selectCharacteristics(this.characteristics, indices)), action));
    }

    public Action checkBC() {
        return this.bc.check();
    }

    public String printBC() {
        return this.bc.toString();
    }

    public String printEvaluatedBC() {
        return this.bc.toStringEvaluated();
    }

    public void processPerceptions(boolean[] perceptions) {

        enum PerceptionIndex {
            TOP_LEFT, TOP, TOP_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT
        }

        // ? DEBUG
        // System.out.println("Robot perceptions: ");
        // for (int i = 0; i < PerceptionIndex.values().length; i++) {
        // System.out.println(PerceptionIndex.values()[i] + ": " + perceptions[i]);
        // }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    // ==========================================================================

    private Characteristic[] selectCharacteristics(Characteristic[] characteristics, int[] indices) {
        Characteristic[] resultCharacteristics = new Characteristic[indices.length];
        for (int i = 0; i < indices.length; i++) {
            resultCharacteristics[i] = characteristics[indices[i]];
        }
        return resultCharacteristics;
    }

}
