package com.ru.usty.scheduling;
import com.ru.usty.scheduling.methods.*;
import com.ru.usty.scheduling.process.*;

import java.util.*;
import java.util.concurrent.Semaphore;

import com.ru.usty.scheduling.process.ProcessExecution;

public class Scheduler {

    ProcessExecution processExecution;
    Policy policy;
    int quantum;

    private fcfs _f;
    private spn _s;
    private srt _sr;
    private hrrn _h;
    private rr _r;
    private fb _fb;


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
        /**
         * Add general initialization code here (if needed)
         */

        switch(policy) {
            case FCFS:  //First-come-first-served
                System.out.println("Starting new scheduling task: First-come-first-served");
                this._f = new fcfs(processExecution);
                break;
            case RR:    //Round robin
                System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
                //processQueue = new LinkedList<Integer>();
                this._r = new rr(processExecution, quantum);
                break;
            case SPN:   //Shortest process next
                System.out.println("Starting new scheduling task: Shortest process next");
                this._s = new spn(processExecution);
                break;
            case SRT:   //Shortest remaining time
                System.out.println("Starting new scheduling task: Shortest remaining time");
                this._sr = new srt(processExecution);
                break;
            case HRRN:  //Highest response ratio next
                System.out.println("Starting new scheduling task: Highest response ratio next");
                this._h = new hrrn(processExecution);
                break;
            case FB:    //Feedback
                System.out.println("Starting new scheduling task: Feedback, quantum = " + quantum);
                this._fb = new fb(processExecution, quantum);
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
                _f.add(processID);
                break;
            case RR:    //Round robin
                _r.add(processID);
                break;
            case SPN:   //Shortest process next
                this._s.add(processID);
                break;
            case SRT:   //Shortest remaining time
                this._sr.add(processID);
                break;
            case HRRN: //Highest response ratio next
                this._h.add(processID);
                break;
            case FB:    //Feedback
                this._fb.add(processID);
                break;
        }
    }

    /**
     * DO NOT CHANGE DEFINITION OF OPERATION
     */
    public void processFinished(int processID) {

        switch(policy) {
            case FCFS:  //First-come-first-served
                _f.finish(processID);
                break;
            case RR:    //Round robin
                _r.finish(processID);
                break;
            case SPN:   //Shortest process next
                this._s.finish(processID);
                break;
            case SRT:   //Shortest remaining time
                this._sr.finish(processID);
                break;
            case HRRN:  //Highest response ratio next
                this._h.finish(processID);
                break;
            case FB:    //Feedback
                this._fb.finish(processID);
                break;
        }
        System.out.println("Finished process " + processID);
    }

}