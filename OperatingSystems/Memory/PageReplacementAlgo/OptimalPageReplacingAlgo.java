package OperatingSystems.Memory.PageReplacementAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class OptimalPageReplacingAlgo {

    private int numFrame;
    private int numHits = 0;
    private int numFaults = 0;
    private ArrayList<Integer> pageSequence = new ArrayList<>();
    private ArrayList<Integer> frames;
    private HashMap<ArrayList<Integer>, Character> result = new HashMap<>();

    OptimalPageReplacingAlgo() {
    }

    void exec() {
        for (int pgSeqIdx = 0; pgSeqIdx < pageSequence.size(); pgSeqIdx++) {
            int pageNo = pageSequence.get(pgSeqIdx);
            if (frames.contains(pageNo)) { // hit
                numHits++;
                ArrayList<Integer> snapshot = new ArrayList<>(frames);
                result.put(snapshot, 'h');
            } else { // page fault
                if (frames.size() < numFrame) { // new addition
                    frames.add(pageNo);

                } else { // replacement
                    int[][] pgIdxPair = new int[pageNo][2];
                    for (int i = 0; i < numFrame; i++) {
                        pgIdxPair[i][0] = frames.get(i);
                        pgIdxPair[i][1] = pgSeqIdx;
                    }

                    // oi index er porer index theke last porjonto search korbo
                    for (int i = pgSeqIdx + 1; i < pageSequence.size(); i++) {
                        int iteratorPageNo = pageSequence.get(i);
                        if (frames.contains(iteratorPageNo)) {
                            pgIdxPair[frames.indexOf(iteratorPageNo)][1] = i;
                        }
                    }

                    // find max
                    int idxToBeReplaced = pgIdxPair[0][1];
                    for (int k = 1; k < pageNo; k++) {
                        if (idxToBeReplaced < pgIdxPair[k][1]) {
                            idxToBeReplaced = k;
                        }
                    }
                    frames.set(idxToBeReplaced, pageNo);
                }

                ArrayList<Integer> snapshot = new ArrayList<>(frames);
                result.put(snapshot, 'f');
                numFaults++;
            }
        }

    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        numFrame = scanner.nextInt();
        frames = new ArrayList<>(numFrame);
        while (scanner.hasNext()) {
            pageSequence.add(scanner.nextInt());
        }
    }

    void printResult() {
        for (int i = 1; i <= numFrame; i++) {
            System.out.printf("F%d\t", i);

        }
        System.out.println();
        Double hitRatio = (double) numHits / (double) pageSequence.size();
        System.out.printf("\nHit ratio = " + (hitRatio * 100) + "%");
    }

    public static void main(String[] args) throws FileNotFoundException {
        OptimalPageReplacingAlgo optimalPageReplacingAlgo = new OptimalPageReplacingAlgo();
        optimalPageReplacingAlgo.readFromFile("OperatingSystems/Memory/PageReplacementAlgo/OptimalPageReplacingAlgo.txt");
        optimalPageReplacingAlgo.exec();
        optimalPageReplacingAlgo.printResult();
    }
}
