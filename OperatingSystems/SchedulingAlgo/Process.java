package OperatingSystems.SchedulingAlgo;

public class Process {
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public Status status;

    public int remainingBurstTime;
    public int waitingTime;
    public int startingTime;

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


}
