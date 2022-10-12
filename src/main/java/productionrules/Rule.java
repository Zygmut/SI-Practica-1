package productionrules;

import agent.Robot.Action;

public class Rule {

    private Condition condition;
    private Action action;

    Rule() {
        condition = new Condition();
        action = Action.values()[0];
    }

    Rule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    public boolean eval(boolean[] characteristics) {
        return true;
        // return condition.eval(characteristics);
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return condition.toString() + " -> " + action.toString();
    }

}
