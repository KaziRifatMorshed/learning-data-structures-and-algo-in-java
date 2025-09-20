package OperatingSystems.Memory;

/*
For Paging
Input
	         Process Size
             Page Size
             Page Table Entry
             Logical Address
Output
	Physical Address
*/

import java.util.ArrayList;
import java.util.Scanner;

class Paging {
    private int processSize;
    private int pageSize;
    //    private ArrayList<Integer> pageTable;
    private int[] pageTable;

    public Paging(int processSize, int pageSize, int[] pageTable) {
        this.processSize = processSize;
        this.pageSize = pageSize;
        this.pageTable = pageTable;
    }

    public int getPhysicalAddress(int logicalAdd) {
        int pageNum = logicalAdd / pageSize;
        int offset = logicalAdd % pageSize;
        if (pageNum >= pageTable.length) {
            System.out.println("Invalid Page Number :" + pageNum);
            return -1;
        } else {
            int frameNum = pageTable[pageNum];
            return frameNum * pageSize + offset;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Process Size: ");
        int processSize = scanner.nextInt();

        System.out.print("Enter Page Size: ");
        int pageSize = scanner.nextInt();

        int numPages = (int) Math.ceil((double) processSize / (double) pageSize);
        int[] pageTable = new int[numPages];

        System.out.println("Enter page table entries:");
        for (int i = 0; i < numPages; i++) {
            System.out.print("Page " + i + ", Frame: ");
            pageTable[i] = scanner.nextInt();
        }

        System.out.println("Input Logical Address: ");
        int logAddr = scanner.nextInt();

        Paging pagingSystem = new Paging(processSize, pageSize, pageTable);
        int phyAddr = pagingSystem.getPhysicalAddress(logAddr);
        System.out.println("RESULT: physical address = "+ phyAddr);
    }
}
