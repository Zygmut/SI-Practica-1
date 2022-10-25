
package environment;

import agent.Executable;
import agent.GridAgent;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import utils.MutableBoolean;

public class Environment<T> implements Serializable {

    private static final String basePath = "test_maps/";
    private MutableBoolean[][] map;
    private T agent;

    public Environment() {
        this.map = null;
        this.agent = null;
    }

    public Environment(int n) {
        this.map = new MutableBoolean[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new MutableBoolean();
            }
        }
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

    public void setAgent(T agent) {
        this.agent = agent;
    }

    public T getAgent() {
        return this.agent;
    }

    public int getSize() {
        return this.map.length;
    }

    @SuppressWarnings("unchecked")
    public void runIteration() {
        ((GridAgent<Executable>) agent).processPerceptions(getPerceptions(agent));
        ((GridAgent<Executable>) agent).checkBC().execute((GridAgent<Executable>) agent);
        System.out.println("step");
    }

    @SuppressWarnings("unchecked")
    private boolean[] getPerceptions(T agent) {
        boolean[] perceptions = new boolean[8];
        int idx = 0;
        Point robotPos = ((GridAgent<T>) agent).getPosition();

        for (int y = robotPos.y - 1; y <= robotPos.y + 1; y++) {
            for (int x = robotPos.x - 1; x <= robotPos.x + 1; x++) {
                if (x == robotPos.x && y == robotPos.y) {
                    continue;
                }

                try {
                    perceptions[idx] = map[x][y].is();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Map outer perimeter
                    perceptions[idx] = true;
                }
                idx++;
            }
        }

        return perceptions;
    }

    public static MutableBoolean[][] useMap(String path) {
        String filePath = path;
        if (!path.endsWith(".map")) {
            filePath = path + ".map";
        }
        
        filePath = basePath + filePath;

        MutableBoolean[][] map = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            map = (MutableBoolean[][]) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void saveMap(String path) {
        String filePath = path;
        if (!path.endsWith(".map")) {
            filePath = path + ".map";
        }
        
        filePath = basePath + filePath;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(this.map);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
