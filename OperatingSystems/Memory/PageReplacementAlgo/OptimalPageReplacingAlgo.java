package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class OptimalPageReplacingAlgo {

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Integer> frames;
    private ArrayList<ArrayList<Integer>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();

    OptimalPageReplacingAlgo() {
    }

    void exec() {
        for (int pgSeqIdx = 0; pgSeqIdx < pageSequence.size(); pgSeqIdx++) {
            int pageNo = pageSequence.get(pgSeqIdx);

            if (frames.contains(pageNo)) { // hit
                numHits++;
                frameHistory.add(new ArrayList<>(frames));
                statusHistory.add('H');
            } else { // page fault
                numFaults++;
                if (frames.size() < numFrame) {
                    frames.add(pageNo);
                } else { // replacement
                    int[][] pgIdxPair = new int[numFrame][2];

                    for (int i = 0; i < numFrame; i++) {
                        pgIdxPair[i][0] = frames.get(i);
                        pgIdxPair[i][1] = -1;
                    }

                    // look ahead for next use
                    for (int i = pgSeqIdx + 1; i < pageSequence.size(); i++) {
                        int futurePg = pageSequence.get(i);
                        if (frames.contains(futurePg)) {
                            int idx = frames.indexOf(futurePg);
                            if (pgIdxPair[idx][1] == -1) {
                                pgIdxPair[idx][1] = i;
                            }
                        }
                    }

                    int furthest = -1;
                    int indexToReplace = -1;
                    for (int i = 0; i < numFrame; i++) {
                        if (pgIdxPair[i][1] == -1) {
                            indexToReplace = i;
                            break;
                        }
                        if (pgIdxPair[i][1] > furthest) {
                            furthest = pgIdxPair[i][1];
                            indexToReplace = i;
                        }
                    }

                    frames.set(indexToReplace, pageNo);
                }

                frameHistory.add(new ArrayList<>(frames));
                statusHistory.add('F');
            }
        }
    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        numFrame = scanner.nextInt();
        frames = new ArrayList<>(numFrame);
        while (scanner.hasNextInt()) {
            pageSequence.add(scanner.nextInt());
        }
        scanner.close();
    }

    void printResult() {
        System.out.println("Optimal Page Replacement Simulation:");
        System.out.print("Page sequence: ");
        for (int p : pageSequence) System.out.print(p + " ");
        System.out.println("\n");

        // print frame table
        for (int f = 0; f < numFrame; f++) {
            System.out.printf("F%d:\t", f + 1);
            for (ArrayList<Integer> snapshot : frameHistory) {
                if (f < snapshot.size()) System.out.printf("%d\t", snapshot.get(f));
                else System.out.print(" \t");
            }
            System.out.println();
        }

        System.out.print("S:\t");
        for (char c : statusHistory) System.out.printf("%c\t", c);
        System.out.println();

        double hitRatio = (double) numHits / pageSequence.size();
        System.out.printf("\nHits: %d, Faults: %d\nHit Ratio = %.2f%%\n",
                numHits, numFaults, hitRatio * 100);
    }

    public static void main(String[] args) throws FileNotFoundException {
        OptimalPageReplacingAlgo algo = new OptimalPageReplacingAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/OptimalPageReplacingAlgo.txt");
        algo.exec();
        algo.printResult();
    }
}
