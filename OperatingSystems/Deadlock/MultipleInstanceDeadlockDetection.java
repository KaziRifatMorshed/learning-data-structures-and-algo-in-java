package OperatingSystems.Deadlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        scanner = new Scanner(prefixPath + "currentAllocMtx.txt");
        currentAllocMtx = new int[numProcesses][numResources];
        for (int r = 0; r < numProcesses; r++) {
            for (int c = 0; c < numResources; c++) {
                currentAllocMtx[r][c] = scanner.nextInt();
            }
        }

        scanner = new Scanner(prefixPath + "reqMtx.txt");
        reqMtx = new int[numProcesses][numResources];
        for (int r = 0; r < numProcesses; r++) {
            for (int c = 0; c < numResources; c++) {
                reqMtx[r][c] = scanner.nextInt();
            }
        }

    }

    void exec() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        MultipleInstanceDeadlockDetection algo = new MultipleInstanceDeadlockDetection();
        algo.readFile();
        algo.exec();
    }

}
