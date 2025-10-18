package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Circular_PageReplacementAlgo {

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Frame_C> frames;
    private ArrayList<ArrayList<Frame_C>> frameHistory = new ArrayList<>();
    private ArrayList<Character> statusHistory = new ArrayList<>();
    private int clockHand = 0;

    Circular_PageReplacementAlgo() {
    }

    // Inner Frame class
    private static class Frame_C {
        int pageNo;
        int refBit; // 0 or 1

        Frame_C(int pageNo) {
            this.pageNo = pageNo;
            this.refBit = 1; // when a page is loaded or referenced, refBit = 1
        }

        Frame_C(Frame_C other) {
            this.pageNo = other.pageNo;
            this.refBit = other.refBit;
        }
    }

    int frameContains(int key) {
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).pageNo == key) return i;
        }
        return -1;
    }

    void exec() {
        for (int pgSeqIdx = 0; pgSeqIdx < pageSequence.size(); pgSeqIdx++) {
            int pageNo = pageSequence.get(pgSeqIdx);
            int idx = frameContains(pageNo);

            if (idx >= 0) { // hit
                numHits++;
                frames.get(idx).refBit = 1;
                frameHistory.add(snapshotFrames());
                statusHistory.add('H');
            } else { // fault
                numFaults++;
                if (frames.size() < numFrame) {
                    frames.add(new Frame_C(pageNo));
                } else {
                    while (true) {
                        Frame_C cur = frames.get(clockHand);
                        if (cur.refBit == 0) { // replace this frame
                            frames.set(clockHand, new Frame_C(pageNo));
                            clockHand = (clockHand + 1) % numFrame;
                            break;
                        } else { // second chance
                            cur.refBit = 0;
                            clockHand = (clockHand + 1) % numFrame;
                        }
                    }
                }
                frameHistory.add(snapshotFrames());
                statusHistory.add('F');
            }
        }
    }

    private ArrayList<Frame_C> snapshotFrames() {
        ArrayList<Frame_C> snap = new ArrayList<>(numFrame);
        for (Frame_C f : frames) {
            snap.add(new Frame_C(f));
        }
        return snap;
    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        if (!scanner.hasNextInt()) {
            scanner.close();
            throw new FileNotFoundException("Input file does not start with number of frames.");
        }
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
        System.out.println("Circular Page Replacement Simulation:");
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
            for (ArrayList<Frame_C> snapshot : frameHistory) {
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

        double hitRatio = pageSequence.isEmpty() ? 0.0 : (double) numHits / pageSequence.size();
        System.out.printf("\nHits: %d, Faults: %d\nHit Ratio = %.2f%%\n", numHits, numFaults, hitRatio * 100);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Circular_PageReplacementAlgo algo = new Circular_PageReplacementAlgo();
        algo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/Circular_PageReplacementAlgo.txt");
        algo.exec();
        algo.printResult();
    }
}