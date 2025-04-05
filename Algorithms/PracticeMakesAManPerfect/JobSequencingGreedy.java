package Algorithms.PracticeMakesAManPerfect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class JobSequencingGreedy {
    static ArrayList<job> jobs = new ArrayList<>();
    static int workWithin = 0;
    static ArrayList<job> myJobSchedule;

    static class job {
        int job_id, payment, deadline;

        public job(int job_id, int payment, int deadline) {
            this.job_id = job_id;
            this.payment = payment;
            this.deadline = deadline;
        }
    }

    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        int job_id = 1;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int payment = scanner1.nextInt();
            int deadline = scanner1.nextInt();
            if (payment == deadline && deadline == 0) return;
            workWithin = Math.max(workWithin, deadline);
            jobs.add(new job(job_id, payment, deadline));
            job_id++;
        }
    }

    private static int jobSequencing() {
        int totalProfit = 0;
        jobs.sort(Comparator.comparingInt(job -> job.payment));
        Collections.reverse(jobs);
        System.out.println(jobs);

        myJobSchedule = new ArrayList<>(Collections.nCopies(workWithin, null));

        for (int i = 0; i < workWithin; i++) {
            int targetIndex = jobs.get(i).deadline - 1;
            while (targetIndex >= 0 && targetIndex < workWithin) {
                if (myJobSchedule.get(targetIndex) == null) { // insert here
                    myJobSchedule.set(targetIndex, jobs.get(i));
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

} // WORKING
