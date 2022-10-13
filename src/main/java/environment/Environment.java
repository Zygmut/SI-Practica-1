
package environment;

import agent.Robot;
import java.awt.Point;


public class Environment {
    
    private Boolean[][] map;
    private Robot robot;
    
    public Environment(int n){
        this.map = new Boolean[n][n];
    }
    
    public Environment(Boolean[][] map){
        this.map = map;
    }
    
    public Boolean isObstacle(int i, int j){
        return this.map[i][j];
    }
    
    public void setObstacle(int i, int j, boolean value){
        this.map[i][j] = value;
    }
    
    public void toggleObstacle(int i, int j){
        this.map[i][j] = !this.map[i][j];
    }
    
    public void runIteration(){
        boolean[] perceptions = new boolean[8];
        //perceptions.from(robot, map);
        //robot.processPerceptions(perceptions); //update characteristics
        //action = robot.check()
        //action.execute()
        //wait animation
    }
    
    private boolean[] getPerceptions(){
        boolean[] perceptions = new boolean[8];
        
        Point robot = this.robot.getPosition();
        for (int i = -1; i < robot.x +1; i++) {
            for (int j = 0; j < 10; j++) {
                
                
            }
            boolean perception = perceptions[i];
            
        }
        
        return perceptions;
    }
    
}
