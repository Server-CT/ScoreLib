package org.serverct.vl;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main i;
    public static boolean debug;

    @Override
    public void onEnable() {
        i = this;
        if (!this.getDataFolder().exists()) {
            getConfig().addDefault("debug", false);
            getConfig().options().copyDefaults(true);
            saveConfig();
            reloadConfig();
        }
        debug = getConfig().getBoolean("debug");
        getLogger().info("Loaded!!!");
    }

    @Override
    public void onDisable() {
    }
}
