/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productionrules;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author ccf20
 */
public class BC {
    private LinkedList<Rule> contentRules;

    BC() {
        this.contentRules = new LinkedList<>();
    }

    BC(LinkedList contentRules) {
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
            str += it.next() + "\n";
        }
        return str;
    }

}
