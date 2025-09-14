package OperatingSystems.SchedulingAlgo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class RoundRobin {
    int quanta = 5;
    ArrayList<Process> processes;
    int processCount = 0;
    ArrayList<GanttChart> ganttChart = new ArrayList<>();

    static class GanttChart {
        int processId;
        int width;

        public GanttChart(int processId, int width) {
            this.processId = processId;
            this.width = width;
        }
    }

    RoundRobin() {
        processes = new ArrayList<>();
    }

    void addProcess(Process p) {
        processes.add(p);
        processCount++;
    }

    void exec() {
        int currentTime = 0;
/*
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt((Process p) -> p.arrivalTime).thenComparingInt(p -> p.pid));
 */
        PriorityQueue<Process> queue = new PriorityQueue<>();

        for (int servedProcess = 0; servedProcess < processCount; ) {
            for (Process process : processes) {
                if (process.arrivalTime >= currentTime && !queue.contains(process)) {
                    queue.add(process);
                }
            }

            Process currentlyExecuting = queue.peek();
            int thisQuanta = (currentlyExecuting.remainingBurstTime > quanta) ? quanta : currentlyExecuting.remainingBurstTime;

            for (Process process : queue) {
                if (process.pid != currentlyExecuting.pid) {
                    process.waitingTime += thisQuanta;
                }
            }

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) {
                currentlyExecuting.startingTime = currentTime;
            }
            if (currentlyExecuting.remainingBurstTime > 0) {
                currentlyExecuting.remainingBurstTime -= thisQuanta;
                currentTime += thisQuanta;
            }
            if (currentlyExecuting.remainingBurstTime > 0) {
                ganttChart.add(new GanttChart(currentlyExecuting.pid, thisQuanta));
                queue.add(queue.poll());
            }
            if (currentlyExecuting.remainingBurstTime == 0) {
                ganttChart.add(new GanttChart(currentlyExecuting.pid, thisQuanta));
                currentlyExecuting.endingTime = currentTime;
                queue.poll();
                servedProcess++;
            }


        }


        printGanttChart();
        debug();
    }

    void debug() {
        System.out.println("\n\nPrinting All Process Details:");
        for (Process process : processes) System.out.println(process);
    }

    void printGanttChart() {
        System.out.println("\n\nPrinting Gantt Chart:");
        // remaining
    }

    public static void main(String[] args) {
        Process p1 = new Process(1, 0, 53);
        Process p2 = new Process(2, 0, 17);
        Process p3 = new Process(3, 0, 68);
        Process p4 = new Process(4, 0, 24);

        RoundRobin roundRobin = new RoundRobin();
        roundRobin.addProcess(p1);
        roundRobin.addProcess(p2);
        roundRobin.addProcess(p3);
        roundRobin.addProcess(p4);
        roundRobin.exec();
    }

}
