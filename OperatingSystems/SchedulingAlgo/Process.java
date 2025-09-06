package OperatingSystems.SchedulingAlgo;

public class Process {
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public Status status;

    public int remainingBurstTime;
    public int waitingTime;
    public int startingTime;
    public int endingTime;

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
                ", endingTime=" + (endingTime+1) +
                '}';
    }
}
