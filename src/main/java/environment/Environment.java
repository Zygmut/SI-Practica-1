
package environment;

import agent.Robot;


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
        //perceptions.from(robot, map);
        //robot.processPerceptions(characteristics, perceptions); //update characteristics
        //action = robot.check()
        //action.execute()
        //wait animation
    }
    
}
