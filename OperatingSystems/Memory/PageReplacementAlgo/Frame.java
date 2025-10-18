package OperatingSystems.Memory.PageReplacementAlgo;

class Frame {
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
