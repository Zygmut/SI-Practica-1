package productionrules;

import java.util.LinkedList;
import agent.Robot.Action;

import java.io.Serializable;
import java.util.Iterator;

public class BC implements Serializable{
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

    public Action check() {
        Iterator<Rule> it = contentRules.iterator();

        while (it.hasNext()) {
            Rule rule = it.next();
            if (rule.eval()) {
                return rule.getAction();
            }

        }

        return null;
    }

    public LinkedList<Rule> getContentRules() {
        return contentRules;
    }

    public String toStringEvaluated() {
        Iterator<Rule> it = contentRules.iterator();
        String str = "";

        while (it.hasNext()) {
            Rule rule = it.next();
            str += rule.toString() + ": " + rule.eval() + "\n";
        }
        return str;

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
