
package environment;

import agent.Robot;
import java.awt.Point;
import utils.MutableBoolean;

public class Environment {

    private MutableBoolean[][] map;
    private Robot robot;

    public Environment(int n) {
        this.map = new MutableBoolean[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].setValue(false);
            }
        }
    }

    public Environment(MutableBoolean[][] map) {
        this.map = map;
    }

    public boolean isObstacle(int i, int j) {
        return this.map[i][j].is();
    }
    
    public MutableBoolean getIsObstacle(int i, int j) {
        return this.map[i][j];
    }

    public void setObstacle(int i, int j, boolean value) {
        this.map[i][j].setValue(value);
    }

    public void toggleObstacle(int i, int j) {
        this.map[i][j].toggle();
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void runIteration() {
        robot.processPerceptions(getPerceptions(robot));
        // robot.checkBC().execute();

        // TODO: wait animation
    }

    private boolean[] getPerceptions(Robot robot) {
        boolean[] perceptions = new boolean[8];
        int idx = 0;
        Point robotPos = robot.getPosition();

        for (int y = robotPos.y - 1; y <= robotPos.y + 1; y++) {
            for (int x = robotPos.x - 1; x <= robotPos.x + 1; x++) {
                if (x == robotPos.x && y == robotPos.y) {
                    continue;
                }

                try {
                    perceptions[idx] = map[y][x].is();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Map outer perimeter
                    perceptions[idx] = true;
                }
                idx++;
            }
        }

        return perceptions;
    }

}
