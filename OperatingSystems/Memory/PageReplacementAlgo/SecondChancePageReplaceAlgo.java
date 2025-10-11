package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class SecondChancePageReplaceAlgo {

    static class Frame {
        public int pageNo;
        public int iddx;
        public boolean isHigh; // R bit

        public Frame(int pageNo, int index, boolean isHigh) {
            this.pageNo = pageNo;
            this.iddx = index;
            this.isHigh = isHigh;
        }

        @Override
        public String toString() {
            return pageNo + (isHigh ? "*" : "");
        }
    }

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Frame> frames;
    private ArrayList<ArrayList<Frame>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();

    SecondChancePageReplaceAlgo() {
    }

    int frameContains(int key) {
        int i = 0;
        for (Frame frame : frames) {
            if (frame.pageNo == key) return i;
            i++;
        }
        return -1;
    }

    void exec() {
        for (int pgSeqIdx = 0; pgSeqIdx < pageSequence.size(); pgSeqIdx++) {
            int pageNo = pageSequence.get(pgSeqIdx);
            int idx = frameContains(pageNo);

            if (idx >= 0) { // hit
                numHits++;
                frames.get(idx).isHigh = true; // set reference bit
                frameHistory.add(new ArrayList<>(frames));
                statusHistory.add('H');
            } else { // fault
                numFaults++;
                if (frames.size() < numFrame) {
                    frames.add(new Frame(pageNo, pgSeqIdx, false));
                } else {

                    while (true) {
                        Frame oldest = frames.get(0);
                        if (oldest.isHigh) {
                            oldest.isHigh = false;
                            frames.remove(0);
                            frames.add(oldest);
                        } else {
                            frames.remove(0);
                            frames.add(new Frame(pageNo, pgSeqIdx, false));
                            break;
                        }
                    }
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
        System.out.println("Second Chance Page Replacement Simulation:");
        System.out.print("Page sequence: ");
        for (int p : pageSequence) System.out.print(p + " ");
        System.out.println("\n");

        for (int f = 0; f < numFrame; f++) {
            System.out.printf("F%d:\t", f + 1);
            for (ArrayList<Frame> snapshot : frameHistory) {
                if (f < snapshot.size()) System.out.printf("%d\t", snapshot.get(f).pageNo);
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
        SecondChancePageReplaceAlgo algo = new SecondChancePageReplaceAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/SecondChancePageReplaceAlgo.txt");
        algo.exec();
        algo.printResult();
    }
}
