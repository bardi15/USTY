package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.LinkedList;
import java.util.Queue;

public class fcfs {
    private Queue<Integer> processQueue;
    private ProcessExecution p;
    public fcfs(ProcessExecution processExecution) {
        processQueue = new LinkedList<>();
        p = processExecution;

    }

    public void add(int processID) {
        this.processQueue.add(processID);
        if(this.processQueue.size() == 1) {
            this.p.switchToProcess(processID);
        }
    }

    public void finish(int processID) {
        this.processQueue.poll();
        if(!this.processQueue.isEmpty()) {
            this.p.switchToProcess(processQueue.peek());
        }
    }
}
