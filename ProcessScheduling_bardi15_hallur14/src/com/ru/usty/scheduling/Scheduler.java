package com.ru.usty.scheduling;
import com.ru.usty.scheduling.process.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import com.ru.usty.scheduling.process.ProcessExecution;

public class Scheduler {

    ProcessExecution processExecution;
    Policy policy;
    int quantum;
    int currProcessID;

    Queue<Integer> processQueue;
 //   PriorityQueue<SPNinfo>   priorityQueue;
 //   PriorityQueue<HRRNinfo> priorityHRRNQueue;

    PriorityQueue<Integer> priorityQueue;

    ArrayList<Integer> processIDs;

    Semaphore processRunning;
//    private ProcessQueue priorityQueue;


    /**
     * DO NOT CHANGE DEFINITION OF OPERATION
     */
    public Scheduler(ProcessExecution processExecution) {
        this.processExecution = processExecution;

        /**
         * Add general initialization code here (if needed)
         */
    }

    /**
     * DO NOT CHANGE DEFINITION OF OPERATION
     */
    public void startScheduling(Policy policy, int quantum) {

        this.policy = policy;
        this.quantum = quantum;

        processQueue = new LinkedList<>();

        this.processIDs = new ArrayList<>();

        /**
         * Add general initialization code here (if needed)
         */

        switch(policy) {
            case FCFS:  //First-come-first-served
                System.out.println("Starting new scheduling task: First-come-first-served");
                /**
                 * Add your policy specific initialization code here (if needed)
                 */
                break;
            case RR:    //Round robin
                System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
                this.processRunning = new Semaphore(1,true);
                break;
            case SPN:   //Shortest process next
                System.out.println("Starting new scheduling task: Shortest process next");
                this.priorityQueue = new PriorityQueue(new ProcessComparator(Policy.SPN, processExecution));
                break;
            case SRT:   //Shortest remaining time
                System.out.println("Starting new scheduling task: Shortest remaining time");
                this.priorityQueue = new PriorityQueue(new ProcessComparator(Policy.SPN, processExecution));
                //this.priorityQueue = new PriorityQueue<>();
                break;
            case HRRN:  //Highest response ratio next
                System.out.println("Starting new scheduling task: Highest response ratio next");
                //this.PriorityQueue = new PriorityQueue<>();
                break;
            case FB:    //Feedback
                System.out.println("Starting new scheduling task: Feedback, quantum = " + quantum);
                /**
                 * Add your policy specific initialization code here (if needed)
                 */
                break;
        }

        /**
         * Add general scheduling or initialization code here (if needed)
         */

    }

    /**
     * DO NOT CHANGE DEFINITION OF OPERATION
     */
    public void processAdded(int processID) {

        switch(policy) {
            case FCFS:  //First-come-first-served
                processQueue.add(processID);
                if(processQueue.size() == 1) {
                    processExecution.switchToProcess(processID);
                }
                break;
            case RR:    //Round robin
                try {
                    processRunning.acquire();
                    processIDs.add(processID);
                    processRunning.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case SPN:   //Shortest process next
                //SPNinfo processInfo = new SPNinfo(processID, processExecution.getProcessInfo(processID).totalServiceTime);
                if(priorityQueue.isEmpty()){
                    priorityQueue.add(processID);
                    processExecution.switchToProcess(processID);
                } else {
                    priorityQueue.add(processID);
                }
                break;
            case SRT:   //Shortest remaining time
//                ArrayList<Integer> L = new ArrayList<Integer>();
//
//
//                if (L.size() == 0) {
//                    L.add(processID);
//                    processExecution.switchToProcess(processID);
//                } else {
//                    for(int i = 0; i < L.size(); i++) {
//                        if (getRemainingTime(L.get(i)) < getRemainingTime(processID)) {
//                            processExecution.switchToProcess(L.get(i));
//                        }
//                    }
//
//                }

                priorityQueue.add(processID);
                processExecution.switchToProcess(priorityQueue.peek());

//                int currProcessID = 0;
//                if(priorityQueue.isEmpty()){
//                    priorityQueue.add(processID);
//                    currProcessID = processID;
//                    processExecution.switchToProcess(processID);
//                } else {
//                    System.out.println("priorityQueue: " + priorityQueue.size());
//                    priorityQueue.add(processID);
//                    processExecution.switchToProcess(processID);
                    //if (priorityQueue.peek() != currProcessID) {
                    //    processExecution.switchToProcess(processID);
                    // }
//                    if (priorityQueue.peek() != currProcessID) {
//                        final int cPrs = currProcessID;
//                        currProcessID = processID;
//                        processExecution.switchToProcess(processID);
//                        priorityQueue.removeIf(p -> p == cPrs);
//                    } else {
//                        priorityQueue.add(processID);
//                    }
//                }

                    //                SPNinfo SRTInfo = new SPNinfo(processID, (processExecution.getProcessInfo(processID).totalServiceTime - processExecution.getProcessInfo(processID).elapsedExecutionTime));

//                if(priorityQueue.isEmpty()){
//                    priorityQueue.add(processID);
//                    currProcessID = processID;
//                    processExecution.switchToProcess(processID);
//
//                } else {
//                    if(((processExecution.getProcessInfo(currProcessID).totalServiceTime -
//                            processExecution.getProcessInfo(currProcessID).elapsedExecutionTime)
//                            > processExecution.getProcessInfo(processID).totalServiceTime)){
//
//                        processExecution.switchToProcess(processID);
//                        priorityQueue.add(processID);
//
//                        int prevID = currProcessID;
//                        currProcessID = processID;
//                        if(prevID >= 0){
//                            if(priorityQueue.removeIf(p -> p == prevID)){
//                                priorityQueue.add(prevID);
//                            }
//                        }
//                    } else{
//                        priorityQueue.add(processID);
//                    }
//                }
                    break;


//                SPNinfo SRTInfo = new SPNinfo(processID, (processExecution.getProcessInfo(processID).totalServiceTime - processExecution.getProcessInfo(processID).elapsedExecutionTime));
//
//                if(priorityQueue.isEmpty()){
//                    priorityQueue.add(SRTInfo);
//                    currProcessID = processID;
//                    processExecution.switchToProcess(processID);
//
//                } else {
//                    if(((processExecution.getProcessInfo(currProcessID).totalServiceTime -
//                            processExecution.getProcessInfo(currProcessID).elapsedExecutionTime)
//                            > processExecution.getProcessInfo(processID).totalServiceTime)){
//
//                        processExecution.switchToProcess(processID);
//                        priorityQueue.add(SRTInfo);
//
//                        int prevID = currProcessID;
//                        currProcessID = processID;
//                        if(prevID >= 0){
//                            if(priorityQueue.removeIf(p -> p.getId() == prevID)){
//                                SPNinfo updateSRT = new SPNinfo(prevID, (processExecution.getProcessInfo(prevID).totalServiceTime - processExecution.getProcessInfo(prevID).elapsedExecutionTime));
//                                priorityQueue.add(updateSRT);
//                            }
//                        }
//                    } else{
//                        priorityQueue.add(SRTInfo);
//                    }
//
//                }
                    // break;
              //  };
            case HRRN:  //Highest response ratio next
//                HRRNinfo HRRNinfo = new HRRNinfo(processID, processExecution.getProcessInfo(processID));
//                processIDs.add(processID);
//                if(priorityHRRNQueue.isEmpty()){
//                    priorityHRRNQueue.add(HRRNinfo);
//                    processExecution.switchToProcess(processID);
//                } else {
//                    priorityHRRNQueue.add(HRRNinfo);
//                }
//                break;
            case FB:    //Feedback

                break;
        }
    }

    /**
     * DO NOT CHANGE DEFINITION OF OPERATION
     */
    public void processFinished(int processID) {

        switch(policy) {
            case FCFS:  //First-come-first-served
                processQueue.poll();
                if(!processQueue.isEmpty()) {
                    processExecution.switchToProcess(processQueue.peek());
                }
                break;
            case RR:    //Round robin
                try {
                    processRunning.acquire();
                    processIDs.remove(processIDs.indexOf(processID));
                    processRunning.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case SPN:   //Shortest process next
                priorityQueue.removeIf(p -> p == processID);
                if(!priorityQueue.isEmpty()){
                    processExecution.switchToProcess(priorityQueue.peek());
                }
                break;
            case SRT:   //Shortest remaining time
                System.out.println("count before: " + priorityQueue.size());
                priorityQueue.removeIf(p -> p == processID);
                System.out.println("count after: " + priorityQueue.size());

                if(!priorityQueue.isEmpty()){
                    processExecution.switchToProcess(priorityQueue.peek());
                }
                break;
//                priorityQueue.removeIf(p -> p == processID);
//                if(!priorityQueue.isEmpty()){
//                    currProcessID = priorityQueue.peek();
//                    processExecution.switchToProcess(currProcessID);
//
//                } else {
//                    currProcessID = -1;
//                }
//                priorityQueue.removeIf(p -> p == processID);
//                if(!priorityQueue.isEmpty()){
//                    processExecution.switchToProcess(priorityQueue.peek());
//                }
//                break;
            case HRRN:  //Highest response ratio next
//                priorityHRRNQueue.removeIf(p -> p.getId() == processID);
//                processIDs.remove(Integer.valueOf(processID));
//
//                priorityHRRNQueue.clear();
//                for(int i = 0; i < processIDs.size(); i++){
//                    Integer updateID = processIDs.get(i);
//                    HRRNinfo HRRNupdate = new HRRNinfo(updateID, processExecution.getProcessInfo(updateID));
//                    priorityHRRNQueue.add(HRRNupdate);
//                }
//
//                if(!priorityHRRNQueue.isEmpty()){
//                    processExecution.switchToProcess(priorityHRRNQueue.peek().getId());
//                }
                break;
            case FB:    //Feedback

                break;
        }
        System.out.println("Finished process " + processID);


    }

    public long getRemainingTime(int id) {
        //                long _srt_a = pe.getProcessInfo(o1).totalServiceTime - pe.getProcessInfo(o1).elapsedExecutionTime;

        return processExecution.getProcessInfo(id).totalServiceTime - processExecution.getProcessInfo(id).elapsedExecutionTime;
    }
}