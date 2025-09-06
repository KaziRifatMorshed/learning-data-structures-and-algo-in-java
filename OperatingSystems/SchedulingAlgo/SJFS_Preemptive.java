package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


class SJFS_Preemptive { // Shortest-Job-First Scheduling
    // jar kom, se age jabe, but per moment e
    ArrayList<Process> processes;
    int processCount = 0;
    ArrayList<String> ganttChart = new ArrayList<>();

    SJFS_Preemptive() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        p.status = Status.Ready;
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0; // nano-sec
        PriorityQueue<Process> queue = new PriorityQueue<>(
                Comparator.comparingInt((Process process) -> process.remainingBurstTime)
                        .thenComparingInt(process -> process.pid)); // THIS WORKS

        for (int servedProcess = 0; servedProcess < processCount; currentTime++) {
            for (Process p : processes) {
                if (currentTime == p.arrivalTime && !queue.contains(p)) queue.add(p);
            }

            Process currentlyExecuting = queue.peek();
            currentlyExecuting.status = Status.Running;
            ganttChart.add("p" + currentlyExecuting.pid);

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) {
                currentlyExecuting.startingTime = currentTime;
            }

            for (Process p : queue) {
                if (p.pid != currentlyExecuting.pid) {
                    p.status = Status.Waiting;
                    p.waitingTime++;
                }
            }

            if (currentlyExecuting.remainingBurstTime > 0) {
                currentlyExecuting.remainingBurstTime--;
                currentlyExecuting.turnaroundTime++;
                currentlyExecuting.status = Status.Waiting;
            }
            if (currentlyExecuting.remainingBurstTime == 0) {
                currentlyExecuting.endingTime = currentTime;

                currentlyExecuting.status = Status.Terminated;
                queue.poll();
                servedProcess++;
            }
        }

        System.out.println("Total time taken = " + currentTime);
        System.out.println("Waiting time for all processes:");
        double avgWaitingTime = 0;
        for (Process process : processes) {
            System.out.println("pid = " + process.pid + ", waiting time = " + process.waitingTime);
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
        Process p1 = new Process(1, 0, 8, Status.New);
        Process p2 = new Process(2, 1, 4, Status.New);
        Process p3 = new Process(3, 2, 9, Status.New);
        Process p4 = new Process(4, 3, 5, Status.New);

        SJFS_Preemptive sjfs_p = new SJFS_Preemptive();
        sjfs_p.addProcess(p1);
        sjfs_p.addProcess(p2);
        sjfs_p.addProcess(p3);
        sjfs_p.addProcess(p4);
        sjfs_p.exec();
    }
} // OK

