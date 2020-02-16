package org.serverct.vl.analyzer;

import org.bukkit.Bukkit;
import org.serverct.vl.bean.Score;
import org.serverct.vl.interfaces.Base;
import org.serverct.vl.util.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Stat implements Base {
    HashMap<UUID, Score> Map = new HashMap<>();
    private boolean readOnly;
    private String ID;

    public Stat(boolean readOnly, String ID, Category category) {
        this.ID = ID;
        this.readOnly = readOnly;
    }

    @Override
    public int getScore(UUID UniqueID) {
        return Map.getOrDefault(UniqueID, new Score(0)).score;
    }

    @Override
    public boolean addScore(UUID UniqueID, int score) {
        if (readOnly) return false;
        if (Bukkit.getPlayer(UniqueID) != null && Bukkit.getPlayer(UniqueID).hasPermission("scorelib.bypass")) {
            return false;
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
        if (readOnly) {
            return false;
        }
        Map.put(UniqueID, new Score(score));
        return true;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public String getID() {
        return ID;
    }
}
