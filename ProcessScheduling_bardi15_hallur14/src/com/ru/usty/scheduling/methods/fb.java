package com.ru.usty.scheduling.methods;

import com.ru.usty.scheduling.process.ProcessExecution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class fb {
    static final int numOfFeedbackQueues = 7;
    ArrayList<Queue<Integer>> feedbackQueue;
    ArrayList<Integer> finished;
    ProcessExecution p;
    private int quantum;
    public fb(ProcessExecution processExecution, int quant) {
        p = processExecution;
        quantum = quant;
        feedbackQueue = new ArrayList<Queue<Integer>>();
        for(int i = 0; i<numOfFeedbackQueues; i++){
            this.feedbackQueue.add(new LinkedList<Integer>());
        }
        this.finished = new ArrayList<Integer>();
        Thread thread = this.sleep();
        thread.start();
    }

    public void add(int processID) {
        feedbackQueue.get(0).add(processID);
    }

    public void finish(int processID) {
        finished.add(processID);

    }

    public Thread sleep() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true){
                    boolean upperQueueNotEmpty = false;
                    for(int i = 0; i < numOfFeedbackQueues; i++){
                        while(!feedbackQueue.get(i).isEmpty()){
                            for(int j = 0; j < i; j++){
                                if(!feedbackQueue.get(j).isEmpty()){
                                    upperQueueNotEmpty = true;
                                    break;
                                }
                            }
                            if(upperQueueNotEmpty){
                                break;
                            }
                            int process = feedbackQueue.get(i).remove();
                            p.switchToProcess(process);
                            long startTime = System.currentTimeMillis();
                            while(!finished.contains(process)){
                                if(System.currentTimeMillis()>startTime + quantum){
                                    break;
                                }
                            }
                            if(!finished.contains(process)){
                                if(i<numOfFeedbackQueues-1){
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
                        if(upperQueueNotEmpty){
                            break;
                        }
                    }
                }
            }
        });
        return t;
    }
}
