package com.ru.usty.scheduling;
import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.Comparator;

public class ProcessAssist {
    private int pid;
    private long eT;
    private long wT;
    private long sT;

    public ProcessAssist(int processID, ProcessExecution processExecution){
        this.pid = processID;
        this.eT = processExecution.getProcessInfo(processID).elapsedExecutionTime;
        this.wT = processExecution.getProcessInfo(processID).elapsedWaitingTime;
        this.sT = processExecution.getProcessInfo(processID).totalServiceTime;
    }

    public long ExecutionTime() {
        return this.eT;
    }

    public long WaitingTime() {
        return this.wT;
    }

    public long ServiceTime() {
        return this.sT;
    }

    public int getId() {
        return this.pid;
    }
}
