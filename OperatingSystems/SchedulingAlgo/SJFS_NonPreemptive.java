package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class SJFS_NonPreemptive { // Shortest-Job-First Scheduling
    // jar kom, se age jabe
    ArrayList<Process> processes;
    int processCount = 0;
    ArrayList<String> ganttChart = new ArrayList<>();

    SJFS_NonPreemptive() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0; // nano-sec
        PriorityQueue<Process> queue = new PriorityQueue<>(
                Comparator.comparingInt((Process process) -> process.burstTime)
                        .thenComparingInt(process -> process.pid));

        for (int servedProcess = 0; servedProcess < processCount; currentTime++) {
            for (Process p : processes) {
                if (currentTime == p.arrivalTime && !queue.contains(p)) queue.add(p);
            }

            Process currentlyExecuting = queue.peek();
            ganttChart.add("p" + currentlyExecuting.pid);

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) {
                currentlyExecuting.startingTime = currentTime;
            }
            if (currentlyExecuting.remainingBurstTime > 0) {
                currentlyExecuting.remainingBurstTime--;
            }
            if (currentlyExecuting.remainingBurstTime == 0) {
                currentlyExecuting.endingTime = currentTime;
                queue.poll();
                servedProcess++;
            }

        }

        System.out.println("Total time taken = " + currentTime);
        System.out.println("Waiting time for all processes:");
        double avgWaitingTime = 0;
        for (Process process : processes) {
            System.out.println("pid = " + process.pid + ", waiting time = " + process.startingTime);
            process.waitingTime += (process.startingTime - process.arrivalTime);
            avgWaitingTime += (double) process.waitingTime;
        }
        avgWaitingTime /= (double) processes.size();
        System.out.println("Avg Waiting Time = " + avgWaitingTime);
        System.out.println("Throughput = " + (double) (currentTime / processCount));

        printGanttChart();

        debug();
    }

    void printGanttChart() {
        System.out.println("\nPrinting Gantt Chart:");
        for (String s : ganttChart) {
            System.out.print(s + "|");
        }
    }

    void debug() {
        System.out.println("\n\nPrinting all process details:");
        for (Process p : processes) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Process p1 = new Process(1, 0, 6);
        Process p2 = new Process(2, 0, 8);
        Process p3 = new Process(3, 0, 7);
        Process p4 = new Process(4, 0, 3);

        SJFS_NonPreemptive sjfs_np = new SJFS_NonPreemptive();
        sjfs_np.addProcess(p1);
        sjfs_np.addProcess(p2);
        sjfs_np.addProcess(p3);
        sjfs_np.addProcess(p4);

        sjfs_np.exec();
    }
} // i guess, working
