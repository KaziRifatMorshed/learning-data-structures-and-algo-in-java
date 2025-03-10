package Algorithms.Greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JobSequencingWithDeadline {
    static ArrayList<Job> jobs = new ArrayList<>();
    static int workWithin = 0;
    static ArrayList<Job> myJobSchedule;

    static class Job {
        int job_id, payment, deadlineDays;

        public Job(int job_id, int payment, int deadlineDays) {
            this.job_id = job_id;
            this.payment = payment;
            this.deadlineDays = deadlineDays;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "job_id=" + job_id +
                    ", payment=" + payment +
                    ", deadlineDays=" + deadlineDays +
                    '}';
        }
    }

    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner fs = new Scanner(new File(filePath));
        int job_id = 1;
        while (fs.hasNext()) {
            String s = fs.nextLine();
            Scanner scanner = new Scanner(s);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            workWithin = Math.max(b, workWithin);
            if (a == b && b == 0) return;
            jobs.add(new Job(job_id, a, b));
            job_id++;
        }
    } // WORKS

//    static boolean isFull() {
//        return workWithin == myJobSchedule.size();
//    }

    static int jobSequencing() {
        int totalProfit = 0;
        jobs.sort(Comparator.comparingInt(job -> job.payment));
//        jobs.reversed(); // --------------------- THIS DOES NOT WORK
        Collections.reverse(jobs);

        System.out.println(jobs);

        myJobSchedule = new ArrayList<>(Collections.nCopies(workWithin, null));

//        for (int i = 0; !isFull() && i < workWithin; i++) {
        for (int i = 0; i < workWithin; i++) {
            int targetIndex = jobs.get(i).deadlineDays - 1;
            while (targetIndex >= 0 && targetIndex < workWithin) {
                if (myJobSchedule.get(targetIndex) == null) {
                    myJobSchedule.set(targetIndex, jobs.get(i));
                    System.out.println(jobs.get(i).payment + " " + jobs.get(i).deadlineDays);
                    totalProfit += jobs.get(i).payment;
                    break;
                } else targetIndex--;
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) throws FileNotFoundException {
        takeInputFromFile("./Algorithms/Greedy/JobSeq.txt");
        int totalProfit = jobSequencing();
        System.out.println("totalProfit = " + totalProfit);
    }
} // DONE
