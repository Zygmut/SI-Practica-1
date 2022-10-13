package productionrules;

import java.util.LinkedList;
import agent.Robot.Action;
import java.util.Iterator;

public class BC {
    private LinkedList<Rule> contentRules;

    public BC() {
        this.contentRules = new LinkedList<>();
    }

    public BC(LinkedList<Rule> contentRules) {
        this.contentRules = contentRules;
    }

    public void addRule(Rule rule) {
        contentRules.add(rule);
    }

    public Action check(boolean[] characteristics) {
        Iterator<Rule> it = contentRules.iterator();

        while (it.hasNext()) {
            Rule rule = it.next();
            if (rule.eval(characteristics)) {
                return rule.getAction();
            }

        }

        return null;
    }

    @Override
    public String toString() {
        Iterator<Rule> it = contentRules.iterator();
        String str = "";

        while (it.hasNext()) {
            str += it.next().toString() + "\n";
        }
        return str;
    }

}
