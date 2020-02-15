package org.serverct.vl.interfaces;

import java.util.UUID;

public interface Base {
    int getScore(UUID UniqueID);

    boolean addScore(UUID UniqueID, int score);

    void clear();
}
