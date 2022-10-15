package productionrules;

import java.io.Serializable;

public class Characteristic implements Serializable {
    private boolean value = true;
    private String label;

    public Characteristic(String label) {
        this.label = label;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value){
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }

}