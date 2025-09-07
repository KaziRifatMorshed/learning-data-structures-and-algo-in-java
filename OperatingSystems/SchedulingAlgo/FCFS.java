package OperatingSystems.SchedulingAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class FCFS { // First-Come, First-Served Scheduling
    // age asle age pabe, pore asle pore pabe
    ArrayList<Process> processes;
    int processCount = 0;
    ArrayList<String> ganttChart = new ArrayList<>();

    FCFS() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0; // nano-sec
        PriorityQueue<Process> queue = new PriorityQueue<>(
                Comparator.comparingInt((Process process) -> process.arrivalTime)
                        .thenComparingInt(process -> process.pid)); // readyQueue

        for (int servedProcess = 0; servedProcess < processCount; currentTime++) {
            for (Process p : processes) { // readyQueue te process include koro
                if (currentTime == p.arrivalTime && !queue.contains(p)) queue.add(p);
            }

            // this time moment e ekta process ke execute korbo
            Process currentlyExecuting = queue.peek();
            ganttChart.add("p" + currentlyExecuting.pid);

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) {
                // jodi ekebare new process hoy, age execute hoy nai emon
                currentlyExecuting.startingTime = currentTime;
                currentlyExecuting.status = Status.Running;
            }
            if (currentlyExecuting.remainingBurstTime > 0) {
                currentlyExecuting.remainingBurstTime--;
                currentlyExecuting.status = Status.Running;
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
        Process p1 = new Process(1, 0, 24);
        Process p2 = new Process(2, 0, 3);
        Process p3 = new Process(3, 0, 3);

        FCFS fcfs = new FCFS();
        fcfs.addProcess(p1);
        fcfs.addProcess(p2);
        fcfs.addProcess(p3);

        fcfs.exec();
    }
} // i think, OK
