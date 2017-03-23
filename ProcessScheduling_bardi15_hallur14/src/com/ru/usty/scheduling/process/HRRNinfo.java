package com.ru.usty.scheduling.process;


import java.util.PriorityQueue;

public class HRRNinfo extends PriorityQueue {
    private int pd;
    private ProcessInfo pi;
    public HRRNinfo (int processID, ProcessInfo pr) {
        this.pd = processID;
        this.pi = pr;
    }

    public int getId() {
        return this.pd;
    }
}
