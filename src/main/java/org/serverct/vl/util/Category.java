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

    public Stat addStat(String ID, boolean ReadOnly) {
        Stat a = new Stat(ReadOnly, ID, this);
        statmap.put(ID, a);
        return a;
    }

    public Executor addExecutor(String ID, boolean ReadOnly) {
        Executor a = new Executor(ReadOnly, ID, this);
        execmap.put(ID, a);
        return a;
    }
}
