package OperatingSystems.Deadlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class MultipleInstanceDeadlockDetection {

    int numProcesses; // row
    int numResources; // col
    final String prefixPath = "OperatingSystems/Deadlock/";
    int[] existenceArr;
    int[] availableArr;
    int[][] currentAllocMtx;
    int[][] reqMtx;

    void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(prefixPath + "parameters.txt"));
        numProcesses = scanner.nextInt();
        numResources = scanner.nextInt();

        scanner = new Scanner(new File(prefixPath + "existence.txt"));
        existenceArr = new int[numResources];
        for (int i = 0; i < numResources; i++) {
            existenceArr[i] = scanner.nextInt();
        }

        scanner = new Scanner(new File(prefixPath + "available.txt"));
        availableArr = new int[numResources];
        for (int i = 0; i < numResources; i++) {
            availableArr[i] = scanner.nextInt();
        }

        scanner = new Scanner(new File(prefixPath + "currentAllocMtx.txt"));
        currentAllocMtx = new int[numProcesses][numResources];
        for (int r = 0; r < numProcesses; r++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim whitespace
                Scanner scanner1 = new Scanner(line);
                for (int c = 0; c < numResources; c++) {
                    if (scanner1.hasNextInt()) {
                        currentAllocMtx[r][c] = scanner1.nextInt();
                    }
                }
            }
        }

        scanner = new Scanner(new File(prefixPath + "reqMtx.txt"));
        reqMtx = new int[numProcesses][numResources];
        for (int r = 0; r < numProcesses; r++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim whitespace
                Scanner scanner1 = new Scanner(line);
                for (int c = 0; c < numResources; c++) {
                    if (scanner1.hasNextInt()) {
                        reqMtx[r][c] = scanner1.nextInt();
                    }
                }
            }
        }

    }

    void exec() {
        // Standard multiple-instance deadlock detection (like the Banker's "detection" algorithm).
        // Work starts as the currently available vector.
        if (availableArr == null || currentAllocMtx == null || reqMtx == null) {
            System.out.println("Data not initialized");
            return;
        }

        int[] work = Arrays.copyOf(availableArr, numResources);

        boolean[] finish = new boolean[numProcesses];
        Arrays.fill(finish, false);

        // Try to find a sequence of processes that can finish
        boolean progress = true;
        while (progress) {
            progress = false;
            for (int p = 0; p < numProcesses; p++) {
                if (finish[p]) continue;

                boolean canSatisfy = true;
                for (int r = 0; r < numResources; r++) {
                    if (reqMtx[p][r] > work[r]) {
                        canSatisfy = false;
                        break;
                    }
                }

                if (canSatisfy) {
                    // this process can finish: release its current allocation to work
                    for (int r = 0; r < numResources; r++) {
                        work[r] += currentAllocMtx[p][r];
                    }
                    finish[p] = true;
                    progress = true;
                }
            }
        }

        // After the loop, processes with finish[p] == false are considered unable to finish.
        List<Integer> deadlocked = new ArrayList<>();
        for (int p = 0; p < numProcesses; p++) {
            if (!finish[p]) {
                deadlocked.add(p);
            }
        }

        if (deadlocked.isEmpty()) {
            System.out.println("No deadlock detected");
        } else {
            System.out.println("Deadlock detected for process:");
            for (int p : deadlocked) {
                System.out.print("P" + p);
                // optionally show the allocation and request for clarity
                System.out.print(" (Alloc: [");
                for (int r = 0; r < numResources; r++) {
                    System.out.print(currentAllocMtx[p][r]);
                    if (r < numResources - 1) System.out.print(", ");
                }
                System.out.print("], Req: [");
                for (int r = 0; r < numResources; r++) {
                    System.out.print(reqMtx[p][r]);
                    if (r < numResources - 1) System.out.print(", ");
                }
                System.out.println("])");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        MultipleInstanceDeadlockDetection algo = new MultipleInstanceDeadlockDetection();
        algo.readFile();
        algo.exec();
    }

}