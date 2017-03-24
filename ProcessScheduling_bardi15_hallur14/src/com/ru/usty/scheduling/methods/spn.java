package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.Policy;
import com.ru.usty.scheduling.process.ProcessComparator;
import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.PriorityQueue;

public class spn {
    private PriorityQueue<Integer> priorityQueue;
    private ProcessExecution p;
    public spn(ProcessExecution processExecution) {
        priorityQueue = new PriorityQueue(new ProcessComparator(Policy.SPN, processExecution));
        p = processExecution;

    }

    public void add(int processID) {
        if(priorityQueue.isEmpty()){
            priorityQueue.add(processID);
            p.switchToProcess(processID);
        } else {
            priorityQueue.add(processID);
        }
    }

    public void finish(int processID) {
        priorityQueue.removeIf(p -> p == processID);
        if(!priorityQueue.isEmpty()){
            p.switchToProcess(priorityQueue.peek());
        }
    }
}
