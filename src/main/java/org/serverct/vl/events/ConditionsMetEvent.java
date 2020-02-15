package org.serverct.vl.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class ConditionsMetEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private String TaskID;
    private UUID Key;
    private int score;

    public ConditionsMetEvent(String TaskID, UUID Key, int score) {
        this.TaskID = TaskID;
        this.Key = Key;
        this.score = score;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getTaskID() {
        return TaskID;
    }

    public UUID getUUID() {
        return Key;
    }

    public int getScore() {
        return score;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
