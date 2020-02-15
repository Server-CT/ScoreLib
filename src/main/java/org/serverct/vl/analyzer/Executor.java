package org.serverct.vl.analyzer;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.serverct.vl.Main;
import org.serverct.vl.bean.Mode;
import org.serverct.vl.events.ConditionsMetEvent;
import org.serverct.vl.interfaces.Base;

import java.util.HashMap;
import java.util.UUID;

public class Executor extends Stat implements Base {
    HashMap<Integer, Boolean> Acts = new HashMap<>();
    Mode mode = Mode.TRIG;
    int alive;
    BukkitRunnable r = new BukkitRunnable() {
        @Override
        public void run() {
            Executor.super.Map.entrySet().forEach(a -> {
                long time = System.currentTimeMillis();
                if (time - a.getValue().lastTime >= alive && alive != -1) {
                    Executor.super.setScore(a.getKey(), 0);
                }
            });
            Executor.super.Map.entrySet().forEach(a -> {
                if (Acts.get(a.getValue().score)) {
                    Main.i.getServer().getPluginManager().callEvent(new ConditionsMetEvent(Executor.super.getID(), a.getKey(), a.getValue().score));
                }
            });
        }
    };

    public Executor(boolean readOnly, String ID) {
        super(readOnly, ID);
    }

    public void putAction(int score, Boolean act) {
        Acts.put(score, act);
    }

    public boolean clearAction() {
        if (super.isReadOnly()) {
            return false;
        }
        Acts.clear();
        return true;
    }

    public boolean clearAction(int score) {
        if (super.isReadOnly()) {
            return false;
        }
        Acts.remove(score);
        return true;
    }

    public boolean addScore(UUID UniqueID, int score) {
        if (Bukkit.getPlayer(UniqueID) != null && Bukkit.getPlayer(UniqueID).hasPermission("scorelib.bypass")) {
            return false;
        }
        if (Acts.containsKey(score) && (mode.equals(Mode.TRIG) || mode.equals(Mode.MIX))) {
            Main.i.getServer().getPluginManager().callEvent(new ConditionsMetEvent(super.getID(), UniqueID, super.Map.get(UniqueID).score + 1));
        }
        return super.addScore(UniqueID, score);
    }

    public void setAliveTime(int second) {
        alive = second;
    }

    public void start(int delay) {
        if (mode.equals(Mode.CHECKER)) {
            r.runTaskTimerAsynchronously(Main.i, 0, delay);
        }
    }

    public void stop() {
        if (mode.equals(Mode.CHECKER)) {
            r.cancel();
        }
    }
}
