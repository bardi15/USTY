package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.Policy;
import com.ru.usty.scheduling.process.ProcessComparator;
import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.PriorityQueue;

public class srt {
    private PriorityQueue<Integer> priorityQueue;
    private ProcessExecution p;
    public srt(ProcessExecution processExecution) {
        priorityQueue = new PriorityQueue(new ProcessComparator(Policy.SRT, processExecution));
        p = processExecution;
    }

    public void add(int processID) {
        if(priorityQueue.isEmpty()) {
            priorityQueue.add(processID);
            p.switchToProcess(processID);
        }
        else  {
            priorityQueue.add(processID);
            int lowest = processID;
            for (Integer e : priorityQueue) {
                long eLow = getRemainingTime(e);
                long gLow = getRemainingTime(lowest);
                if (getRemainingTime(e) < getRemainingTime(lowest)) {
                    lowest = e;
                }
                p.switchToProcess(lowest);
            }
        }
    }

    public void finish(int processID) {
        priorityQueue.removeIf(p -> p == processID);
        if(!priorityQueue.isEmpty()){
            p.switchToProcess(priorityQueue.peek());
        }
    }

    private long getRemainingTime(int id) {
        return p.getProcessInfo(id).totalServiceTime - p.getProcessInfo(id).elapsedExecutionTime;
    }
}
