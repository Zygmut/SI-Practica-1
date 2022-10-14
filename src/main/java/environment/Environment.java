
package environment;

import agent.Robot;
import java.awt.Point;

public class Environment {

    private Boolean[][] map;
    private Robot robot;

    public Environment(int n) {
        this.map = new Boolean[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = false;
            }
        }
    }

    public Environment(Boolean[][] map) {
        this.map = map;
    }

    public Boolean isObstacle(int i, int j) {
        return this.map[i][j];
    }

    public void setObstacle(int i, int j, boolean value) {
        this.map[i][j] = value;
    }

    public void toggleObstacle(int i, int j) {
        this.map[i][j] = !this.map[i][j];
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
                    perceptions[idx] = map[y][x];
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
