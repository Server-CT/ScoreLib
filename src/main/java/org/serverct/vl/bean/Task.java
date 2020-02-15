package org.serverct.vl.bean;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Task {
    Plugin pl;
    BukkitRunnable runnable;

    public Task(Plugin p, BukkitRunnable r) {
        pl = p;
        runnable = r;
    }

    public void run() {
        runnable.runTaskAsynchronously(pl);
    }
}
