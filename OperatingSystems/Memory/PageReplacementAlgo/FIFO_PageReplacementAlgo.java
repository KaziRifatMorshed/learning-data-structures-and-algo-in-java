package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class FIFO_PageReplacementAlgo {
    
    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Integer> frames;
    private ArrayList<Integer> framesQueue;
    private ArrayList<ArrayList<Integer>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();

    FIFO_PageReplacementAlgo() {
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
                    framesQueue.add(pgSeqIdx);
                } else { // replacement
                    int oldest = Integer.MAX_VALUE;
                    int indexToReplace = -1;
                    for (int i = 0; i < numFrame; i++) {
                        if (framesQueue.get(i) < oldest) {
                            oldest = framesQueue.get(i);
                            indexToReplace = i;
                        }
                    }
                    frames.set(indexToReplace, pageNo);
                    framesQueue.set(indexToReplace, pgSeqIdx);
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
        framesQueue = new ArrayList<>(numFrame);
        while (scanner.hasNextInt()) {
            pageSequence.add(scanner.nextInt());
        }
        scanner.close();
    }

    void printResult() {
        System.out.println("FIFO Page Replacement Simulation:");
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
        FIFO_PageReplacementAlgo algo = new FIFO_PageReplacementAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/FIFO_PageReplacementAlgo.txt");
        algo.exec();
        algo.printResult();
    }
}
