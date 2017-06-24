package org.eddy.xml.rule;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;

import java.util.Objects;

/**
 * Created by eddy on 2017/4/28.
 */
public abstract class Comparator {

    public abstract DataNode check(RuleNode ruleNode);

    public boolean script(String mapper, Object[] param, String script) {
        Objects.requireNonNull(script);

        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        shell.setVariable("mapper", mapper);
        shell.setVariable("params", param);
        Object returnValue = shell.evaluate(script);

        return (boolean) returnValue;
    }
}
