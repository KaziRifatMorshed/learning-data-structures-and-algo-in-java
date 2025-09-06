package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class FCFS { // First-Come, First-Served Scheduling
    // age asle age pabe, pore asle pore pabe
    ArrayList<Process> processes;
    int processCount = 0;

    FCFS() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0; // nano-sec
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(process -> process.arrivalTime));

        for (int servedProcess = 0; servedProcess < processCount; currentTime++) {
            for (Process p : processes) {
                if (currentTime >= p.arrivalTime) queue.add(p);
            }

            Process currentlyExecuting = queue.peek();

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) {
                currentlyExecuting.startingTime = currentTime;
            }
            if (currentlyExecuting.remainingBurstTime > 0) {
                currentlyExecuting.remainingBurstTime--;
            } else {
                currentlyExecuting.endingTime = currentTime;
                queue.poll();
                servedProcess++;
            }

        }

        System.out.println("Total time taken = " + currentTime);
        System.out.println("Waiting time for all processes:");
        double avgWaitingTime = 0;
        for (Process process : processes) {
            System.out.println("pid = " + process.pid + ", waiting time (exec started at) = " + process.startingTime);
            avgWaitingTime += (double) (process.startingTime - process.arrivalTime);
        }
        avgWaitingTime /= (double) processes.size();
        System.out.println("Avg Waiting Time = " + avgWaitingTime);

        debug();
    }

    void debug() {
        System.out.println("\nPrinting all process details:");
        for (Process p : processes) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Process p1 = new Process(1, 0, 24);
        Process p2 = new Process(2, 0, 3);
        Process p3 = new Process(3, 0, 3);

        FCFS fcfs = new FCFS();
        fcfs.addProcess(p1);
        fcfs.addProcess(p2);
        fcfs.addProcess(p3);

        fcfs.exec();
    }
}
