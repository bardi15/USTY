package com.ru.usty.scheduling.process;

import java.util.PriorityQueue;

public class SPNinfo extends PriorityQueue {
    private int pd;
    private long pt;

    public SPNinfo(int processID, long processTime) {
        this.pd = processID;
        this.pt = processTime;
    }

    public int getId() {
        return this.pd;
    }

    public void add() {

    }
}