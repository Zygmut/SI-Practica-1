package agent;

import java.awt.Point;

import productionrules.BC;
import productionrules.Characteristic;

public class Robot extends GridAgent<Executable> {

    public enum Action implements Executable {

        MOVE_NORTH {

            @Override
            public void execute(Object robot) {
                System.out.println("[Robot.java] Action: " + this.toString());
                ((Robot) robot).setLooking(LookDirection.NORTH);
                Point currPosition = ((Robot) robot).getPosition();
                ((Robot) robot).setPosition(currPosition.x, currPosition.y - 1);
            }
        },
        MOVE_SOUTH {
            @Override
            public void execute(Object robot) {
                System.out.println("[Robot.java] Action: " + this.toString());
                ((Robot) robot).setLooking(LookDirection.SOUTH);
                Point currPosition = ((Robot) robot).getPosition();
                ((Robot) robot).setPosition(currPosition.x, currPosition.y + 1);
            }
        },
        MOVE_EAST {
            @Override
            public void execute(Object robot) {
                System.out.println("[Robot.java] Action: " + this.toString());
                ((Robot) robot).setLooking(LookDirection.EAST);
                Point currPosition = ((Robot) robot).getPosition();
                ((Robot) robot).setPosition(currPosition.x + 1, currPosition.y);
            }
        },
        MOVE_WEST {
            @Override
            public void execute(Object robot) {
                System.out.println("[Robot.java] Action: " + this.toString());
                ((Robot) robot).setLooking(LookDirection.WEST);
                Point currPosition = ((Robot) robot).getPosition();
                ((Robot) robot).setPosition(currPosition.x - 1, currPosition.y);
            }
        };

        public void execute(Robot robot) {
            System.out.println("NOT IMPLEMENTED");
        }
    }

    public enum LookDirection {
        NORTH, EAST, SOUTH, WEST
    }

    public enum Labels {
        Wall_NW,
        Not_Wall_NW,
        Wall_N,
        Not_Wall_N,
        Wall_NE,
        Not_Wall_NE,
        Wall_W,
        Not_Wall_W,
        Wall_E,
        Not_Wall_E,
        Wall_SW,
        Not_Wall_SW,
        Wall_S,
        Not_Wall_S,
        Wall_SE,
        Not_Wall_SE,
    };

    private Characteristic[] looking;

    public Robot() {
        this.bc = new BC<>();
        this.position = new Point(-1, -1);
        this.looking = new Characteristic[] { new Characteristic("Looking_North"), new Characteristic("Looking_East"),
                new Characteristic("Looking_South"), new Characteristic("Looking_West") };
        this.setLooking(LookDirection.NORTH);

        characteristics = new Characteristic[Labels.values().length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(Labels.values()[i].name());
        }
    }

    public Robot(int x, int y) {
        this.bc = new BC<>();
        this.position = new Point(x, y);
        this.looking = new Characteristic[] { new Characteristic("Looking_North"), new Characteristic("Looking_East"),
                new Characteristic("Looking_South"), new Characteristic("Looking_West") };
        this.setLooking(LookDirection.NORTH);

        // Initialize characteristics to add labels
        characteristics = new Characteristic[Labels.values().length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(Labels.values()[i].name());
        }

    }

    public Robot(Point position) {
        this.bc = new BC<>();
        this.position = position;
        this.looking = new Characteristic[] { new Characteristic("Looking_North"), new Characteristic("Looking_East"),
                new Characteristic("Looking_South"), new Characteristic("Looking_West") };
        this.setLooking(LookDirection.NORTH);

        // Initialize characteristics to add labels
        characteristics = new Characteristic[Labels.values().length];
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = new Characteristic(Labels.values()[i].name());
        }

    }

    public void setLooking(LookDirection look) {
        for (Characteristic c : looking) {
            c.setValue(false);
        }

        looking[LookDirection.valueOf(look.name()).ordinal()].setValue(true);
    }

    public Characteristic getLooking() {
        for (Characteristic c : looking) {
            if (c.getValue() == true) {
                return c;
            }
        }
        return null;
    }

    public Characteristic getLookingRef(LookDirection lookAt) {
        return looking[lookAt.ordinal()];
    }

    public void processPerceptions(boolean[] perceptions) {

        for (int i = 0; i < perceptions.length; i++) {
            characteristics[i * 2].setValue(perceptions[i]);
            characteristics[(i * 2) + 1].setValue(!perceptions[i]);
        }

    }

}
