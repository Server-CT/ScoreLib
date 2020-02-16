package org.serverct.vl.analyzer;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.serverct.vl.Main;
import org.serverct.vl.bean.Mode;
import org.serverct.vl.events.ConditionsMetEvent;
import org.serverct.vl.interfaces.Base;
import org.serverct.vl.util.Category;

import java.util.HashMap;
import java.util.UUID;

public class Executor extends Stat implements Base {
    HashMap<Integer, Boolean> Acts = new HashMap<>();
    Mode mode = Mode.TRIG;
    int alive;
    int increase;
    BukkitRunnable r = new BukkitRunnable() {
        @Override
        public void run() {
            Executor.super.Map.entrySet().forEach(a -> {
                long time = System.currentTimeMillis();
                if (time - a.getValue().lastTime >= alive && alive != -1) {
                    Executor.super.setScore(a.getKey(), a.getValue().score - 1);
                }
            });
            if (mode.equals(Mode.CHECKER) || mode.equals(Mode.MIX)) {
                Executor.super.Map.entrySet().forEach(a -> {
                    if (Acts.get(a.getValue().score)) {
                        Main.i.getServer().getPluginManager().callEvent(new ConditionsMetEvent(Executor.super.getID(), a.getKey(), a.getValue().score));
                    }
                });
            }
        }
    };

    public Executor(String ID, Category category) {
        super(ID, category);
    }

    public void putAction(int score, Boolean act) {
        Acts.put(score, act);
    }

    public boolean clearAction() {
        Acts.clear();
        return true;
    }

    public boolean clearAction(int score) {
        Acts.remove(score);
        return true;
    }

    public boolean addScore(UUID UniqueID, int score) {
        if (Bukkit.getPlayer(UniqueID) != null && Bukkit.getPlayer(UniqueID).hasPermission("scorelib." + super.getCategory().getName() + ".bypass")) {
            return false;
        }
        if (Acts.containsKey(score) && !mode.equals(Mode.CHECKER)) {
            Main.i.getServer().getPluginManager().callEvent(new ConditionsMetEvent(super.getID(), UniqueID, super.Map.get(UniqueID).score + 1));
        }
        return super.addScore(UniqueID, score);
    }

    public void setAliveTime(int second) {
        alive = second;
    }

    public void start(int delay, int increase) {
        this.increase = increase;
        r.runTaskTimerAsynchronously(Main.i, 0, delay);
    }

    public void stop() {
        r.cancel();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
