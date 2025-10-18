package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class NRU_PageReplacementAlgo {

    static class Frame {
        int pageNo;
        int refBit;
        int modBit;

        Frame(int pageNo, int refBit, int modBit) {
            this.pageNo = pageNo;
            this.refBit = refBit;
            this.modBit = modBit;
        }
    }

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Frame> frames;
    private int[][] pageSequenceBits;
    private ArrayList<int[][]> pageSequenceBitInput;
    private ArrayList<ArrayList<Integer>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();
    private Random random = new Random();

    NRU_PageReplacementAlgo() {
    }

    int frameContains(int pageNo) {
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).pageNo == pageNo) return i;
        }
        return -1;
    }

    void clearReferenceBits() {
        for (Frame f : frames) {
            f.refBit = 0;
        }
    }

    void exec() {
        int clearInterval = 4;

        for (int pgSeqIdx = 0; pgSeqIdx < pageSequence.size(); pgSeqIdx++) {
            int pageNo = pageSequence.get(pgSeqIdx);

            if (pgSeqIdx % clearInterval == 0 && pgSeqIdx != 0) {
                clearReferenceBits();
            }

            int foundIdx = frameContains(pageNo);

            if (foundIdx != -1) { // Hit
                numHits++;
                frames.get(foundIdx).refBit = 1;
                // Randomly simulate write (M-bit)
                frames.get(foundIdx).modBit = random.nextBoolean() ? 1 : 0;
                frameHistory.add(snapshot());
                statusHistory.add('H');
            } else { // Page fault
                numFaults++;
                if (frames.size() < numFrame) {
                    frames.add(new Frame(pageNo, 1, random.nextBoolean() ? 1 : 0));
                } else {
                    // Replacement using NRU logic
                    int replaceIdx = selectNRUFrame(pgSeqIdx);
                    frames.set(replaceIdx, new Frame(pageNo, 1, random.nextBoolean() ? 1 : 0));
                }

                frameHistory.add(snapshot());
                statusHistory.add('F');
            }

        }
    }

    // Select victim frame based on NRU class
    int selectNRUFrame(int pgSeqIdx) {
        // Classes: 0=(0,0), 1=(0,1), 2=(1,0), 3=(1,1)
        ArrayList<Integer>[] classes = new ArrayList[4];
        for (int i = 0; i < 4; i++) classes[i] = new ArrayList<>();

        for (int i = 0; i < frames.size(); i++) {
            Frame f = frames.get(i);

            int cls = (pageSequenceBitInput.get(pgSeqIdx)[i][0] << 1) | (pageSequenceBitInput.get(pgSeqIdx)[i][0]);
            classes[cls].add(i);
        }

        // Pick lowest
        for (int i = 0; i < 4; i++) {
            if (!classes[i].isEmpty()) {
                // Choose randomly within class to simulate OS behavior
                return classes[i].get(random.nextInt(classes[i].size()));
            }
        }
        return 0;
    }

    // Snapshot frame list for printing
    ArrayList<Integer> snapshot() {
        ArrayList<Integer> snap = new ArrayList<>();
        for (Frame f : frames) snap.add(f.pageNo);
        return snap;
    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        numFrame = scanner.nextInt();
        frames = new ArrayList<>(numFrame);
        pageSequenceBitInput = new ArrayList<>();
        while (scanner.hasNextInt()) {
            pageSequence.add(scanner.nextInt());
            pageSequenceBits = new int[numFrame][2];
            for (int i = 0; i < numFrame; i++) {
                pageSequenceBits[i][0] = scanner.nextInt();
                pageSequenceBits[i][1] = scanner.nextInt();
            }
            pageSequenceBitInput.add(pageSequenceBits);
        }
        scanner.close();
    }

    void printBarHelper() {
        System.out.printf("-----");
        for (int i = 0; i < pageSequence.size(); i++) System.out.printf("----");
        System.out.println();
    }

    void printResult() {
        System.out.println("NRU (Not Recently Used) Page Replacement Simulation:");
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
        // Print frame table
        for (int f = 0; f < numFrame; f++) {
            System.out.printf("F%d:\t|", f + 1);
            for (ArrayList<Integer> snapshot : frameHistory) {
                if (f < snapshot.size()) System.out.printf("%2d\t|", snapshot.get(f));
//                if (f < snapshot.size()) System.out.printf("%2d(%d,%d)|\t", snapshot.get(f));
                else System.out.print(" \t|");
            }
            System.out.println();
        }

        printBarHelper();
        System.out.print("S:\t|");
        for (char c : statusHistory) System.out.printf("%2c\t|", c);
        System.out.println();
        printBarHelper();

        double hitRatio = (double) numHits / pageSequence.size();
        System.out.printf("\nHits: %d, Faults: %d\nHit Ratio = %.2f%%\n", numHits, numFaults, hitRatio * 100);
    }

    public static void main(String[] args) throws FileNotFoundException {
        NRU_PageReplacementAlgo algo = new NRU_PageReplacementAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/NRU_PageReplacementAlgoNew.txt");
        algo.exec();
        algo.printResult();
    }
}
