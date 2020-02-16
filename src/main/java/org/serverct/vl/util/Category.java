package org.serverct.vl.util;

import org.serverct.vl.analyzer.Executor;
import org.serverct.vl.analyzer.Stat;

import java.util.HashMap;

public final class Category {
    HashMap<String, Stat> statmap = new HashMap<>();
    HashMap<String, Executor> execmap = new HashMap<>();
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Stat addStat(String ID) {
        Stat a = new Stat(ID, this);
        statmap.put(ID, a);
        return a;
    }

    public void addStat(Stat s) {
        statmap.put(s.getID(), s);
    }

    public Executor addExecutor(String ID) {
        Executor a = new Executor(ID, this);
        execmap.put(ID, a);
        return a;
    }

    public void addExecutor(Executor exec) {
        execmap.put(exec.getID(), exec);
    }
}
