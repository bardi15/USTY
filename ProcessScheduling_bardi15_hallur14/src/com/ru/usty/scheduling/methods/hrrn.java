package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.Policy;
import com.ru.usty.scheduling.process.ProcessComparator;
import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class hrrn {
    private PriorityQueue<Integer> priorityQueue;
    private ProcessExecution p;
    ArrayList<Integer> processIDs;
    public hrrn(ProcessExecution processExecution) {
        priorityQueue = new PriorityQueue(new ProcessComparator(Policy.HRRN, processExecution));
        p = processExecution;
        processIDs = new ArrayList<Integer>();
    }

    public void add(int processID) {
        this.processIDs.add(processID);
        if(this.priorityQueue.isEmpty()){
            this.priorityQueue.add(processID);
            this.p.switchToProcess(processID);
        } else {
            this.priorityQueue.add(processID);
        }
    }

    public void finish(int processID) {
        this.priorityQueue.removeIf(p -> p == processID);
        this.processIDs.remove(Integer.valueOf(processID));
        this.priorityQueue.clear();

        for (int i = 0; i <  this.processIDs.size(); i++) {
            this.priorityQueue.add(this.processIDs.get(i));
        }

        if(!this.priorityQueue.isEmpty()){
            this.p.switchToProcess(this.priorityQueue.peek());
        }
    }

    private long getRemainingTime(int id) {
        return p.getProcessInfo(id).totalServiceTime - p.getProcessInfo(id).elapsedExecutionTime;
    }
}
