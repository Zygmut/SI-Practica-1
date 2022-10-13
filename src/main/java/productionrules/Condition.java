package productionrules;

public class Condition {

    LogicalExpresion logicalExpresion;
    String label;

    public Condition() {
        logicalExpresion = null;
        label = "None";
    }

    public Condition(LogicalExpresion lg, String label){
        this.logicalExpresion = lg;
        this.label = label;
    }

    public boolean eval(boolean[] characteristics) {
        return logicalExpresion.eval(characteristics);
    }

    @Override
    public String toString() {
        return label;
    }
}
