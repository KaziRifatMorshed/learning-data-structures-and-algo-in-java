package OperatingSystems.SchedulingAlgo;

import java.util.*;

class RoundRobin {
    int quanta = 20;
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
        int servedProcess = 0;

        Queue<Process> queue = new LinkedList<>();

        while (servedProcess < processCount) {

            AddNewProcess:
            for (Process process : processes) { // add new process
                if (process.arrivalTime <= currentTime && process.remainingBurstTime > 0 && !queue.contains(process)) {
                    queue.add(process);
                }
            }

            Process currentlyExecuting = queue.poll();
            int thisQuanta = Math.min(quanta, currentlyExecuting.remainingBurstTime);

            AdjustWaitingTimeForAllProcesses:
            for (Process process : queue) {
                if (process.pid != currentlyExecuting.pid && process.remainingBurstTime > 0) {
                    process.waitingTime += thisQuanta;
                }
            }

            if (currentlyExecuting.remainingBurstTime == currentlyExecuting.burstTime) { // init process
                currentlyExecuting.startingTime = currentTime;
            }
            if (currentlyExecuting.remainingBurstTime > 0) { // execute and calculate
                currentlyExecuting.remainingBurstTime -= thisQuanta;
                currentTime += thisQuanta;

                ganttChart.add(new GanttChart(currentlyExecuting.pid, thisQuanta));

                queue.add(currentlyExecuting); // process not finished
            } else { // process finished
                currentlyExecuting.endingTime = currentTime;
                servedProcess++;
            }
        }

        printGanttChart();
        debug();
    } // ONE TEST CASE PASSED

    void debug() {
        System.out.println("\n\nPrinting All Process Details:");
        for (Process process : processes) System.out.println(process);
    }

    void printGanttChart() { // simple
        System.out.println("\n\nPrinting Gantt Chart:");
        for (GanttChart gc : ganttChart) {
            System.out.print("|P" + gc.processId + " ");
        }
        System.out.println("|");

        int t = 0;
        System.out.print(t + "\t");
        for (GanttChart gc : ganttChart) {
            t += gc.width;
            System.out.print(t + "\t");
        }
        System.out.println();
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