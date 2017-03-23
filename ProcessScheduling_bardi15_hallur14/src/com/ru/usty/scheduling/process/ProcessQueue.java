package com.ru.usty.scheduling.process;

/**
 * Created by bambinn on 23.3.2017.
 */
public class ProcessQueue {
    private int pd;
    private long pt;

    public ProcessQueue(int processID, long processTime) {
        this.pd = processID;
        this.pt = processTime;
    }

    public ProcessQueue() {
    }

    public int getId() {
        return this.pd;
    }

    public void add() {

    }
}
