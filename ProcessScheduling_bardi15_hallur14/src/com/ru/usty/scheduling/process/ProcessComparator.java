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
        switch (this.pol) {
            case RR:
                //TODO
                return -1;
                //break;
            case SPN:
                //TODO
                long a = pe.getProcessInfo(o1).totalServiceTime;
                long b = pe.getProcessInfo(o2).totalServiceTime;
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
                long _srt_a = pe.getProcessInfo(o1).totalServiceTime - pe.getProcessInfo(o1).elapsedExecutionTime;
                long _srt_b = pe.getProcessInfo(o2).totalServiceTime - pe.getProcessInfo(o2).elapsedExecutionTime;
//                long _srt_a = pe.getProcessInfo(o1).elapsedExecutionTime;
//                long _srt_ax = pe.getProcessInfo(o1).totalServiceTime;
//                long _srt_b = pe.getProcessInfo(o2).totalServiceTime;
//                System.out.println("a is:" + a + ", o1: " + o1 + ", b is: " + b + ", o2: " + o2);
                if (_srt_a > _srt_a) {
                    return -1;
                } else if (_srt_a <= _srt_a) {
                    return 1;
                }
                System.out.println("getProcessInfo(o1).elapsedExecutionTime: " + pe.getProcessInfo(o1).elapsedExecutionTime +
                    "\npe.getProcessInfo(o1).totalServiceTime" + pe.getProcessInfo(o1).totalServiceTime +
                        "\ngetProcessInfo(o2).elapsedExecutionTime: " + pe.getProcessInfo(o2).elapsedExecutionTime +
                        "\npe.getProcessInfo(o2).totalServiceTime" + pe.getProcessInfo(o2).totalServiceTime + "\n++++++");
                //return 0;
                //break;
            case HRRN:
                //TODO
                return 0;
               //break;
        }
        return 0;
    }
}