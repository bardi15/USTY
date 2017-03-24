package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.LinkedList;
import java.util.Queue;

public class rr {
    private Queue<Integer> processQueue;
    private ProcessExecution p;
    private boolean runningProcess;
    private int quantum;
    public rr(ProcessExecution processExecution, int quant) {
        processQueue = new LinkedList<Integer>();
        p = processExecution;
        runningProcess = false;
        quantum = quant;
    }

    public void add(int processID) {
        processQueue.add(processID);
        if(!runningProcess){
            p.switchToProcess(processID);
            runningProcess = true;
        }
        Thread t = this.sleep(processQueue.element());
        t.start();
    }

    public void finish(int processID) {
        processQueue.remove(processID);
        runningProcess = false;
        if(!processQueue.isEmpty()) {
            int id = processQueue.element();
            Thread thread = this.sleep(id);
            thread.start();
            runningProcess = true;
        }
    }

    public Thread sleep(int pid) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    p.switchToProcess(pid);
                    processQueue.remove(pid);
                    processQueue.add(pid);
                    Thread.sleep(quantum);

                } catch (InterruptedException e) {
                    System.out.println("Failure in RR method");
                    e.printStackTrace();
                }
            }
        });
        return t;
    }
}
