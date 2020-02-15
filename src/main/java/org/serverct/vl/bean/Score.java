package org.serverct.vl.bean;

public class Score {
    public int score;
    public long lastTime;

    public Score(int s) {
        score = s;
        lastTime = System.currentTimeMillis();
    }
}
