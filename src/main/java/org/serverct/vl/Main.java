package org.serverct.vl;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main i;

    @Override
    public void onEnable() {
        i = this;
        getLogger().info("Loaded!!!");
    }

    @Override
    public void onDisable() {
    }
}
