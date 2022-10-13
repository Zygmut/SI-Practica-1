package agent;

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
    
    //==========================================================================
    
    private BC bc; 
    
    public Robot(){
        this.bc = new BC();
    }
    
    public void addRule(Rule rule){
        
        this.bc.addRule(rule);
    }
    
    public void addRule(Characteristic[] characteristics, int[] indices, Action action){
        this.bc.addRule(new Rule(new Condition(selectCharacteristics(characteristics, indices)), action));
    }
    
    public Action checkBC(){
        return this.bc.check();
    }
    
    public String printBC(){
        return this.bc.toString();
    }
    
    public String printEvaluatedBC(){
        return this.bc.toStringEvaluated();
    }
    
    
    //==========================================================================
        
    private Characteristic[] selectCharacteristics(Characteristic[] characteristics, int[] indices){
        Characteristic[] resultCharacteristics = new Characteristic[indices.length];
        for (int i = 0; i < indices.length; i++) {
            resultCharacteristics[i] = characteristics[indices[i]];
        }
        return resultCharacteristics;
    }
    
    
}
