package com.ru.usty.scheduling.process;

/**
 * Created by bambinn on 24.3.2017.
 */
public class TimeKeep {
    private long aT;
    private long sT;
    private long cT;

    public TimeKeep() {
    }

    public void start() {
        sT = getTime();
    }

    public void end() {
        cT = getTime();
    }

    public long getTurnAroundTime() {
        return cT - aT;
    }

    public long getResponseTime() {
        return sT - aT;
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
