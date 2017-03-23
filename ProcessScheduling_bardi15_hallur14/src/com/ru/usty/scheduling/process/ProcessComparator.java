package com.ru.usty.scheduling.process;

import com.ru.usty.scheduling.Policy;

import java.util.Comparator;

public class ProcessComparator implements Comparator<Integer> {
    private Policy pol;
    private ProcessExecution pe;

    public ProcessComparator(Policy policy, ProcessExecution processExecution ) {
        pol = policy;
        pe = processExecution;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        ProcessInfo pA = pe.getProcessInfo(o1);
        ProcessInfo pB = pe.getProcessInfo(o2);
        switch (this.pol) {
            case RR:
                //TODO
                return -1;
                //break;
            case SPN:
                //TODO
                long a = pA.totalServiceTime;
                long b = pB.totalServiceTime;
                System.out.println("a is:" + a + ", o1: " + o1 + ", b is: " + b + ", o2: " + o2);
                if (a < b) {
                    return -1;
                } else if (a >= b) {
                    return 1;
                }
                //return 0;
                //break;
            case SRT:
                //TODO
                //TODO
               // ProcessInfo pA = pe.getProcessInfo(o1);
               // ProcessInfo pB = pe.getProcessInfo(o2);
                long _srt_a = pA.totalServiceTime - pA.elapsedExecutionTime;
                long _srt_b = pB.totalServiceTime - pB.elapsedExecutionTime;
//                long _srt_a = pe.getProcessInfo(o1).elapsedExecutionTime;
//                long _srt_ax = pe.getProcessInfo(o1).totalServiceTime;
//                long _srt_b = pe.getProcessInfo(o2).totalServiceTime;
//                System.out.println("a is:" + a + ", o1: " + o1 + ", b is: " + b + ", o2: " + o2);
                if (_srt_a > _srt_b) {
                    return -1;
                } else if (_srt_a < _srt_b) {
                    return 1;
                } else {
                    return 0;
                }
                //return 0;
                //break;
            case HRRN:
                // R = W + S / S
                long a_t = (pA.elapsedWaitingTime + pA.totalServiceTime) / pA.totalServiceTime;
                long b_t = (pB.elapsedWaitingTime + pB.totalServiceTime) / pB.totalServiceTime;
                if (a_t > b_t) {
                    return -1;
                } else if (a_t < b_t) {
                    return 1;
                } else {
                    return 0;
                }
               //break;
        }
        return 0;
    }
}