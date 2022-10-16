
package environment;

import agent.Robot;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import utils.MutableBoolean;

public class Environment implements Serializable {

    private MutableBoolean[][] map;
    private Robot robot;

    public Environment() {
        this.map = null;
        this.robot = null;
    }

    public Environment(int n) {
        this.map = new MutableBoolean[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new MutableBoolean();
            }
        }
        this.robot = new Robot();
    }

    public Environment(MutableBoolean[][] map) {
        this.map = map;
    }

    public boolean isObstacle(int i, int j) {
        return this.map[i][j].is();
    }

    public MutableBoolean getIsObstacleReference(int i, int j) {
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
    
    public Robot getRobot(){
        return this.robot;
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

    public static Environment useMap(String path) {
        String filePath = path;
        if (!path.endsWith(".map")) {
            filePath = path + ".map";
        }

        Environment env = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            env = (Environment) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return env;
    }

    public void saveMap(String path) {
        String filePath = path;
        if (!path.endsWith(".map")) {
            filePath = path + ".map";
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
