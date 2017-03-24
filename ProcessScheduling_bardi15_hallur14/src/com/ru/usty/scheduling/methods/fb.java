package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class fb {
    static final int _fbqueues = 7;
    ArrayList<Queue<Integer>> feedbackQueue;
    ArrayList<Integer> f_feedbackQueue;
    ProcessExecution p;
    private int quantum;
    private boolean queueEmpty;
    public fb(ProcessExecution processExecution, int quant) {
        p = processExecution;
        quantum = quant;
        feedbackQueue = new ArrayList<Queue<Integer>>();
        for(int i = 0; i < _fbqueues; i++){
            this.feedbackQueue.add(new LinkedList<Integer>());
        }
        f_feedbackQueue = new ArrayList<Integer>();
        queueEmpty = true;
        Thread thread = this.seekQueue();
        thread.start();
    }

    public void add(int processID) {
        feedbackQueue.get(0).add(processID);
    }

    public void finish(int processID) {
        f_feedbackQueue.add(processID);
    }

    public Thread seekQueue() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true){
                    queueEmpty = true;
                    for(int i = 0; i < _fbqueues; i++){
                        deque(i);
                    }
                }
            }
        });
        return t;
    }

    public void deque(int i) {
        while(!feedbackQueue.get(i).isEmpty()){
            for(int j = 0; j < i; j++){
                if(!feedbackQueue.get(j).isEmpty()){
                    queueEmpty = false;
                    break;
                }
            }
            if(!queueEmpty){
                break;
            }
            int process = feedbackQueue.get(i).remove();
            p.switchToProcess(process);
            long startTime = getTime();
            while(!f_feedbackQueue.contains(process)){
                if(getTime() > startTime + quantum){
                    break;
                }
            }
            if(!f_feedbackQueue.contains(process)){
                if(i< _fbqueues-1){
                    i++;
                }
                feedbackQueue.get(i).add(process);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!queueEmpty){
            return;
        }
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
