package org.serverct.vl.analyzer;

import org.bukkit.Bukkit;
import org.serverct.vl.Main;
import org.serverct.vl.bean.Score;
import org.serverct.vl.interfaces.Base;
import org.serverct.vl.util.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Stat implements Base {
    HashMap<UUID, Score> Map = new HashMap<>();
    private String ID;
    private Category c;

    public Stat(String ID, Category category) {
        this.ID = ID;
        c = category;
    }

    @Override
    public int getScore(UUID UniqueID) {
        return Map.getOrDefault(UniqueID, new Score(0)).score;
    }

    @Override
    public boolean addScore(UUID UniqueID, int score) {
        if (Bukkit.getPlayer(UniqueID) != null && Bukkit.getPlayer(UniqueID).hasPermission("scorelib." + c.getName() + ".bypass")) {
            return false;
        }
        if (Main.debug) {
            Main.i.getLogger().info("UUID: " + UniqueID.toString() + " | add: " + score + " | total:" + getScore(UniqueID));
        }
        Score s = Map.getOrDefault(UniqueID, new Score(0));
        s.score = s.score + score;
        s.lastTime = System.currentTimeMillis();
        Map.put(UniqueID, s);
        return true;
    }

    @Override
    public void clear() {
        Map.clear();
    }

    public ArrayList<UUID> fromScore(int s) {
        ArrayList<UUID> list = new ArrayList<>();
        Map.keySet().forEach(k -> {
            if (Map.get(k).score == s) {
                list.add(k);
            }
        });
        return list;
    }

    public boolean setScore(UUID UniqueID, int score) {
        if (Bukkit.getPlayer(UniqueID) != null && Bukkit.getPlayer(UniqueID).hasPermission("scorelib." + c.getName() + ".bypass")) {
            return false;
        }
        if (Main.debug) {
            Main.i.getLogger().info(UniqueID.toString() + " has been set to " + score);
        }
        Map.put(UniqueID, new Score(score));
        return true;
    }

    public String getID() {
        return ID;
    }

    public Category getCategory() {
        return c;
    }
}
