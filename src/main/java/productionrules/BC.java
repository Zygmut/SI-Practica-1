package productionrules;

import java.util.LinkedList;

import java.util.Iterator;

public class BC<T> {
    private LinkedList<Rule<T>> contentRules;

    public BC() {
        this.contentRules = new LinkedList<>();
    }

    public BC(LinkedList<Rule<T>> contentRules) {
        this.contentRules = contentRules;
    }

    public void addRule(Rule<T> rule) {
        contentRules.add(rule);
    }

    public T check() {
        Iterator<Rule<T>> it = contentRules.iterator();

        while (it.hasNext()) {
            Rule<T> rule = it.next();
            if (rule.eval()) {
                return rule.getAction();
            }

        }

        return null;
    }

    public LinkedList<Rule<T>> getContentRules() {
        return contentRules;
    }

    public String toStringEvaluated() {
        Iterator<Rule<T>> it = contentRules.iterator();
        String str = "";

        while (it.hasNext()) {
            Rule<T> rule = it.next();
            str += rule.toString() + ": " + rule.eval() + "\n";
        }
        return str;

    }

    @Override
    public String toString() {
        Iterator<Rule<T>> it = contentRules.iterator();
        String str = "";

        while (it.hasNext()) {
            str += it.next().toString() + "\n";
        }
        return str;
    }

}
