package OperatingSystems.SchedulingAlgo;

public class Process {
    public int pid;
    public int arrivalTime; // submission timestamp
    public int burstTime;
    public Status status;
    public int priority;

    public int remainingBurstTime;
    public int waitingTime;
    public int startingTime; // when remainingBurstTime == BurstTime
    public int endingTime; // when remainingBurstTime == 0
    private int turnaroundTime = 0; // submission to completion

    public Process(int pid, int arrivalTime, int burstTime, Status status) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.status = status;
        this.remainingBurstTime = burstTime;
    }

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
    }

    public Process(int pid, int priority, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingBurstTime = burstTime;
    }

    int calculateTurnaroundTime() {
        this.turnaroundTime = endingTime - arrivalTime;
        return this.turnaroundTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", status=" + status +
                ", remainingBurstTime=" + remainingBurstTime +
                ", waitingTime=" + waitingTime +
                ", startingTime=" + startingTime +
                ", endingTime=" + (endingTime + 1) +
                ", turnaroundTime=" + calculateTurnaroundTime() +
                '}';
    }

    public static void main(String[] args) {
        Process p1 = new Process(1, 0, 24);
        Process p2 = new Process(2, 0, 3);
        Process p3 = new Process(3, 0, 3);

    }
}
