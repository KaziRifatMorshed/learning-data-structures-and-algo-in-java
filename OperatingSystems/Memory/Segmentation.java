package OperatingSystems.Memory;

import java.util.Scanner;

class Segmentation {
    private int numSegments;
    private int[] base;
    private int[] limit;

    public Segmentation(int numSegments, int[] base, int[] limit) {
        this.numSegments = numSegments;
        this.base = base;
        this.limit = limit;
    }

    int getPhysicalAddress(int segmentNum, int offset) {
        if (segmentNum >= numSegments) {
            System.out.println("Invalid segment number");
            return -1;
        }
        if (offset >= limit[segmentNum]) {
            System.out.println("Invalid offset number");
            return -1;
        }
        return base[segmentNum] + offset;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Number of Segments: ");
        int numSegments = scanner.nextInt();

        int[] base = new int[numSegments];
        int[] limit = new int[numSegments];

        System.out.println("Enter Segment Table Entries: ");
        for (int i = 0; i < numSegments; i++) {
            System.out.print("Segment " + i + " Base: ");
            base[i] = scanner.nextInt();
            System.out.print("Segment " + i + " Limit: ");
            limit[i] = scanner.nextInt();
        }

        System.out.println("Enter Logical address's segment number: ");
        int segmentNum = scanner.nextInt();
        System.out.println("Enter Logical Address's Offset number");
        int offsetNum = scanner.nextInt();

        Segmentation segmentationSystem = new Segmentation(numSegments, base, limit);
        int phyAddr = segmentationSystem.getPhysicalAddress(segmentNum, offsetNum);
        System.out.println("Physical Address = " + phyAddr);
    }
}
