package productionrules;

public interface LogicalExpresion {
    boolean eval(boolean[] characteristics);
}