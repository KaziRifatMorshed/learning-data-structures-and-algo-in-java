package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

class SecondChancePageReplaceAlgo {

    static class Frame {
        public int pageNo;
        public int iddx;
        public int refBit;
        public int arrival;

        public Frame(int pageNo, int iddx, int refBit, int arrival) {
            this.pageNo = pageNo;
            this.iddx = iddx;
            this.refBit = refBit;
            this.arrival = arrival;
        }

        Frame colne() {
            return new Frame(pageNo, iddx, refBit, arrival);
        }

        @Override
        public String toString() {
            return "" + pageNo + refBit;
        }
    }

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private int[] pageSequenceBits;
    private ArrayList<int[]> pageSequenceBitInput;
    private ArrayList<SecondChancePageReplaceAlgo.Frame> frames;
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
                frames.get(idx).refBit = pageSequenceBitInput.get(idx)[frames.size() - 1]; // set reference bit
                frameHistory.add(new ArrayList<>(frames));
                statusHistory.add('H');
            } else { // fault
                numFaults++;
                if (frames.size() < numFrame) {
                    frames.add(new Frame(pageNo, pgSeqIdx, pageSequenceBitInput.get(frames.size())[frames.size()], pgSeqIdx));
                } else {
                    ArrayList<Frame> temp = new ArrayList<>(numFrame);
                    for (int i = 0; i < numFrame; i++) {
                        temp.add(frames.get(i));
                    }
                    for (int i = 0; i < numFrame; i++) {
                        Collections.sort(temp, Comparator.comparingInt((Frame f) -> f.arrival));
                    }
                    for (int i = 0; i < numFrame; i++) {
                        if (temp.get(i).refBit == 0) {
                            int thisPgNo = temp.get(i).pageNo;
                            int thisFrmNo = 0;
                            for (int j = 0; j < numFrame; j++) {
                                if (frames.get(i).pageNo == thisPgNo) thisFrmNo = j;
                            }
                            frames.get(thisFrmNo).pageNo = pageNo;
                            frames.get(thisFrmNo).arrival = pgSeqIdx;
                            break;
                        }
                    }
                }
                frameHistory.add(new ArrayList<>(frames));
                statusHistory.add('F');
            }
        }
    }

//    void readFromFile(String path) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new File(path));
//        numFrame = scanner.nextInt();
//        frames = new ArrayList<>(numFrame);
//        while (scanner.hasNextInt()) {
//            pageSequence.add(scanner.nextInt());
//        }
//        scanner.close();
//    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        numFrame = scanner.nextInt();
        frames = new ArrayList<>(numFrame);
        pageSequenceBits = new int[numFrame];
        pageSequenceBitInput = new ArrayList<>();
        while (scanner.hasNextInt()) {
            pageSequence.add(scanner.nextInt());
            for (int i = 0; i < numFrame; i++) {
                pageSequenceBits[i] = scanner.nextInt();
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
        System.out.println("Second Chance Page Replacement Simulation:");
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

        for (int f = 0; f < numFrame; f++) {
            System.out.printf("F%d:\t|", f + 1);
            for (ArrayList<Frame> snapshot : frameHistory) {
                if (f < snapshot.size()) System.out.printf("%2d\t|", snapshot.get(f).pageNo);
                else System.out.print("  \t|");
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
        SecondChancePageReplaceAlgo algo = new SecondChancePageReplaceAlgo();
//        algo.readFromFile("src/Memory/PageReplacementAlgo/SecondChancePageReplaceAlgo.txt");
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/SecondChancePageReplaceAlgoNew.txt");
        algo.exec();
        algo.printResult();
    }
}
