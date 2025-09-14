package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class PriorityNonPreemptive {
    ArrayList<Process> processes;
    int processCount = 0;
    ArrayList<String> ganttChart = new ArrayList<>();

    PriorityNonPreemptive() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>(
                Comparator.comparingInt((Process process) -> process.priority)
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
    } // works

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
        Process p1 = new Process(1,3, 0, 10);
        Process p2 = new Process(2,1, 0, 1);
        Process p3 = new Process(3,4, 0, 2);
        Process p4 = new Process(4,5, 0, 1);
        Process p5 = new Process(5,2, 0, 5);

        PriorityNonPreemptive priorityNonPreemptive = new PriorityNonPreemptive();
        priorityNonPreemptive.addProcess(p1);
        priorityNonPreemptive.addProcess(p2);
        priorityNonPreemptive.addProcess(p3);
        priorityNonPreemptive.addProcess(p4);
        priorityNonPreemptive.addProcess(p5);
        priorityNonPreemptive.exec();
    }
}
