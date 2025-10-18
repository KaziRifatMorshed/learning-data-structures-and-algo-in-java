package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
                } else { // replacement (LRU)
                    // lastUse[i] will store the index of the most recent use (before current)
                    // of the page stored in frames.get(i). If not found, it stays -1.
                    int[] lastUse = new int[numFrame];
                    Arrays.fill(lastUse, -1);

                    // look back for last use (scan backwards from current position - 1)
                    for (int i = pgSeqIdx - 1; i >= 0; i--) {
                        int prevPg = pageSequence.get(i);
                        int idx = frames.indexOf(prevPg);
                        if (idx != -1 && lastUse[idx] == -1) {
                            lastUse[idx] = i;
                        }
                        // Optimization: if all frames have lastUse filled, break early
                        boolean allFound = true;
                        for (int j = 0; j < numFrame; j++) {
                            if (lastUse[j] == -1) {
                                allFound = false;
                                break;
                            }
                        }
                        if (allFound) break;
                    }

                    // Choose replacement index:
                    // - If any lastUse == -1 (i.e., not found in past), that page is the least recently used for our purposes.
                    // - Otherwise choose the page with the smallest lastUse value (farthest in the past).
                    int indexToReplace = 0;
                    boolean foundNeverUsed = false;
                    for (int i = 0; i < numFrame; i++) {
                        if (lastUse[i] == -1) {
                            indexToReplace = i;
                            foundNeverUsed = true;
                            break;
                        }
                    }
                    if (!foundNeverUsed) {
                        int minIdx = Integer.MAX_VALUE;
                        for (int i = 0; i < numFrame; i++) {
                            if (lastUse[i] < minIdx) {
                                minIdx = lastUse[i];
                                indexToReplace = i;
                            }
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
        if (scanner.hasNextInt()) {
            numFrame = scanner.nextInt();
        } else {
            scanner.close();
            throw new FileNotFoundException("Input file does not start with number of frames.");
        }
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