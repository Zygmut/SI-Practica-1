package agent;

import java.awt.Point;
import productionrules.BC;
import productionrules.Characteristic;
import productionrules.Condition;
import productionrules.Rule;

public abstract class GridAgent<T> {

    protected Characteristic[] characteristics;
    protected BC<T> bc;
    protected Point position;

    public void addProdRule(Rule<T> rule) {
        this.bc.addProdRule(rule);
    }

    public void addProdRule(int[] indices, T action) {
        this.bc.addProdRule(new Rule<T>(new Condition(selectCharacteristics(this.characteristics, indices)), action));
    }

    public T checkBC() {
        return this.bc.check();
    }

    public String printBC() {
        return this.bc.toString();
    }

    public String printEvaluatedBC() {
        return this.bc.toStringEvaluated();
    }

    public void processPerceptions(boolean[] perceptions) {
    };

    public void setCharacteristics(Characteristic[] characteristics) {
        this.characteristics = characteristics;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    // ==========================================================================

    private Characteristic[] selectCharacteristics(Characteristic[] characteristics, int[] indices) {
        Characteristic[] resultCharacteristics = new Characteristic[indices.length];
        for (int i = 0; i < indices.length; i++) {
            resultCharacteristics[i] = characteristics[indices[i]];
        }
        return resultCharacteristics;
    }

}
