package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class LRU_PageReplacementAlgo {

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Integer> frames;
    private ArrayList<ArrayList<Integer>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();

    LRU_PageReplacementAlgo() {
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

                    // look back for next use
                    for (int i = pgSeqIdx - 1; i <= 0; i--) {
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

    void printBarHelper() {
        System.out.printf("-----");
        for (int i = 0; i < pageSequence.size(); i++) System.out.printf("----");
        System.out.println();
    }

    void printResult() {
        System.out.println("LRU Page Replacement Simulation:");
        System.out.print("Page sequence: ");
        for (int p : pageSequence) System.out.print(p + " ");
        System.out.println("\n");

        printBarHelper();
        System.out.printf("   \t|");
        for (int inputPage : pageSequence) {
            System.out.printf("%2d\t|", inputPage);
        }
        System.out.println();

        printBarHelper();
        // print frame table
        for (int f = 0; f < numFrame; f++) {
            System.out.printf("F%d:\t|", f + 1);
            for (ArrayList<Integer> snapshot : frameHistory) {
                if (f < snapshot.size()) System.out.printf("%2d\t|", snapshot.get(f));
                else System.out.print(" \t|");
            }
            System.out.println();
        }

        printBarHelper();
        System.out.print("S:\t|");
        for (char c : statusHistory) System.out.printf("%2c\t|", c);
        System.out.println();

        double hitRatio = (double) numHits / pageSequence.size();
        System.out.printf("\nHits: %d, Faults: %d\nHit Ratio = %.2f%%\n",
                numHits, numFaults, hitRatio * 100);
    }

    public static void main(String[] args) throws FileNotFoundException {
        LRU_PageReplacementAlgo algo = new LRU_PageReplacementAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/LRU_PageReplacementAlgo.txt");
        algo.exec();
        algo.printResult();
    }
}

