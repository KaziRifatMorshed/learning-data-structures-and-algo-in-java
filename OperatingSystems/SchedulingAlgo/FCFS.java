package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.PriorityQueue;

class FCFS { // First-Come, First-Served Scheduling
    // age asle age pabe, pore asle pore pabe
    ArrayList<Process> processes;
    int currentlyExecutingProcessId = 0;

    FCFS() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
    }

    void exec() {
        int currentTime = 0; // nano-sec
        PriorityQueue<Process> queue = new PriorityQueue<>();

        for (; !processes.isEmpty(); currentTime++) {
            for (Process p : processes) {
                if (currentTime == p.arrivalTime) queue.add(p);
            }
        }
        
        System.out.println("Total time taken = " + currentTime);
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
